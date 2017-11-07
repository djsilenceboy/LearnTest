'''
Convert Google finance currency data helper: From JSON to CSV.

Update log: (date / version / author : comments)
2017-11-07 / 1.0.0 / Du Jiang : Creation
'''


from com.djs.learn.dataformat import ConvertGoogleFinanceCurrencyData1

__base_file_name = "CurrencyData"
__input_file_path = "../../../../Temp/{0}.json".format(__base_file_name)
__output_file_path = "../../../../Temp/{0}.csv".format(__base_file_name)

# Test usage.
# argv = []
# argv = ["-h"]

# Test wrong.
# argv = ["-b"]
# argv = ["-i"]

# Test correct.
# argv = ["-i", __input_file_path]
argv = ["-i", __input_file_path, "-o", __output_file_path]
ConvertGoogleFinanceCurrencyData1.main(argv)


'''
Or run:

python ConvertGoogleFinanceCurrencyData1.py -i "../../../../Temp/CurrencyData.json" -o "../../../../Temp/CurrencyData.csv" 
'''

if __name__ == '__main__':
    pass
