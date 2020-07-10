'''
Retrieve game list (by Requests).

Update log: (date / version / author : comments)
2020-07-10 / 1.0.0 / Du Jiang : Creation
'''

from com.djs.learn.playstationstore import RetrieveGameList

__data_type = 0

argv = ["-d", __data_type]
RetrieveGameList.main(argv)

'''
Or run:

python RetrieveGameList.py -d 0
'''

if __name__ == '__main__':
    pass
