'''
Check Yahoo finance stock data helper.

Update log: (date / version / author : comments)
2017-12-08 / 1.0.0 / Du Jiang : Creation
'''


from com.djs.learn.financeapi import CheckYahooFinanceStockData


__fund_info_file_path = "../../../../etc/StockInfoY.csv"
__result_output_file_path = "../../../../Temp/StockDataY.json"

# from time import time, localtime, strftime
# time_str = strftime("%Y%m%d_%H%M%S", localtime(time()))
# __result_output_file_path = "../../../../Temp/StockDataY_{0}.json".format(time_str)

# Test usage.
# argv = []
# argv = ["-h"]

# Test wrong.
# argv = ["-b"]
# argv = ["-i"]

# Test correct.
# argv = ["-i", __fund_info_file_path]
argv = ["-i", __fund_info_file_path, "-o", __result_output_file_path]
CheckYahooFinanceStockData.main(argv)


'''
Or run:

python CheckYahooFinanceStockData.py -i "../../../../etc/StockInfoY.csv" -o "../../../../Temp/StockDataY.json"
'''

if __name__ == '__main__':
    pass
