
from os import path
from com.djs.learn.common import LoggingHelper
from com.djs.learn.financeapi.CheckStockInfo import CheckStockInfo

input_file_path = "../../../../etc"
output_file_path = "../../../../Temp"

LoggingHelper.set_logging(path.join(output_file_path, "Monitoring.Log"))


stock_info_file_name = path.join(input_file_path, "StockInfo.csv")
finance_apikey_file_name = path.join(output_file_path, "FinanceApiKey.csv")

try:
    checkStockInfo = CheckStockInfo(
        stock_info_file_name, finance_apikey_file_name)
    checkStockInfo.retrieve()
    checkStockInfo.process_records()
except Exception as e:
    print("Check failed:", e)

if __name__ == '__main__':
    pass
