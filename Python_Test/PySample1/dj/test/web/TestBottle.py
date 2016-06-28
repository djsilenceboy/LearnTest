'''
Created on Apr 6, 2016

@author: jiangdu
'''

from bottle import route, run


@route('/')
def home():
    message = "Hello, World!"
    print("message =", message)
    return message


@route('/echo/<thing>')
def echo(thing):
    message = "This is " + thing + "!"
    print("message =", message)
    return message

run(HOST='localhost', PORT=8002)

if __name__ == '__main__':
    pass
