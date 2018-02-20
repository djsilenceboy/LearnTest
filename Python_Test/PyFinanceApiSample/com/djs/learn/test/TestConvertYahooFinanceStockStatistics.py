'''
Convert Yahoo finance stock statistics helper: From JSON to CSV.

Update log: (date / version / author : comments)
2018-02-19 / 1.0.0 / Du Jiang : Creation
'''


from com.djs.learn.dataformat import ConvertFinanceStatistics

__data_type = 0
__base_file_name = "StockStatisticsY"
__input_file_path = "../../../../Temp/{0}.json".format(__base_file_name)
__output_file_path = "../../../../Temp/{0}.csv".format(__base_file_name)

argv = ["-d", __data_type, "-i", __input_file_path, "-o", __output_file_path]
ConvertFinanceStatistics.main(argv)

'''
Or run:

python ConvertFinanceStatistics.py -d 2 -i "../../../../Temp/StockStatisticsY.json" -o "../../../../Temp/StockStatisticsY.csv" 
'''

if __name__ == '__main__':
    pass
