'''
Created on June 15, 2017

@author: dj
'''

import sqlite3

conn = sqlite3.connect(":memory:")
print("conn =", conn)

print("-" * 40)

conn.execute('''
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

conn.execute(
    'INSERT INTO SALE_ORDER VALUES ("201601001", "2016-01-01", 1001, "Tom", 10.00)')

insert_sql = 'INSERT INTO SALE_ORDER (ORDER_ID, PURCHASE_DATE, CUSTOMER_ID, CUSTOMER_NAME, SALE_AMOUNT) VALUES (?, ?, ?, ?, ?)'
print("insert_sql =", insert_sql)

conn.execute(insert_sql, ("201601002", "2016-01-01", 1002, "Jerry", 20.0))

more_records = [("201601003", "2016-01-05", 1001, "Tom", 15.0),
                ("201601004", "2016-01-10", 1002, "Jerry", 25.00)]

conn.executemany(insert_sql, more_records)

conn.commit()

print("-" * 40)

cur = conn.execute('SELECT * FROM SALE_ORDER')
rows = cur.fetchall()

print("rows =", rows)

print("-" * 40)

conn.close()

if __name__ == '__main__':
    pass
