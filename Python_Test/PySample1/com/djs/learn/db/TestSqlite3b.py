'''
Created on Apr 5, 2016

@author: dj
'''

# Run TestSqlite3.py first.

from os import path
import sqlite3

db_file_path = "../../../../Temp"
db_file = path.join(db_file_path, "SampleSqlite3.db")

conn = sqlite3.connect(db_file)
print("conn =", conn)

cur = conn.cursor()
print("cur =", cur)

print("-" * 40)

cur.execute('SELECT * FROM SALE_ORDER')
rows = cur.fetchall()

print("rows =", rows)

print("-" * 40)

cur.close()
conn.close()


if __name__ == '__main__':
    pass
