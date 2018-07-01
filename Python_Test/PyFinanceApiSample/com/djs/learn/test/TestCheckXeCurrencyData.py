'''
Check XE currency data helper.

Update log: (date / version / author : comments)
2018-07-01 / 1.0.0 / Du Jiang : Creation
'''


from com.djs.learn.financeapi import CheckFinanceDataSelenium

__data_type = 1
__inventory_info_file_path = "../../../../etc/CurrencyInfo.csv"
__result_output_file_path = "../../../../Temp/CurrencyDataX.json"

# The path must be absolute path.
__web_driver_file_path = "D:\Download\Shared\phantomjs.exe"
__web_driver_log_file_path = "../../../../Temp/phantomjs.log"

argv = ["-d", __data_type, "-i", __inventory_info_file_path, "-o", __result_output_file_path,
        "-w", __web_driver_file_path, "-l", __web_driver_log_file_path]
CheckFinanceDataSelenium.main(argv)

'''
Or run:

python CheckFinanceDataSelenium.py -d 1 -i "../../../../etc/CurrencyInfo.csv" -o "../../../../Temp/CurrencyDataX.json" -w "D:\Download\Shared\phantomjs.exe" -l "../../../../Temp/phantomjs.log"
'''

if __name__ == '__main__':
    pass
