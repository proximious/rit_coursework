"""
file: lengths.py
author: bksteele in lecture
description: multiple solutions to length(st) and longest(st)
"""

def length_r( st, size=0):
    """
    tail recursive length of a string
    """
    if st == "":
        return size
    else:
        return length_r( st[1:], size+1)

def length_i( st):
    """
    iterative length of string using for
    """
    size = 0
    for _ in st:
        size += 1
    return size

def length_w( st):
    """
    while-based length
    """
    size = 0
    while st != "":
        size += 1
        st = st[1:]
    return size

# set global to choose which length function to use
length = length_i

def longest( st):
    """
    return length of longest word in the string
    note: this has a bug. find and fix it
    """
    biggest = 0
    size = 0
    for ch in st:
        if ch != ' ' and ch != '\t' and ch != '\n':
            size += 1
        else:
            size = 0
        if size > biggest:
            biggest = size
    return biggest



