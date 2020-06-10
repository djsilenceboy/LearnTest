'''
Download URA data.

Update log: (date / version / author : comments)
2020-06-10 / 1.0.0 / Du Jiang : Creation
'''

from com.djs.learn.ura import DownloadData

__data_type = 0
__base_file_name = "URA_CondoEcTrans_M"
__output_file_path = "../../../../../Temp/{0}.csv".format(__base_file_name)

argv = ["-d", __data_type, "-o", __output_file_path]
DownloadData.main(argv)

'''
Or run:

python DownloadData.py -d 0 -o "../../../../../Temp/URA_CondoEcTrans_M.csv"
'''

if __name__ == '__main__':
    pass
