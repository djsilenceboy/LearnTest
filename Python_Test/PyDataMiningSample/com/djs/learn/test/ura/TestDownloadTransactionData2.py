'''
Download URA data.

Update log: (date / version / author : comments)
2020-06-10 / 1.0.0 / Du Jiang : Creation
'''

from com.djs.learn.ura import DownloadData

__data_type = 0
__base_file_name = "URA_CondoEcTrans_M"

argv = ["-d", __data_type]
DownloadData.main(argv)

'''
Or run:

python DownloadData.py -d 0
'''

if __name__ == '__main__':
    pass
