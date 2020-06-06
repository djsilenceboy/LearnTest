'''
Fundsupermart records helper.

Read records from a csv file. The file should have a header line.
Line format: Fund name,Fund ID

Update log: (date / version / author : comments)
2017-08-06 / 1.0.0 / Du Jiang : Creation
'''

from com.djs.learn.common import LoggingHelper
from com.djs.learn.common.CsvRecordsHelper import CsvRecordsHelper

FIELD_IDX_FUND_NAME = 0
FIELD_IDX_FUND_ID = 1


class FundsupermartRecordsHelper(CsvRecordsHelper):
    '''
    Manage host records.
    '''

    __logger = LoggingHelper.get_logger("FundsupermartRecordsHelper")

    def __init__(self, csv_filename):
        '''
        @param csv_filename: CSV file.
        '''
        super().__init__(csv_filename)

        self.__logger = FundsupermartRecordsHelper.__logger
        self.__logger.debug("locals() = %s", locals())

    def find_by_fund_id(self, fund_id):
        '''
        Find record by fund ID.

        @param fund_id
        @return: Record, or None if not found.
        '''
        record = None

        # If hosts list is not empty.
        if self._records:
            # Check each item, and find matching one.
            for item in self._records:
                if self._use_dict:
                    if item[self._headers[FIELD_IDX_FUND_ID]] == fund_id:
                        record = item
                        break
                else:
                    if item[FIELD_IDX_FUND_ID] == fund_id:
                        record = item
                        break
        return record
