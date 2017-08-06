'''
Check Fundsupermart fund data helper.

Update log: (date / version / author : comments)
2017-08-06 / 1.0.0 / Du Jiang : Creation
'''


from com.djs.learn.financeapi import CheckFundsupermartFundData

# The path must be absolute path.
__geckodriver_file_path = "D:\Download\Shared\geckodriver.exe"
__geckodriver_log_file_path = "../../../../Temp/geckodriver.log"

__fund_info_file_path = "../../../../etc/Fundsupermart_FundInfo.csv"
__result_output_file_path = "../../../../Temp/Fundsupermart_FundData.json"

# Test usage.
# argv = []
# argv = ["-h"]

# Test wrong.
# argv = ["-b"]
# argv = ["-i"]
# argv = ["-w", __geckodriver_file_path]

# Test correct.
argv = ["-i", __fund_info_file_path, "-w",
        __geckodriver_file_path, "-l", __geckodriver_log_file_path]
# argv = ["-i", __fund_info_file_path, "-o", __result_output_file_path, "-w", __geckodriver_file_path, "-l", __geckodriver_log_file_path]
CheckFundsupermartFundData.main(argv)


'''
Or run:

python CheckSlaPortal.py -w "D:\Download\Shared\geckodriver.exe" -i "../../../../etc/Fundsupermart_FundInfo.csv" -o "../../../../Temp/Fundsupermart_FundData.json"
'''

if __name__ == '__main__':
    pass
