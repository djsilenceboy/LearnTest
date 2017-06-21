'''
Created on Jun 21, 2017

@author: dj
'''

from os import path
import pandas as pd

input_file_path = "../../../../etc"
output_file_path = "../../../../Temp"

# It will call xlrd internally.
data_frame = pd.read_excel(
    path.join(input_file_path, "SampleInput_2016.xlsx"), sheetname="January_2016")

print(data_frame)

print("-" * 40)

data_frame_column_by_index = data_frame.iloc[:, [1, 3]]
print(data_frame_column_by_index)

print("-" * 40)

data_frame_column_by_name = data_frame.loc[:, ["Order ID", "Customer ID"]]
print(data_frame_column_by_name)

print("-" * 40)

data_frame_remove_row = data_frame.drop([1, 3])
print(data_frame_remove_row)

print("-" * 40)

names = ["Jerry", "John"]
data_frame_value_in_set = data_frame[data_frame["Customer Name"].isin(names)]
print(data_frame_value_in_set)

print("-" * 40)

data_frame_value_matches_pattern = data_frame[data_frame["Customer Name"].str.startswith(
    "J")]
print(data_frame_value_matches_pattern)

print("-" * 40)

data_frame_value_meets_condition = data_frame[data_frame['Sale Amount'].astype(
    int) > 15]
print(data_frame_value_meets_condition)

print("-" * 40)

writer = pd.ExcelWriter(path.join(output_file_path, "SampleOutput_2016.xls"))
data_frame.to_excel(writer, sheet_name='January_2013_New', index=False)
writer.save()

print("-" * 40)

if __name__ == '__main__':
    pass
