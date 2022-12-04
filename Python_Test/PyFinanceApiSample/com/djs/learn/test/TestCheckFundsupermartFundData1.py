'''
Check Fundsupermart fund data helper.

Update log: (date / version / author : comments)
2017-08-06 / 1.0.0 / Du Jiang : Creation
2017-12-13 / 2.0.0 / Du Jiang : Use new API
'''

from com.djs.learn.financeapi import CheckFinanceDataSelenium

__data_type = 0
__inventory_info_file_path = "../../../../etc/Fundsupermart_FundInfo.csv"
__result_output_file_path = "../../../../Temp/Fundsupermart_FundData.json"

__web_driver_type = 1

argv = ["-d", __data_type, "-i", __inventory_info_file_path, "-o", __result_output_file_path, "-t", __web_driver_type]
CheckFinanceDataSelenium.main(argv)

'''
Or run:

python CheckFinanceDataSelenium.py -d 0 -i "../../../../etc/Fundsupermart_FundInfo.csv" -o "../../../../Temp/Fundsupermart_FundData.json" -t 1
'''

if __name__ == '__main__':
    pass
