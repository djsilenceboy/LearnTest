'''
Created on Oct 26, 2016

@author: dj
'''

import re


def make_regex(pattern, flags=0):
    print("pattern =", pattern)
    print("flags =", flags)
    regex = re.compile(pattern, flags)
    return regex


def search(regex, source):
    print("source =", source)
    match = regex.search(source)
    print("match =", match)
    if match:
        print("group =", match.group())
        print("groups =", match.groups())
        print("groupdict =", match.groupdict())


def findall(regex, source):
    print("source =", source)
    match = regex.findall(source)
    print("match =", match)


def sub(regex, newform, source):
    print("newform =", newform)
    print("source =", source)
    match = regex.sub(newform, source)
    print("match =", match)


def main():
    print("-" * 40)

    regex = make_regex(r"good")
    search(regex, "This good is a very good day.")
    print("-" * 20)
    findall(regex, "This good is a very good day.")

    print("-" * 40)

    regex = make_regex(r"[Gg]ood")
    findall(regex, "This Good is gOod and good.")

    print("-" * 40)

    regex = make_regex(r"[A-Z]{3,4}")
    search(regex, "ABCDE")
    print("-" * 20)
    search(regex, "ABcde")

    print("-" * 40)

    regex = make_regex(r"[A-Z]{3,4}?")
    search(regex, "ABCDE")

    print("-" * 40)

    regex = make_regex(r"[^A-Z]+")
    search(regex, "abc")
    print("-" * 20)
    search(regex, "Abc")

    print("-" * 40)

    regex = make_regex(r"[\w]+")
    findall(regex, "abc.ABC 123= _-")

    print("-" * 40)

    regex = make_regex(r"[\W]+")
    findall(regex, "abc.ABC 123= _-")

    print("-" * 40)

    regex = make_regex(r"[\d]+")
    findall(regex, "abc.ABC 123= _-")

    print("-" * 40)

    regex = make_regex(r"[\D]+")
    findall(regex, "abc.ABC 123= _-")

    print("-" * 40)

    regex = make_regex(r"\bWord\b")
    search(regex, "Word")
    search(regex, " Word")
    search(regex, " 1Word")
    search(regex, "Word ")
    search(regex, "Word2 ")

    print("-" * 40)

    regex = make_regex(r"^Word")
    search(regex, "Word")
    search(regex, " Word")

    print("-" * 40)

    regex = make_regex(r"Word$")
    search(regex, "Word")
    search(regex, "Word ")

    print("-" * 40)

    # Groups.
    regex = make_regex(r"([\w]+).([\w]+)")
    search(regex, "abc.ABC 123= _-")

    print("-" * 40)

    # Reference existing groups.
    regex = make_regex(r"<([\w]+)>.*</\1>")
    search(regex, "<foo>Hello</foo>")
    search(regex, "<foo>Hello</bar>")

    print("-" * 40)

    # Reference existing groups.
    regex = make_regex(r"<([\w]+)>.*</\1><([\w]+)>.*</\2>")
    search(regex, "<foo>Hello</foo><bar>Word</bar>")

    print("-" * 40)

    # Named groups.
    regex = make_regex(r"(?P<First>[\w]+).(?P<Second>[\d]+)")
    search(regex, "abc.123 xxx")

    print("-" * 40)

    # Lookahead.
    regex = make_regex(r"He(?=ll)")
    search(regex, "Hello")
    search(regex, "Helo")

    print("-" * 40)

    # Ignore case.
    regex = make_regex(r"[a-z]+", re.IGNORECASE)
    findall(regex, "abc ABC")

    print("-" * 40)

    # "." does not match new line.
    regex = make_regex(r".+")
    findall(regex, "abc\nABC")
    print("-" * 20)
    # "." matches new line.
    regex = make_regex(r".+", re.DOTALL)
    findall(regex, "abc\nABC")

    print("-" * 40)

    # Match does not consider multiple lines.
    regex = make_regex(r"^ABC")
    findall(regex, "abc\nABC")
    print("-" * 20)
    # Match considers multiple lines.
    regex = make_regex(r"^ABC", re.MULTILINE)
    findall(regex, "abc\nABC")

    print("-" * 40)

    # Verbose mode.
    regex = make_regex(r"""(?P<First>[\w]+) # First.
    . # A dot.
    (?P<Second>[\d]+) # Second.
    """, re.VERBOSE)
    search(regex, "abc.123 xxx")

    print("-" * 40)

    # Substitution.
    regex = make_regex(r"([\w]+).([\d]+)")
    sub(regex, r"(\2)-(\1)", "abc.123 xxx")

    print("-" * 40)

if __name__ == '__main__':
    main()
