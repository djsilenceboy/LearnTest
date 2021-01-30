'''
Check currency XE data helper.

Update log: (date / version / author : comments)
2021-01-30 / 1.0.0 / Du Jiang : Creation
'''

from com.djs.learn.financeapi import CheckCurrencyXeRequests

__data_type = 0
__inventory_info_file_path = "../../../../etc/CurrencyInfo.csv"
__result_output_file_path = "../../../../Temp/CurrencyDataX.json"

# argv = ["-d", __data_type, "-i", __inventory_info_file_path, "-o", __result_output_file_path]
argv = ["-d", __data_type, "-i", __inventory_info_file_path, "-o", __result_output_file_path, "-k", "xxx"]
CheckCurrencyXeRequests.main(argv)

'''
Or run:

python CheckCurrencyXeRequests.py -d 0 -i "../../../../etc/CurrencyInfo.csv" -o "../../../../Temp/CurrencyDataX.json" -k "xxx"
'''

if __name__ == '__main__':
    pass
