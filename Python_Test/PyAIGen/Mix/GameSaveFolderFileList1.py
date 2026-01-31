import os
import csv
from datetime import datetime

# ===== CONFIGURATION =====
ROOT_DIR = r"F:\WorkDJS\Test\AIGen\GameSaveFolder\GameSave"   # change this to your root folder path
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

        for file_name in os.listdir(subfolder_path):
            file_path = os.path.join(subfolder_path, file_name)
            if not os.path.isfile(file_path):
                continue

            # File modified date
            mtime = os.path.getmtime(file_path)
            file_date = datetime.fromtimestamp(mtime).strftime("%Y-%m-%d")

            # File name split
            name_without_ext, ext = os.path.splitext(file_name)

            rows.append([
                folder_name,
                subfolder_name,
                file_date,
                name_without_ext,
                file_name
            ])

# Write CSV (Excel-friendly encoding)
with open(OUTPUT_CSV, "w", newline="", encoding="utf-8-sig") as f:
    writer = csv.writer(f)
    writer.writerow([
        "Folder Name",
        "Subfolder Name",
        "File Date",
        "File Name (No Extension)",
        "File Name (With Extension)"
    ])
    writer.writerows(rows)

print(f"CSV file generated: {OUTPUT_CSV}")
