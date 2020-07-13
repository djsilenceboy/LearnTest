'''
Process data by SQLite to generate PSS games with Metacritic scores.

Update log: (date / version / author : comments)
2020-07-13 / 1.0.0 / Du Jiang : Creation
'''

from com.djs.learn.gaming import PssGameMetaScore

__input_file_path_playstation_store = "../../../../../Temp/{0}.csv".format("PlayStationStore_GameList")
__input_file_path_metascritic = "../../../../../Temp/{0}.csv".format("Metacritic_GameList")
__db_file_path = "../../../../../Temp/{0}.db".format("PssGameMetaScore")
__output_file_prefix_path = "../../../../../Temp/{0}".format("PssGameMetaScore_")

argv = ["-p", __input_file_path_playstation_store, "-m", __input_file_path_metascritic, "-d", __db_file_path, "-o", __output_file_prefix_path]
PssGameMetaScore.main(argv)

'''
Or run:

python PssGameMetaScore.py -p "../../../../../Temp/PlayStationStore_GameList.csv" -m "../../../../../Temp/Metacritic_GameList.csv" -d "../../../../../Temp/PssGameMetaScore.db" -o "../../../../../Temp/PssGameMetaScore_"
'''

if __name__ == '__main__':
    pass
