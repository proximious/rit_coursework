"""
file: test_link_sort.py
author: Alex Iacob
description: tester for functions in linked_insort.py
"""

import linked_insort

import linked_code


def read_file(filename):
    """
       Open a file of containing one integer per line,
       prepend each integer to a linked sequence,
       and return the reverse of the sequence.
       :param filename: A string containing the name of the file
       :return: A linked list of the numbers in the file, ordered
                identically to how they are ordered in the file
    """
    lst = []
    with open(filename, "r")as file:
        for line in file:
            integers = line.strip()
            lst.append(integers)

        linked_lst = linked_code.mk_linked_list_rec(lst)

    return linked_lst


def main():
    """
       Read file, build sequence, print it, sort it, print result, and
       pretty-print both lists.
    """

    name = input("Enter file name: ")
    if name == "":
        return

    original_s = read_file(name)
    print("unsorted:", original_s)

    sorted_s = linked_insort.insort(original_s)

    print("sorted:", sorted_s)

    print("pretty printed original: ", end="")
    linked_insort.pretty_print(original_s)
    print("pretty printed sorted: ", end="")
    linked_insort.pretty_print(sorted_s)


if __name__ == "__main__":
    main()
