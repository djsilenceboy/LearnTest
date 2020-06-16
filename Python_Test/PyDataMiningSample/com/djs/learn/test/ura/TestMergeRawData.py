'''
Merge raw data in CSV.

Update log: (date / version / author : comments)
2020-06-16 / 1.0.0 / Du Jiang : Creation
'''

from com.djs.learn.ura import ConvertHtmlToRawData

__base_file_name = "URA_CondoEcRent_M_Sample"
__input_folder_path = "../../../../../Temp"
__input_file_prefix = __base_file_name
__output_file_path = "../../../../../Temp/URA_CondoEcRent_Merged.csv"

argv = ["-f", __input_folder_path, "-i", __input_file_prefix, "-o", __output_file_path]
ConvertHtmlToRawData.main(argv)

'''
Or run:

python ConvertHtmlToRawData.py -f "../../../../../Temp" -i "URA_CondoEcRent_M_Sample" -o "../../../../../Temp/URA_CondoEcRent_Merged.csv"
'''

if __name__ == '__main__':
    pass
