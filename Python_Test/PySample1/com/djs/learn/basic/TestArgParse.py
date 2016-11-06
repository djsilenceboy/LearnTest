'''
Created on Nov 2, 2016

@author: dj
'''
import argparse
from random import choice


def main():
    # By default, there is pre-defined "-h" option.
    parser = argparse.ArgumentParser()

    # "store_true" means a boolean with default value is true.
    # "Not define" means false.
    parser.add_argument(
        "-q", "--quiet", action="store_true", dest="quiet", help="Suppress output.")

    # "store_false" means a boolean with default value is false.
    # "Not define" means true.
    parser.add_argument(
        "-e", "--erase", action="store_false", dest="erase", help="Erase something.")

    parser.add_argument(
        "-i", "--info", default="Hello", dest="info", help="Some info.")

    parser.add_argument(
        "-f", "--fruit", default="Apple", dest="fruit", help="A fruit.")

    parser.add_argument(
        "-t", "--toy", choices=("bell", "poke", "watch"), default="poke",  dest="toy", help="Toy.")

    parser.add_argument(
        "-p", "--pair", default=["Tom", "Jerry"], dest="pair", help="A pair of friends.")

    print("Parser =", parser)

    # If there is "-h" option, all other options will be suppressed.
    # The app shows usage and quit after this line.
    args = parser.parse_args()

    print("Args =", args)

if __name__ == '__main__':
    main()
