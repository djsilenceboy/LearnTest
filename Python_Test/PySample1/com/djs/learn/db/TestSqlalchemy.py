'''
Created on Apr 5, 2016

@author: djs
'''

import sqlalchemy as sa

conn = sa.create_engine('sqlite://')
print("conn =", conn)

print("-" * 40)

conn.execute('''
CREATE TABLE ZOO (
TYPE VARCHAR(10) PRIMARY KEY,
NAME VARCHAR(10),
AGE INT
)
''')

print("-" * 40)

insert_sql = 'INSERT INTO ZOO (TYPE, NAME, AGE) VALUES (?, ?, ?)'
print("insert_sql =", insert_sql)

conn.execute(insert_sql, 'Cat', 'Tom', 10)
conn.execute(insert_sql, 'Dog', 'Hog', 12)
conn.execute(insert_sql, 'Mouse', 'Jerry', 8)

print("-" * 40)

rows = conn.execute('SELECT * FROM ZOO')
print("rows =", rows)

for row in rows:
    print(row)

print("-" * 40)


if __name__ == '__main__':
    pass
