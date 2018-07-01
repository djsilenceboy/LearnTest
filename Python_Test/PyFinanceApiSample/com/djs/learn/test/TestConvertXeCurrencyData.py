'''
Convert XE currency data helper: From JSON to CSV.

Update log: (date / version / author : comments)
2018-07-01 / 1.0.0 / Du Jiang : Creation
'''


from com.djs.learn.dataformat import ConvertFinanceData

__data_type = 3
__base_file_name = "CurrencyDataX"
__input_file_path = "../../../../Temp/{0}.json".format(__base_file_name)
__output_file_path = "../../../../Temp/{0}.csv".format(__base_file_name)

argv = ["-d", __data_type, "-i", __input_file_path, "-o", __output_file_path]
ConvertFinanceData.main(argv)

'''
Or run:

python ConvertFinanceData.py -d 3 -i "../../../../Temp/CurrencyDataX.json" -o "../../../../Temp/CurrencyDataX.csv" 
'''

if __name__ == '__main__':
    pass
