'''
Retrieve game list (by Requests).

Update log: (date / version / author : comments)
2020-07-11 / 1.0.0 / Du Jiang : Creation
'''

from com.djs.learn.metacritic import RetrieveGameList

__data_type = 0
__max_pages = 176

argv = ["-d", __data_type, "-p", __max_pages]
RetrieveGameList.main(argv)

'''
Or run:

python RetrieveGameList.py -d 0 -p 176
'''

if __name__ == '__main__':
    pass
