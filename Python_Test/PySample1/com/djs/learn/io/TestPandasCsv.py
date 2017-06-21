'''
Created on Jun 18, 2017

@author: dj
'''

from os import path
import pandas as pd

input_file_path = "../../../../etc"
output_file_path = "../../../../Temp"


data_frame = pd.read_csv(path.join(input_file_path, "SampleInput.csv"))
print(data_frame)

print("-" * 40)

data_frame_column_by_index = data_frame.iloc[:, [1]]
print(data_frame_column_by_index)

print("-" * 40)

data_frame_column_by_name = data_frame.loc[:, ["Type"]]
print(data_frame_column_by_name)

print("-" * 40)

data_frame_remove_row = data_frame.drop([1])
print(data_frame_remove_row)

print("-" * 40)

data_frame.to_csv(path.join(output_file_path,
                            "SampleOutput_Pandas.csv"), index=False)

print("-" * 40)

header_list = ["New Type", "New Name"]
data_frame_2 = pd.read_csv(
    path.join(input_file_path, "SampleInput.csv"), header=None, names=header_list)
print(data_frame_2)

print("-" * 40)

if __name__ == '__main__':
    pass
