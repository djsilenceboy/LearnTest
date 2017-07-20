'''
Created on Jul 20, 2017

@author: dj
'''

from io import StringIO, open
from os import path
from pdfminer.converter import TextConverter
from pdfminer.layout import LAParams
from pdfminer.pdfinterp import PDFResourceManager, process_pdf


input_file_path = "../../../../etc"

print("-" * 40)

input_file = path.join(input_file_path, "SampleDoc.pdf")
print("Input file name:", input_file)

res_mgr = PDFResourceManager()
raw = StringIO()
laparams = LAParams()
device = TextConverter(res_mgr, raw, laparams=laparams)

pdf_file = open(input_file, "rb")
process_pdf(res_mgr, device, pdf_file)
device.close()
pdf_file.close()
content = raw.getvalue()
raw.close()

print("-" * 40)

print(content)

print("-" * 40)

if __name__ == '__main__':
    pass
