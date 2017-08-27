'''
Finance API key records helper.

Read records from a csv file. The file should have a header line.
Line format: Provider,API key

Update log: (date / version / author : comments)
2017-08-19 / 1.0.0 / Du Jiang : Creation
'''

from com.djs.learn.common import LoggingHelper
from com.djs.learn.common.CsvRecordsHelper import CsvRecordsHelper


FIELD_IDX_PROVIDER = 0
FIELD_IDX_APIKEY = 1

FIELD_NAME_PROVIDER = "Provider"
FIELD_NAME_APIKEY = "API key"


class FinanceApiKeyRecordsHelper(CsvRecordsHelper):
    '''
    Manage host records.
    '''

    __logger = LoggingHelper.get_logger("FinanceApiKeyRecordsHelper")

    def __init__(self, csv_filename, use_dict=False):
        '''
        @param csv_filename: CSV file.
        @param use_dict: Boolean, whether to open CSV as dict. True: read records as a list of dict; False: read records as a list of list.
        '''
        super().__init__(csv_filename, use_dict)

        self.__logger = FinanceApiKeyRecordsHelper.__logger
        self.__logger.debug("locals() = %s", locals())

    def find_by_provider(self, provider):
        '''
        Find record by provider.

        @param provider
        @return: Record, or None if not found.
        '''
        record = None

        # If hosts list is not empty.
        if self._records:
            # Check each item, and find matching one.
            for item in self._records:
                if self._use_dict:
                    if item[self._hearders[FIELD_IDX_PROVIDER]] == provider:
                        record = item
                        break
                else:
                    if item[FIELD_IDX_PROVIDER] == provider:
                        record = item
                        break
        return record
