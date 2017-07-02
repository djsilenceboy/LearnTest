'''
Logging helper/wrapper.

Update log: (date / version / author : comments)
2016-05-04 / 1.0.0 / Du Jiang : Creation
'''

import logging

# Set default format.
__default_log_format = "<%(asctime)s>[%(levelname)s](%(module)s:%(funcName)s:%(lineno)d){%(threadName)s:%(thread)d} %(message)s"


def set_logging(log_filename, log_level=logging.DEBUG, log_format=__default_log_format, log_filemode='w'):
    '''
    Set default properties.
    "logging.basicConfig" should only be called once!
    Following settings do not have effect.

    @param log_filename: Log file name.
    @param log_level: Log level. Default is DEBUG.
    @param logging_format: Log format. Default is
        <%(asctime)s>[%(levelname)s](%(module)s:%(funcName)s:%(lineno)d){%(threadName)s:%(thread)d} %(message)s
    @param log_filemode: Log file mode. Default is overwrite.
    '''
    logging.basicConfig(
        level=log_level, format=log_format, filename=log_filename, filemode=log_filemode)


def get_logger(log_name=None):
    '''
    Get a logger, which will output to both log file and console.

    @param log_name: Default is None, to return root logger.
    '''
    logger = logging.getLogger(log_name)
    logger.addHandler(logging.StreamHandler())
    return logger
