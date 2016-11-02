'''
Created on Nov 2, 2016

@author: dj
'''
import argparse


def main():
    parser = argparse.ArgumentParser()

    parser.add_argument(
        "-q", "--quiet", action="store_true", dest="quiet", help="Suppress output.")
    parser.add_argument(
        "-i", "--info", default="Hello", dest="info", help="Some info.")
    parser.add_argument(
        "-f", "--fruit", default="Apple", dest="fruit", help="A fruit.")

    args = parser.parse_args()

    print("Args =", args)

if __name__ == '__main__':
    main()
