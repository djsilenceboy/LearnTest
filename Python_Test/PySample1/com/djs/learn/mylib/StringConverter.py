'''
Created on Apr 9, 2017

@author: dj
'''


def to_str(bytes_or_str):
    '''
    Convert input bytes / string to string.
    '''
    if isinstance(bytes_or_str, bytes):
        value = bytes_or_str.decode('utf-8')
    else:
        value = bytes_or_str
    return value


def to_bytes(bytes_or_str):
    '''
    Convert input bytes / string to bytes.
    '''
    if isinstance(bytes_or_str, bytes):
        value = bytes_or_str.encode('utf-8')
    else:
        value = bytes_or_str
    return value


if __name__ == '__main__':
    pass
