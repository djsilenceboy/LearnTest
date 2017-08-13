'''
Convert Fundsupermart fund data helper: From JSON to CSV.

Update log: (date / version / author : comments)
2017-08-13 / 1.0.0 / Du Jiang : Creation
'''


from com.djs.learn.dataformat import ConvertFundsupermartFundData1

__input_file_path = "../../../../Temp/Fundsupermart_FundData.json"
__output_file_path = "../../../../Temp/Fundsupermart_FundData_1.csv"

# Test usage.
# argv = []
# argv = ["-h"]

# Test wrong.
# argv = ["-b"]
# argv = ["-i"]

# Test correct.
# argv = ["-i", __input_file_path]
argv = ["-i", __input_file_path, "-o", __output_file_path]
ConvertFundsupermartFundData1.main(argv)


'''
Or run:

python ConvertFundsupermartFundData1.py -i "../../../../Temp/Fundsupermart_FundData.json" -o "../../../../Temp/Fundsupermart_FundData_1.csv" 
'''

if __name__ == '__main__':
    pass
