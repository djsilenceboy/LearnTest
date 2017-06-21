'''
Created on Jun 14, 2017

@author: dj
'''

from datetime import date
from os import path
from xlrd import open_workbook, xldate_as_tuple
from xlwt import Workbook

input_file_path = "../../../../etc"
output_file_path = "../../../../Temp"

print("-" * 40)

input_file = path.join(input_file_path, "SampleInput_2016.xlsx")
print("Input file name:", input_file)

with open_workbook(input_file) as input_workbook:
    print("Input worksheets number:", input_workbook.nsheets)
    print("{0:20}{1:10}{2:10}".format("Worksheet name", "Rows", "Columns"))
    for worksheet in input_workbook.sheets():
        print("{0:20}{1:<10}{2:<10}".format(
            worksheet.name, worksheet.nrows, worksheet.ncols))

    print("-" * 40)

    input_worksheet = input_workbook.sheet_by_name('January_2016')
    print("Input worksheet:", input_worksheet)

    output_workbook = Workbook()
    output_worksheet = output_workbook.add_sheet('Jan_2016')
    print("Output worksheet:", output_worksheet)

    # Write each cell to new worksheet.
    for row_index in range(input_worksheet.nrows):
        for column_index in range(input_worksheet.ncols):
            cell_value = input_worksheet.cell_value(row_index, column_index)
            # Check whether the cell is a Date value.
            if input_worksheet.cell_type(row_index, column_index) == 3:
                date_cell = xldate_as_tuple(
                    cell_value, input_workbook.datemode)
                # date_cell is a tuple (year, month, day,...). [0:3] means
                # (year, month, day) part. Convert date into a string by strftime().
                date_cell = date(*date_cell[0:3]).strftime('%Y-%m-%d')
                output_worksheet.write(row_index, column_index, date_cell)
            else:
                # Other cells.
                output_worksheet.write(
                    row_index, column_index, cell_value)

print("-" * 40)

# Output file format is "xls", not "xlsx.
output_file = path.join(output_file_path, "SampleOutput.xls")
print("Output file name:", output_file)
output_workbook.save(output_file)

print("-" * 40)

if __name__ == '__main__':
    pass
