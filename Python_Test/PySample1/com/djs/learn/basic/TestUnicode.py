'''
Created on Apr 6, 2016

@author: jiangdu
'''

import sys
import unicodedata


def unicode_info(value):
    name = unicodedata.name(value)
    value2 = unicodedata.lookup(name)
    print("value = '{}', name = '{}', value2 = '{}'".format(
        value, name, value2))


print("-" * 40)

unicode_info('A')
unicode_info('$')

# Make sure the file is saved as UTF-8.
unicode_info('\u00a2')
unicode_info('\u20ac')
unicode_info('\u2603')

print("-" * 40)

unichar1 = '\u2603'
print("unichar1 =", unichar1)

unibytes1 = unichar1.encode('utf_8')
print("unibytes1 =", unibytes1)

unibytes2 = unichar1.encode('ascii', 'replace')
print("unibytes2 =", unibytes2)

unichar2 = unibytes1.decode('utf_8')
print("unichar2 =", unichar2)

if __name__ == '__main__':
    pass
