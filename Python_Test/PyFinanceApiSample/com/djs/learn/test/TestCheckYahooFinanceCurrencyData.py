'''
Check Yahoo finance currency data helper.

Update log: (date / version / author : comments)
2017-12-10 / 1.0.0 / Du Jiang : Creation
2017-12-13 / 2.0.0 / Du Jiang : Use new API
'''


from com.djs.learn.financeapi import CheckFinanceDataRequests

__data_type = 1
__inventory_info_file_path = "../../../../etc/CurrencyInfo.csv"
__result_output_file_path = "../../../../Temp/CurrencyDataY.json"

argv = ["-d", __data_type, "-i", __inventory_info_file_path,
        "-o", __result_output_file_path]
CheckFinanceDataRequests.main(argv)

'''
Or run:

python CheckFinanceDataRequests.py -d 1 -i "../../../../etc/CurrencyData.csv" -o "../../../../Temp/CurrencyDataY.json"
'''

if __name__ == '__main__':
    pass
