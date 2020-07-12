'''
Convert table data from html to CSV.

Update log: (date / version / author : comments)
2020-07-12 / 1.0.0 / Du Jiang : Creation
'''

from com.djs.learn.metacritic import ConvertHtmlToRawData

__base_file_name = "Metacritic_GameList_"
__input_folder_path = "../../../../../Temp"
__input_file_prefix = __base_file_name
__output_file_path = "../../../../../Temp/{0}M.csv".format(__base_file_name)

argv = ["-f", __input_folder_path, "-i", __input_file_prefix, "-o", __output_file_path]
ConvertHtmlToRawData.main(argv)

'''
Or run:

python ConvertHtmlToRawData.py -f "../../../../../Temp" -i "Metacritic_GameList_" -o "../../../../../Temp/Metacritic_GameList_M.csv"
'''

if __name__ == '__main__':
    pass
