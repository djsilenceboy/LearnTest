'''
Check Fundsupermart fund data helper.

Update log: (date / version / author : comments)
2020-05-02 / 1.0.0 / Du Jiang : Creation
'''

from com.djs.learn.financeapi import CheckFinanceDataSelenium

__data_type = 0
__inventory_info_file_path = "../../../../etc/Fundsupermart_FundInfo.csv"
__result_output_file_path = "../../../../Temp/Fundsupermart_FundData.json"

__web_driver_type = 2
# The path must be absolute path.
__web_driver_file_path = "D:\Download\Shared\chromedriver.exe"
__web_driver_log_file_path = "../../../../Temp/chromedriver.log"

argv = ["-d", __data_type, "-i", __inventory_info_file_path, "-o", __result_output_file_path, "-t", __web_driver_type,
        "-w", __web_driver_file_path, "-l", __web_driver_log_file_path]
CheckFinanceDataSelenium.main(argv)

'''
Or run:

python CheckFinanceDataSelenium.py -d 0 -i "../../../../etc/Fundsupermart_FundInfo.csv" -o "../../../../Temp/Fundsupermart_FundData.json" -t 2 -w "D:\Download\Shared\chromedriver.exe" -l "../../../../Temp/chromedriver.log"
'''

if __name__ == '__main__':
    pass
