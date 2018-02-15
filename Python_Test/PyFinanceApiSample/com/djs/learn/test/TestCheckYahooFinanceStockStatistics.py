'''
Check Yahoo finance stock statistic helper.

Update log: (date / version / author : comments)
2018-02-15 / 1.0.0 / Du Jiang : Creation
'''


from com.djs.learn.financeapi import CheckFinanceStatisticsRequests

__data_type = 0
__inventory_info_file_path = "../../../../etc/StockInfoY_S.csv"
__result_output_file_path = "../../../../Temp/StockStatisticsY.json"

argv = ["-d", __data_type, "-i", __inventory_info_file_path,
        "-o", __result_output_file_path]
CheckFinanceStatisticsRequests.main(argv)

'''
Or run:

python CheckFinanceStatisticsRequests.py -d 0 -i "../../../../etc/StockInfoY_S.csv" -o "../../../../Temp/StockStatisticsY.json"
'''

if __name__ == '__main__':
    pass
