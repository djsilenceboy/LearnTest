'''
Preprocess URA merged data.

Update log: (date / version / author : comments)
2020-06-06 / 1.0.0 / Du Jiang : Creation
'''

from com.djs.learn.ura import PreprocessData

__data_type = 1
__base_file_name = "URA_CondoEcRent_M"
__input_file_path = "../../../../../Temp/{0}.csv".format(__base_file_name)

argv = ["-d", __data_type, "-i", __input_file_path]
PreprocessData.main(argv)

'''
Or run:

python PreprocessData.py -d 0 -i "../../../../../Temp/URA_CondoEcRent_M.csv"
'''

if __name__ == '__main__':
    pass
