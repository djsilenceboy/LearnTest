'''
Created on Apr 5, 2016

@author: dj
'''

from os import path
import os
import sqlite3


db_file_path = "../../../../Temp"
db_file = path.join(db_file_path, "SampleSqlite3.db")

if path.exists(db_file):
    os.remove(db_file)

print("-" * 40)

conn = sqlite3.connect(db_file)
print("conn =", conn)

cur = conn.cursor()
print("cur =", cur)

print("-" * 40)

cur.execute('''
CREATE TABLE SALE_ORDER (
  ORDER_ID VARCHAR(9) PRIMARY KEY,
  PURCHASE_DATE DATE,
  CUSTOMER_ID INT,
  CUSTOMER_NAME VARCHAR(10),
  SALE_AMOUNT FLOAT
)
''')

conn.commit()

print("-" * 40)

cur.execute(
    'INSERT INTO SALE_ORDER VALUES ("201601001", "2016-01-01", 1001, "Tom", 10.00)')

insert_sql = 'INSERT INTO SALE_ORDER (ORDER_ID, PURCHASE_DATE, CUSTOMER_ID, CUSTOMER_NAME, SALE_AMOUNT) VALUES (?, ?, ?, ?, ?)'
print("insert_sql =", insert_sql)

cur.execute(insert_sql, ("201601002", "2016-01-01", 1002, "Jerry", 20.0))

more_records = [("201601003", "2016-01-05", 1001, "Tom", 15.0),
                ("201601004", "2016-01-10", 1002, "Jerry", 25.00)]

cur.executemany(insert_sql, more_records)

conn.commit()

print("-" * 40)

cur.execute('SELECT * FROM SALE_ORDER')
rows = cur.fetchall()

print("rows =", rows)

print("-" * 40)

cur.close()
conn.close()

if __name__ == '__main__':
    pass
