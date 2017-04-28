'''
Created on Apr 23, 2016

@author: dj
'''

import mysql.connector as ms


try:
    context = ms.connect(user='root', password='admin',
                         host='192.168.0.36',
                         database='test')
    print("context =", context)

    cursor = context.cursor()
    print("cursor =", cursor)

    query = ("SELECT * FROM STUDENT WHERE (NAME = %s)")
    print("query =", query)

    cursor.execute(query, ('Tom',))
    print("cursor =", cursor)

    row = cursor.fetchone()
    while row is not None:
        print(row)
        row = cursor.fetchone()
except ms.Error as err:
    if err.errno == ms.errorcode.ER_ACCESS_DENIED_ERROR:
        print("Error: Wrong user name or password!")
    elif err.errno == ms.errorcode.ER_BAD_DB_ERROR:
        print("Error: Database does not exist!")
    else:
        print("Error:", err)
finally:
    cursor.close()
    print("cursor closed.")
    context.close()
    print("context closed.")

print("-" * 40)

config = {
    'user': 'root',
    'password': 'admin',
    'host': '192.168.0.36',
    'database': 'test',
    'raise_on_warnings': True,
}

try:
    context2 = ms.connect(**config)
    print("context2 =", context2)

    cursor2 = context2.cursor()
    print("cursor2 =", cursor2)

    query = ("SELECT * FROM STUDENT WHERE (GENDER = %s)")
    print("query =", query)

    cursor2.execute(query, ("F",))
    print("cursor =", cursor2)

    for row in cursor2:
        print(row)
except ms.Error as err:
    if err.errno == ms.errorcode.ER_ACCESS_DENIED_ERROR:
        print("Error: Wrong user name or password!")
    elif err.errno == ms.errorcode.ER_BAD_DB_ERROR:
        print("Error: Database does not exist!")
    else:
        print("Error:", err)
finally:
    cursor2.close()
    print("cursor2 closed.")
    context2.close()
    print("context2 closed.")


if __name__ == '__main__':
    pass
