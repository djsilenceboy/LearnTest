'''
Check Fundsupermart fund data helper.

Update log: (date / version / author : comments)
2017-08-06 / 1.0.0 / Du Jiang : Creation
'''


from time import time, localtime, strftime

from com.djs.learn.financeapi import CheckFundsupermartFundData


# The path must be absolute path.
__geckodriver_file_path = "D:\Download\Shared\geckodriver.exe"
__geckodriver_log_file_path = "../../../../Temp/geckodriver.log"

time_str = strftime("%Y%m%d_%H%M%S", localtime(time()))

__fund_info_file_path = "../../../../etc/Fundsupermart_FundInfo.csv"
__result_output_file_path = "../../../../Temp/Fundsupermart_FundData_{0}.json".format(
    time_str)

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
CheckFundsupermartFundData.main(argv)


'''
Or run:

python CheckFundsupermartFundData.py -i "../../../../etc/Fundsupermart_FundInfo.csv" -o "../../../../Temp/Fundsupermart_FundData.json" -w "D:\Download\Shared\geckodriver.exe" -l "../../../../Temp/geckodriver.log"
'''

if __name__ == '__main__':
    pass
