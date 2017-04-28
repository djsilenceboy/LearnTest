'''
Created on Apr 6, 2016

@author: dj
'''

from flask import Flask, render_template, request

app = Flask(__name__, static_folder='.', static_url_path='')


@app.route('/')
def home():
    message = "Hello, World!"
    print("message =", message)
    return message


@app.route('/index')
def index():
    '''/index'''
    return app.send_static_file("index.html")


@app.route('/echo/<thing>')
def echo(thing):
    '''/echo/<>'''
    message = "This is " + thing + "!"
    print("message =", message)
    return message


@app.route('/display/<thing>')
def display(thing):
    '''/display/<>'''
    print("thing =", thing)
    # "flask1.html" is inside sub-folder "./templates".
    return render_template("flask1.html", thing=thing)


@app.route('/retrieve')
def retrieve():
    '''Get /retrieve?thing=<>'''
    thing = request.args.get('thing')
    print("thing =", thing)
    # "flask1.html" is inside sub-folder "./templates".
    return render_template("flask1.html", thing=thing)

app.run(HOST='localhost', PORT=8002, debug=True)

if __name__ == '__main__':
    pass
