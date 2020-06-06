'''
Stock info records helper.

Read records from a csv file. The file should have a header line.
Line format: Company,Exchange,Ticker

Update log: (date / version / author : comments)
2017-08-19 / 1.0.0 / Du Jiang : Creation
'''

from com.djs.learn.common import LoggingHelper
from com.djs.learn.common.CsvRecordsHelper import CsvRecordsHelper

FIELD_IDX_COMPANY = 0
FIELD_IDX_EXCHANGE = 1
FIELD_IDX_TICKER = 2

FIELD_NAME_COMPANY = "Company"
FIELD_NAME_EXCHANGE = "Exchange"
FIELD_NAME_TICKER = "Ticker"


class StockInfoRecordsHelper(CsvRecordsHelper):
    '''
    Manage host records.
    '''

    __logger = LoggingHelper.get_logger("StockInfoRecordsHelper")

    def __init__(self, csv_filename, use_dict = False):
        '''
        @param csv_filename: CSV file.
        @param use_dict: Boolean, whether to open CSV as dict. True: read records as a list of dict; False: read records as a list of list.
        '''
        super().__init__(csv_filename, use_dict)

        self.__logger = StockInfoRecordsHelper.__logger
        self.__logger.debug("locals() = %s", locals())

    def find_by_ticker(self, ticker):
        '''
        Find record by ticker.

        @param ticker
        @return: Record, or None if not found.
        '''
        record = None

        # If hosts list is not empty.
        if self._records:
            # Check each item, and find matching one.
            for item in self._records:
                if self._use_dict:
                    if item[self._headers[FIELD_IDX_TICKER]] == ticker:
                        record = item
                        break
                else:
                    if item[FIELD_IDX_TICKER] == ticker:
                        record = item
                        break
        return record
