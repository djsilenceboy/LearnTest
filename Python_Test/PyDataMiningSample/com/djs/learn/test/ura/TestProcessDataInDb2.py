'''
Process URA data by SQLite.

Update log: (date / version / author : comments)
2020-06-07 / 1.0.0 / Du Jiang : Creation
'''

from com.djs.learn.ura import ProcessDataInDb

__input_file_path_transaction = "../../../../../Temp/{0}.csv".format("URA_CondoEcTrans_MP")
__input_file_path_rental = "../../../../../Temp/{0}.csv".format("URA_CondoEcRent_MP")

argv = ["-t", __input_file_path_transaction, "-r", __input_file_path_rental]
ProcessDataInDb.main(argv)

'''
Or run:

python ProcessDataInDb.py -t "../../../../../Temp/URA_CondoEcTrans_MP.csv" -r "../../../../../Temp/URA_CondoEcRent_MP.csv"
'''

if __name__ == '__main__':
    pass
