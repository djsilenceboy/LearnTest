'''
Convert Fundsupermart fund data helper: From JSON to CSV.

Update log: (date / version / author : comments)
2017-08-13 / 1.0.0 / Du Jiang : Creation
2017-12-13 / 2.0.0 / Du Jiang : Use new API
'''


from com.djs.learn.dataformat import ConvertFinanceData

__data_type = 0
__base_file_name = "Fundsupermart_FundData"
__input_file_path = "../../../../Temp/{0}.json".format(__base_file_name)
__output_file_path = "../../../../Temp/{0}.csv".format(__base_file_name)

argv = ["-d", __data_type, "-i", __input_file_path, "-o", __output_file_path]
ConvertFinanceData.main(argv)

'''
Or run:

python ConvertFinanceData.py -d 0 -i "../../../../Temp/Fundsupermart_FundData.json" -o "../../../../Temp/Fundsupermart_FundData.csv" 
'''

if __name__ == '__main__':
    pass
