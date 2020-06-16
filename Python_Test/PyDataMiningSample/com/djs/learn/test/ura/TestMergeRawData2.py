'''
Merge raw data in CSV.

Update log: (date / version / author : comments)
2020-06-16 / 1.0.0 / Du Jiang : Creation
'''

from com.djs.learn.ura import MergeRawData

__base_file_name = "URA_CondoEcRent_M_Sample"
__input_folder_path = "../../../../../Temp"
__input_file_prefix = __base_file_name

argv = ["-f", __input_folder_path, "-i", __input_file_prefix]
MergeRawData.main(argv)

'''
Or run:

python MergeRawData.py -f "../../../../../Temp" -i "URA_CondoEcRent_M_Sample"
'''

if __name__ == '__main__':
    pass
