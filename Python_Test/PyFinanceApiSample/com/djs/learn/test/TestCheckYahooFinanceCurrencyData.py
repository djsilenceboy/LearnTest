'''
Check Yahoo finance currency data helper.

Update log: (date / version / author : comments)
2017-12-10 / 1.0.0 / Du Jiang : Creation
'''


from com.djs.learn.financeapi import CheckYahooFinanceCurrencyData


__fund_info_file_path = "../../../../etc/CurrencyInfo.csv"
__result_output_file_path = "../../../../Temp/CurrencyDataY.json"

# from time import time, localtime, strftime
# time_str = strftime("%Y%m%d_%H%M%S", localtime(time()))
# __result_output_file_path = "../../../../Temp/CurrencyDataY_{0}.json".format(time_str)

# Test usage.
# argv = []
# argv = ["-h"]

# Test wrong.
# argv = ["-b"]
# argv = ["-i"]

# Test correct.
# argv = ["-i", __fund_info_file_path]
argv = ["-i", __fund_info_file_path, "-o", __result_output_file_path]
CheckYahooFinanceCurrencyData.main(argv)


'''
Or run:

python CheckYahooFinanceCurrencyData.py -i "../../../../etc/CurrencyData.csv" -o "../../../../Temp/CurrencyDataY.json"
'''

if __name__ == '__main__':
    pass
