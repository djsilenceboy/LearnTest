'''
Check Google finance stock data helper.

Update log: (date / version / author : comments)
2017-11-07 / 1.0.0 / Du Jiang : Creation
'''


from com.djs.learn.financeapi import CheckGoogleFinanceStockData


# The path must be absolute path.
__geckodriver_file_path = "D:\Download\Shared\geckodriver.exe"
__geckodriver_log_file_path = "../../../../Temp/geckodriver.log"

__fund_info_file_path = "../../../../etc/StockInfo.csv"
__result_output_file_path = "../../../../Temp/StockData.json"

# from time import time, localtime, strftime
# time_str = strftime("%Y%m%d_%H%M%S", localtime(time()))
# __result_output_file_path = "../../../../Temp/StockData_{0}.json".format(time_str)

# Test usage.
# argv = []
# argv = ["-h"]

# Test wrong.
# argv = ["-b"]
# argv = ["-i"]
# argv = ["-w", __geckodriver_file_path]

# Test correct.
# argv = ["-i", __fund_info_file_path, "-w", __geckodriver_file_path, "-l", __geckodriver_log_file_path]
argv = ["-i", __fund_info_file_path, "-o", __result_output_file_path,
        "-w", __geckodriver_file_path, "-l", __geckodriver_log_file_path]
CheckGoogleFinanceStockData.main(argv)


'''
Or run:

python CheckGoogleFinanceStockData.py -i "../../../../etc/StockInfo.csv" -o "../../../../Temp/StockData.json" -w "D:\Download\Shared\geckodriver.exe" -l "../../../../Temp/geckodriver.log"
'''

if __name__ == '__main__':
    pass
