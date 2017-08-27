'''
Check stock info.

Update log: (date / version / author : comments)
2017-08-26 / 1.0.0 / Du Jiang : Creation
'''

from concurrent.futures import ThreadPoolExecutor
import requests

from com.djs.learn.common import LoggingHelper
from com.djs.learn.files import FinanceApiKeyRecordsHelper
from com.djs.learn.files import StockInfoRecordsHelper


class Constants(object):
    '''
    Use a class to keep constant variables.
    '''

    PROVIDER_ALPHA_VANTAGE = "Alpha Vantage"


class CheckStockInfo(object):
    '''
    Check stock info.
    '''

    __logger = LoggingHelper.get_logger("CheckStockInfo")

    def __init__(self, stock_info_filename, finance_apikey_filename, output_filename=None, concurrent_max_workers=5):
        '''
        @param stock_info_filename: CSV file name for stock info.
        @param finance_apikey_filename: CSV file name for finance API key.
        @param output_filename:  Output file name for results in CSV format. Optional, default is None.
        @param concurrent_max_workers: Concurrent max workers to process records. Optional, default is 5. Must >= 1.
        '''
        self.__stock_info_filename = stock_info_filename
        self.__finance_apikey_filename = finance_apikey_filename
        self.__output_filename = output_filename
        self.__concurrent_max_workers = concurrent_max_workers

        self.__stock_info_records_helper = None
        self.__stock_info_headers = None
        self.__stock_info_records = None

        self.__finance_apikey_records_helper = None
        self.__finance_apikey_headers = None
        self.__finance_apikey_records = None

        self.__logger = CheckStockInfo.__logger
        self.__logger.debug("locals() = %s", locals())

    def retrieve(self):
        '''
        Retrieve records from files.

        @raise exception: If reading file failed. 
        '''
        try:
            self.__logger.info("Retrieve stock info records from csv file...")
            self.__stock_info_records_helper = StockInfoRecordsHelper.StockInfoRecordsHelper(
                self.__stock_info_filename, use_dict=True)
            self.__stock_info_headers, self.__stock_info_records = self.__stock_info_records_helper.retrieve()

            # If any of them is empty, no meaning to continue.
            if (not self.__stock_info_headers) or (not self.__stock_info_records):
                raise ValueError("Invalid stock info file.")

            self.__logger.info(
                "Retrieve finance API key records from csv file...")
            self.__finance_apikey_records_helper = FinanceApiKeyRecordsHelper.FinanceApiKeyRecordsHelper(
                self.__finance_apikey_filename, use_dict=True)
            # Retrieve hosts list from file.
            self.__finance_apikey_headers, self.__finance_apikey_records = self.__finance_apikey_records_helper.retrieve()

            # If any of them is empty, no meaning to continue.
            if (not self.__finance_apikey_headers) or (not self.__finance_apikey_records):
                raise ValueError("Invalid finance API key file.")
        except Exception as e:
            self.__logger.error("Retrieve failed: %s", repr(e))
            self.__stock_info_records_helper = None
            self.__finance_apikey_records_helper = None
            raise e

    def __call_alpha_vantage(self, record):
        '''
        Get info by Alpha Vantage API.

        @param record: A dict of stock info. 
        @return: A dict of result. 
        @raise exception: If failed. 
        '''

        self.__logger.info("Call Alpha Vantage:", record)
        result = None
        try:
            symbol = None
            if record[StockInfoRecordsHelper.FIELD_IDX_EXCHANGE]:
                symbol = record[StockInfoRecordsHelper.FIELD_IDX_EXCHANGE] + ":"
            symbol += record[StockInfoRecordsHelper.FIELD_IDX_TICKER]
            self.__logger.debug("symbol = %s", symbol)

            api_key = self.__finance_apikey_records_helper.find_by_provider(
                Constants.PROVIDER_ALPHA_VANTAGE)
            url_format = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol={0}&apikey=" + api_key
            # self.__logger.debug("url_format = %s", url_format)

            url = url_format.format(symbol)
            self.__logger.debug("url = %s", url)

            resp = requests.get(url)
            self.__logger.debug("resp.status_code = %s", resp.status_code)
            result = resp.text
        except Exception as e:
            self.__logger.error(
                "Get info by Alpha Vantage API failed: %s", repr(e))
            raise e

        return result

    def __process_record(self, record):
        '''
        Process one record.

        @param record: A dict of stock info. 
        @return: A dict of results.
        @raise exception: If processing failed. 
        '''

        self.__logger.info("Process record:", record)
        results = {}
        try:
            results["Goggle"] = None
        except Exception as e:
            self.__logger.error("Process record %s failed: %s",
                                record[StockInfoRecordsHelper.FIELD_NAME_TICKER], repr(e))
            raise e

        return results

    def process_records(self):
        '''
        Process records.

        @raise exception: If processing failed. 
        '''
        try:
            self.__logger.info("Process records...")

            with ThreadPoolExecutor(max_workers=self.__concurrent_max_workers) as executor:
                # Wait for result to return.
                for record, result in zip(self.__stock_info_records, executor.map(self.__process_record, self.__stock_info_records)):
                    record["Results"] = result

            print("stock_info_records =", self.__stock_info_records)
        except Exception as e:
            self.__logger.error("Process records failed: %s", repr(e))
            raise e
