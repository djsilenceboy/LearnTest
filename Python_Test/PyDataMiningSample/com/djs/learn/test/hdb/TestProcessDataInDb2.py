'''
Process HDB data by SQLite.

Update log: (date / version / author : comments)
2020-06-27 / 1.0.0 / Du Jiang : Creation
'''

from com.djs.learn.hdb import ProcessDataInDb

__input_file_path_transaction = "../../../../../Temp/{0}.csv".format("HDB_Trans_MP")

argv = ["-t", __input_file_path_transaction]
ProcessDataInDb.main(argv)

'''
Or run:

python ProcessDataInDb.py -t "../../../../../Temp/HDB_Trans_MP.csv"
'''

if __name__ == '__main__':
    pass
