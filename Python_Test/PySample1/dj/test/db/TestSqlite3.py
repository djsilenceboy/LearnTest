'''
Created on Apr 5, 2016

@author: dj
'''

import os
import sqlite3


os.remove('../../../etc/SampleSqlite3.db')

print("-" * 40)

conn = sqlite3.connect('../../../etc/SampleSqlite3.db')
print("conn =", conn)

curs = conn.cursor()
print("curs =", curs)

print("-" * 40)

curs.execute('''
CREATE TABLE ZOO (
TYPE VARCHAR(10) PRIMARY KEY,
NAME VARCHAR(10),
AGE INT
)
''')

print("-" * 40)

curs.execute('INSERT INTO ZOO VALUES ("Cat", "Tom", 10)')

insert_sql = 'INSERT INTO ZOO (TYPE, NAME, AGE) VALUES (?, ?, ?)'
print("insert_sql =", insert_sql)

curs.execute(insert_sql, ('Dog', 'Hog', 12))
curs.execute(insert_sql, ('Mouse', 'Jerry', 8))

conn.commit()

print("-" * 40)

curs.execute('SELECT * FROM ZOO')
rows = curs.fetchall()

print("rows =", rows)

print("-" * 40)

curs.close()
conn.close()

if __name__ == '__main__':
    pass
