'''
Process HDB data by SQLite.

Update log: (date / version / author : comments)
2020-06-27 / 1.0.0 / Du Jiang : Creation
'''

from com.djs.learn.hdb import ProcessDataInDb

__input_file_path_transaction = "../../../../../Temp/{0}.csv".format("HDB_Trans_MP")
__db_file_path = "../../../../../Temp/{0}.db".format("HDB_Trans")
__output_file_prefix_path = "../../../../../Temp/{0}".format("HDB_Results_")

argv = ["-t", __input_file_path_transaction, "-d", __db_file_path, "-o", __output_file_prefix_path]
ProcessDataInDb.main(argv)

'''
Or run:

python ProcessDataInDb.py -t "../../../../../Temp/HDB_Trans_MP.csv" -d "../../../../../Temp/HDB_Trans.db" -o "../../../../../Temp/HDB_Results_"
'''

if __name__ == '__main__':
    pass
