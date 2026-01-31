import xml.etree.ElementTree as ET
import re
import os
from html import unescape
from datetime import datetime

# -------- CONFIG --------
INPUT_WXR = "input.wxr.xml"
OUTPUT_DIR = "posts"
# ------------------------

def strip_html(html_text):
    """Remove HTML tags and decode entities."""
    if not html_text:
        return ""
    text = unescape(html_text)
    text = re.sub(r"<[^>]+>", "", text)
    return text.strip()

def sanitize_filename(name):
    """Make filename safe for all OS."""
    name = unescape(name)
    name = re.sub(r'[\\/:*?"<>|]+', "_", name)
    name = re.sub(r"\s+", " ", name).strip()
    return name[:200]

def parse_pubdate(pubdate_text):
    """
    Convert pubDate like:
    'Mon, 25 Dec 2023 10:30:00 +0000'
    → '2023-12-25'
    """
    if not pubdate_text:
        return "unknown-date"
    try:
        dt = datetime.strptime(pubdate_text.strip(), "%a, %d %b %Y %H:%M:%S %z")
        return dt.strftime("%Y-%m-%d")
    except ValueError:
        return "unknown-date"

def extract_posts(wxr_file, output_dir):
    os.makedirs(output_dir, exist_ok=True)

    namespaces = {
        "content": "http://purl.org/rss/1.0/modules/content/"
    }

    tree = ET.parse(wxr_file)
    root = tree.getroot()

    count = 0

    for item in root.findall(".//item"):
        title_elem = item.find("title")
        pubdate_elem = item.find("pubDate")
        content_elem = item.find("content:encoded", namespaces)

        if content_elem is None or not content_elem.text:
            continue

        title = title_elem.text.strip() if title_elem is not None else f"post_{count+1}"
        pubdate_raw = pubdate_elem.text if pubdate_elem is not None else None
        pubdate = parse_pubdate(pubdate_raw)

        safe_title = sanitize_filename(title) or f"post_{count+1}"
        filename = f"{pubdate}_{safe_title}.txt"
        filepath = os.path.join(output_dir, filename)

        clean_text = strip_html(content_elem.text)

        with open(filepath, "w", encoding="utf-8") as f:
            f.write(f"Title: {title}\n")
            f.write(f"Date: {pubdate}\n\n")
            f.write(clean_text)

        count += 1

    print(f"Saved {count} posts to folder '{output_dir}'")

if __name__ == "__main__":
    extract_posts(INPUT_WXR, OUTPUT_DIR)
