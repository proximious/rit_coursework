"""
file: linked_insort.py
author: Alex Iacob
description: homework
"""

import linked_code


def insert(value, lnk):
    """
    Put the value in the proper spot in the linked list to keep it sorted.
    New nodes are created.
    :param value: the value to add to the sequence of values in the list
    :param lnk: the node at the head of the list
    :return: a (partially) new linked list with the value inserted
    :pre: the list headed by lnk is sorted.
    :post: the link returned refers to a list that is sorted.
    """
    if lnk is not None:
        if lnk.value >= value:
            return linked_code.LinkNode(value, lnk)
        elif value > lnk.value:
            return linked_code.LinkNode(lnk.value, insert(value, lnk.rest))
        else:
            raise IndexError("Error of indexing")
    else:
        return linked_code.concatenate(lnk, linked_code.LinkNode(value, None))


def insort(lnk):
    """
    Return a copy of a linked list where all the values are sorted,
    with the lowest value at the head.
    :param lnk: the node at the head of the provided list
    :return: the head node of the sorted linked list
    """
    new_lnk = None
    for idx in range(linked_code.length_iter(lnk)):
        new_lnk = insert(linked_code.value_at(lnk, idx), new_lnk)

    return new_lnk


def pretty_print(lnk):
    """
    Print the contents of a linked list in standard Python format.
    [value, value, value] (Note the spaces.)
    :param lnk: the node at the head of the provided list
    :return: None
    """
    print('[', end="")
    while lnk is not None:
        if lnk.rest is None:
            print(lnk.value, end="")
        else:
            print(lnk.value + ', ', end="")
        lnk = lnk.rest
    print(']')
