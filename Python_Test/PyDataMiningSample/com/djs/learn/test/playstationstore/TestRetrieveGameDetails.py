'''
Retrieve game list (by Requests).

Update log: (date / version / author : comments)
2020-07-10 / 1.0.0 / Du Jiang : Creation
'''

from com.djs.learn.playstationstore import RetrieveGameList

__data_type = 0
__output_file_path = "../../../../../Temp/{0}.csv".format("PlayStationStore_GameList")

argv = ["-d", __data_type, "-o", __output_file_path]
RetrieveGameList.main(argv)

'''
Or run:

python RetrieveGameList.py -d 0 -o "../../../../../Temp/PlayStationStore_GameList.csv"
'''

if __name__ == '__main__':
    pass
