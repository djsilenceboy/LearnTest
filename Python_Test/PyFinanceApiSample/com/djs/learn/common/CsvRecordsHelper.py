'''
CSV records helper.

Read records from a CSV file. The file should have a header line.

Update log: (date / version / author : comments)
2016-05-05 / 1.0.0 / Du Jiang : Creation
2017-07-01 / 1.1.0 / Du Jiang : Add use_dict
'''

import csv
from com.djs.learn.common import LoggingHelper


class CsvRecordsHelper(object):
    '''
    Manage connection records.
    '''

    __logger = LoggingHelper.get_logger("CsvRecordsHelper")

    def __init__(self, csv_file_path, use_dict = False):
        '''
        @param csv_file_path: A string of CSV file path.
        @param use_dict: Boolean, whether to open CSV as dict. True: read records as a list of dict; False: read records as a list of list.
        '''
        self._csv_file_path = csv_file_path
        self._use_dict = use_dict
        self._headers = None
        self._records = None

        self.__logger = CsvRecordsHelper.__logger
        self.__logger.debug("locals() = %s", locals())

    def retrieve(self):
        '''
        Retrieve records from file.

        @return: record dict list.
        @raise exception: If reading file failed.
        '''
        if not self._records:
            try:
                # Open file with auto-close.
                with open(self._csv_file_path, "r") as file:
                    self.__logger.info("CSV file = %s", file)

                    if self._use_dict:
                        # Read file as dict.
                        reader = csv.DictReader(file)
                        # Read header line.
                        self._headers = reader.fieldnames
                        # Read records into a list of dict.
                        self._records = [line for line in reader]
                    else:
                        # Read file as list.
                        reader = csv.reader(file)
                        # Read records into a list of list.
                        self._records = [line for line in reader]
                        # Read header line, the first record.
                        # Also remove it from the rest of records.
                        self._headers = self._records.pop(0)

                    self.__logger.debug(
                        "Headers (%s) = %s", len(self._headers), self._headers)
                    record_counter = len(self._records)
                    if record_counter <= 5:
                        self.__logger.debug(
                            "Records (%s) = %s", record_counter, self._records)
                    else:
                        self.__logger.debug(
                            "Records (%s) = %s......", record_counter, self._records[:5])
            except Exception as e:
                self.__logger.error("Retrieve failed: %s", repr(e))
                raise Exception(repr(e)) from e

        return self._headers, self._records

    @property
    def use_dict(self):
        '''
        Get flag: use dict.

        @return: Boolean, whether to open CSV as dict. True: read records as a list of dict; False: read records as a list of list.
        '''
        return self._use_dict

    @property
    def headers(self):
        '''
        Get headers.

        @return: Header field list.
        '''
        return self._headers

    @property
    def records(self):
        '''
        Get records.

        @return: Record list.
        '''
        return self._records
