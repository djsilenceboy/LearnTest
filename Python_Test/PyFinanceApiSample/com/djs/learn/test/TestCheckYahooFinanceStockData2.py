'''
Check Yahoo finance stock data helper.

Update log: (date / version / author : comments)
2022-02-05 / 1.0.0 / Du Jiang : Creation
'''

from com.djs.learn.financeapi import CheckFinanceDataSelenium2

__data_type = 0
__inventory_info_file_path = "../../../../etc/StockInfoY.csv"
__result_output_file_path = "../../../../Temp/StockDataY.json"

__web_driver_type = 0
# The path must be absolute path.
__web_driver_file_path = "D:\Download\Shared\phantomjs.exe"
__web_driver_log_file_path = "../../../../Temp/phantomjs.log"

argv = ["-d", __data_type, "-i", __inventory_info_file_path, "-o", __result_output_file_path, "-t", __web_driver_type,
        "-w", __web_driver_file_path, "-l", __web_driver_log_file_path]
CheckFinanceDataSelenium2.main(argv)

'''
Or run:

python CheckFinanceDataSelenium2.py -d 0 -i "../../../../etc/StockInfoY.csv" -o "../../../../Temp/StockDataY.json" -t 0 -w "D:\Download\Shared\phantomjs.exe" -l "../../../../Temp/phantomjs.log"
'''

if __name__ == '__main__':
    pass
