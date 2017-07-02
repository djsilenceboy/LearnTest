import csv
from os import path
from com.djs.learn.common import LoggingHelper
from com.djs.learn.files.StockCompareRecordsHelper import StockCompareRecordsHelper

input_file_path = "../../../../etc"
output_file_path = "../../../../Temp"

LoggingHelper.set_logging(path.join(output_file_path, "Monitoring.Log"))


file_name = path.join(input_file_path, "StockCompareList.csv")

with open(file_name) as file:
    print('file =', file)
    cin = csv.reader(file)
    records_in = [line for line in cin]
    print("records =", records_in)
    print("fieldnames =", records_in.pop(0))
    print("records =", records_in)
    print("record =", records_in[1][1])


print("-" * 40)

with open(file_name) as file:
    print('file =', file)
    cin = csv.DictReader(file)
    print("fieldnames =", cin.fieldnames)
    records_in = [line for line in cin]
    print("records =", records_in)

print("-" * 40)
# cin = csv.DictReader(file, fieldnames=["Company name", "Ticker", "First selection date", "Check date"])

print("-" * 40)

try:
    stockCompareRecordsHelper = StockCompareRecordsHelper(file_name)
    headers, records = stockCompareRecordsHelper.retrieve()
    print("headers =", headers)
    print("records =", records)

    record = stockCompareRecordsHelper.find_by_ticker("AIG")
    print("record =", record)
except Exception as e:
    print("Retrieve failed:", e)

if __name__ == '__main__':
    pass
