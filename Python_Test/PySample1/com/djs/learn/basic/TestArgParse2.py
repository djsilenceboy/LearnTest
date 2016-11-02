'''
Created on Nov 2, 2016

@author: dj
'''
import argparse


def main():
    parser = argparse.ArgumentParser(prefix_chars="/")

    parser.add_argument(
        "/q", "//quiet", action="store_true", dest="quiet", help="Suppress output.")
    parser.add_argument(
        "/i", "//info", action="store_true", dest="info", help="Some info.")

    args = parser.parse_args()

    print("Args =", args)

if __name__ == '__main__':
    main()
