import os
import csv
from datetime import datetime

# ===== CONFIGURATION =====
ROOT_DIR = r"F:\WorkDJS\Test\AIGen\GameSaveFolder\GameSave"   # <-- change this to your root folder path
OUTPUT_CSV = "file_list.csv"
# =========================

rows = []

for folder_name in os.listdir(ROOT_DIR):
    folder_path = os.path.join(ROOT_DIR, folder_name)
    if not os.path.isdir(folder_path):
        continue

    for subfolder_name in os.listdir(folder_path):
        subfolder_path = os.path.join(folder_path, subfolder_name)
        if not os.path.isdir(subfolder_path):
            continue

        files_info = []

        for file_name in os.listdir(subfolder_path):
            file_path = os.path.join(subfolder_path, file_name)
            if not os.path.isfile(file_path):
                continue

            # File last modified time (Windows-safe)
            mtime = os.path.getmtime(file_path)
            file_date = datetime.fromtimestamp(mtime).strftime("%Y-%m-%d")

            name_without_ext, ext = os.path.splitext(file_name)

            files_info.append((
                mtime,                 # used only for sorting
                folder_name,
                subfolder_name,
                file_date,
                name_without_ext,
                file_name
            ))

        # Sort by last modified time (oldest → newest)
        files_info.sort(key=lambda x: x[0])

        for _, folder, subfolder, date, name_no_ext, name_with_ext in files_info:
            rows.append([
                folder,
                subfolder,
                date,
                name_no_ext,
                name_with_ext
            ])

# Write CSV (UTF-8 BOM so Excel displays Chinese correctly)
with open(OUTPUT_CSV, "w", newline="", encoding="utf-8-sig") as f:
    writer = csv.writer(f)
    writer.writerow([
        "Folder Name",
        "Subfolder Name",
        "File Modified Date",
        "File Name (No Extension)",
        "File Name (With Extension)"
    ])
    writer.writerows(rows)

print(f"CSV file generated successfully: {OUTPUT_CSV}")
