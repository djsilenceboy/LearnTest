'''
Convert table data from html to CSV.

Update log: (date / version / author : comments)
2020-06-27 / 1.0.0 / Du Jiang : Creation
'''

from com.djs.learn.hdb import ConvertHtmlToRawData

__base_file_name = "HDB_Trans_"
__input_folder_path = "../../../../../Temp"
__input_file_prefix = __base_file_name

argv = ["-f", __input_folder_path, "-i", __input_file_prefix]
ConvertHtmlToRawData.main(argv)

'''
Or run:

python ConvertHtmlToRawData.py -f "../../../../../Temp" -i "HDB_Trans_"
'''

if __name__ == '__main__':
    pass
