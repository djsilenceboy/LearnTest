'''
Created on Apr 6, 2016

@author: dj
'''

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

print("-" * 40)

# Default is unicode string.
text_str = 'This \/ is a good day. 月球'
print("text_str =", text_str)
print("type =", type(text_str))
print("len =", len(text_str))

print("-" * 40)

# u means "unicode".
# Same as normal string.
u_text_str = u'This \/ is a good day. 月球'
print("u_text_str =", u_text_str)
print("type =", type(u_text_str))
print("len =", len(u_text_str))

print("-" * 40)

# r means "raw", and will not escape special characters.
r_text_str = r'This \/ is a good day. 月球'
print("r_text_str =", r_text_str)
print("type =", type(r_text_str))
print("len =", len(r_text_str))

print("-" * 40)

# "\" will be "\\".
byte_str = b'This \/ is a good day.'
print("byte_str =", byte_str)
print("type =", type(byte_str))
print("len =", len(byte_str))

print("-" * 40)

# "\" will be "\\".
# Same as (encoding='utf_8', errors='strict')
text_str2 = text_str.encode("utf_8")
print("text_str2 =", text_str2)
print("type =", type(text_str2))
print("len =", len(text_str2))

print("-" * 40)

text_str3 = text_str2.decode("utf_8")
print("text_str3 =", text_str3)
print("type =", type(text_str3))
print("len =", len(text_str3))

print("-" * 40)

text_str4 = text_str2.decode("ascii", "replace")
print("text_str4 =", text_str4)
print("type =", type(text_str4))
print("len =", len(text_str4))

print("-" * 40)

if __name__ == '__main__':
    pass
