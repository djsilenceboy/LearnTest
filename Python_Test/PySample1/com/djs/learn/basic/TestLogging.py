'''
Created on Apr 8, 2016

@author: dj
'''

import logging

fmt = "<%(asctime)s>[%(levelname)s](%(module)s:%(funcName)s:%(lineno)d){%(threadName)s:%(thread)d} %(message)s"
# Default level is ERROR.
# logging.basicConfig(level="DEBUG")
logging.basicConfig(
    level=logging.DEBUG, format=fmt, filename='../../../etc/SampleLog.txt')

logging.info("Start running...")
logging.debug("This is a cat.")
logging.error("Got an exception.")
logging.log(logging.INFO, "Stop running.")

print("-" * 40)

local_logger = logging.getLogger("MyTest")

# With this line, log output to both console and log.
# Without this line, log only output to console in WARNING level.
local_logger.addHandler(logging.StreamHandler())

local_logger.info("Start running...")
local_logger.debug("This is a cat.")
local_logger.error("Got an exception.")
local_logger.log(logging.INFO, "Stop running.")

print("-" * 40)


def some_logging():
    local_logger.info("Start running...")
    local_logger.debug("This is a cat.")
    local_logger.error("Got an exception.")
    local_logger.log(logging.INFO, "Stop running.")


some_logging()

print("-" * 40)

if __name__ == '__main__':
    pass
