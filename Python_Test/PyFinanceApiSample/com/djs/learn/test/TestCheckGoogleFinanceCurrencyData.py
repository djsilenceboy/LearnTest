'''
Check Google finance currency data helper.

Update log: (date / version / author : comments)
2017-11-07 / 1.0.0 / Du Jiang : Creation
'''


from com.djs.learn.financeapi import CheckGoogleFinanceCurrencyData


# The path must be absolute path.
__geckodriver_file_path = "D:\Download\Shared\geckodriver.exe"
__geckodriver_log_file_path = "../../../../Temp/geckodriver.log"

__fund_info_file_path = "../../../../etc/CurrencyInfo.csv"
__result_output_file_path = "../../../../Temp/CurrencyData.json"

# from time import time, localtime, strftime
# time_str = strftime("%Y%m%d_%H%M%S", localtime(time()))
# __result_output_file_path = "../../../../Temp/CurrencyData_{0}.json".format(time_str)

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
CheckGoogleFinanceCurrencyData.main(argv)


'''
Or run:

python CheckGoogleFinanceCurrencyData.py -i "../../../../etc/CurrencyData.csv" -o "../../../../Temp/CurrencyData.json" -w "D:\Download\Shared\geckodriver.exe" -l "../../../../Temp/geckodriver.log"
'''

if __name__ == '__main__':
    pass
