'''
Process URA data by SQLite.

Update log: (date / version / author : comments)
2020-06-07 / 1.0.0 / Du Jiang : Creation
'''

from com.djs.learn.ura import ProcessDataInDb

__input_file_path_transaction = "../../../../../Temp/{0}.csv".format("URA_CondoEcTrans_MP")
__input_file_path_rental = "../../../../../Temp/{0}.csv".format("URA_CondoEcRent_MP")
__db_file_path = "../../../../../Temp/{0}.db".format("URA_CondoEcTransRent")
__output_file_prefix_path = "../../../../../Temp/{0}".format("URA_Results_CondoEc_")

argv = ["-t", __input_file_path_transaction, "-r", __input_file_path_rental, "-d", __db_file_path, "-o", __output_file_prefix_path]
ProcessDataInDb.main(argv)

'''
Or run:

python ProcessDataInDb.py -t "../../../../../Temp/URA_CondoEcTrans_MP.csv" -r "../../../../../Temp/URA_CondoEcRent_MP.csv" -d "../../../../../Temp/URA_CondoEcTransRent.db" -o "../../../../../Temp/URA_Results_CondoEc_"
'''

if __name__ == '__main__':
    pass
