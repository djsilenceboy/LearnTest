'''
Created on Apr 4, 2016

@author: djs
'''

import configparser

cfg = configparser.ConfigParser()
cfg.read("../../../etc/SampleConfig.txt")

print("cfg =", cfg)
print("cfg[french] =", cfg["french"])
print("cfg[french][greeting] =", cfg["french"]["greeting"])

print("-" * 40)

if __name__ == '__main__':
    pass
