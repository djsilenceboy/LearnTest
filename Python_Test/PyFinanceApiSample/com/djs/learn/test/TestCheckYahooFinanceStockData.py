'''
Deprecated.
Check Yahoo finance stock data helper.

Update log: (date / version / author : comments)
2017-12-08 / 1.0.0 / Du Jiang : Creation
2017-12-13 / 2.0.0 / Du Jiang : Use new API
2022-02-05 / Du Jiang : Deprecated.
'''

from com.djs.learn.financeapi import CheckFinanceDataRequests

__data_type = 0
__inventory_info_file_path = "../../../../etc/StockInfoY.csv"
__result_output_file_path = "../../../../Temp/StockDataY.json"

argv = ["-d", __data_type, "-i", __inventory_info_file_path,
        "-o", __result_output_file_path]
CheckFinanceDataRequests.main(argv)

'''
Or run:

python CheckFinanceDataRequests.py -d 0 -i "../../../../etc/StockInfoY.csv" -o "../../../../Temp/StockDataY.json"
'''

if __name__ == '__main__':
    pass
