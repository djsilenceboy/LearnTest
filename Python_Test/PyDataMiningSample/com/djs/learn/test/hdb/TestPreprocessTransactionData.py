'''
Preprocess HDB merged data.

Update log: (date / version / author : comments)
2020-06-27 / 1.0.0 / Du Jiang : Creation
'''

from com.djs.learn.hdb import PreprocessData

__data_type = 0
__base_file_name = "HDB_Trans_M"
__input_file_path = "../../../../../Temp/{0}.csv".format(__base_file_name)
__output_file_path = "../../../../../Temp/{0}P.csv".format(__base_file_name)

argv = ["-d", __data_type, "-i", __input_file_path, "-o", __output_file_path]
PreprocessData.main(argv)

'''
Or run:

python PreprocessData.py -d 0 -i "../../../../../Temp/HDB_Trans_M.csv" -o "../../../../../Temp/HDB_Trans_MP.csv"
'''

if __name__ == '__main__':
    pass
