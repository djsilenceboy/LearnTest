'''
Created on Apr 5, 2016

@author: dj
'''

import sqlite3

conn = sqlite3.connect('../../../etc/SampleSqlite3.db')
print("conn =", conn)

curs = conn.cursor()
print("curs =", curs)

print("-" * 40)

curs.execute('SELECT * FROM ZOO')
rows = curs.fetchall()

print("rows =", rows)

print("-" * 40)

curs.close()
conn.close()


if __name__ == '__main__':
    pass
