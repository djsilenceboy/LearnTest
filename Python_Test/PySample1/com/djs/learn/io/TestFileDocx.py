'''
Created on Jul 20, 2017

@author: dj
'''

from io import BytesIO
from os import path
from zipfile import ZipFile


input_file_path = "../../../../etc"

print("-" * 40)

input_file = path.join(input_file_path, "SampleDoc.docx")
print("Input file name:", input_file)

docx_file = open(input_file, "rb")
docx_file = BytesIO(docx_file.read())

document = ZipFile(docx_file)
content = document.read("word/document.xml")

docx_file.close()

print("-" * 40)

print(content)

print("-" * 40)

if __name__ == '__main__':
    pass
