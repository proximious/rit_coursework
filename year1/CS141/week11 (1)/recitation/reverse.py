"""
CSCI-141 Computer Science 1 Recitation Exercise
11 - Stacks and Queues
Stack Exercise - Reversing a List

Given a list of strings on the command line, create a stack structure
that reverses the order of the strings, and also reverses the characters
in each individual string

For example:

$ python3 reverse.py A Man A Plan A Canal Panama
strings: ['A', 'Man', 'A', 'Plan', 'A', 'Canal', 'Panama']
stack size: 7
stack top: amanaP
stack elements:
amanaP
lanaC
A
nalP
A
naM
A

This is the student starter code.
"""

import week11.recitation.rit_stack
from week11.recitation.rit_stack import *

import sys  # sys.argv


def list_to_reverse_stack(lst):
    """
    Create and return a stack of reversed words from lst that
    are in reverse order.  For example:

    stack = list_to_reverse_stack(['Madam', 'Im', 'Adam']))

    Printing the stack would give:

    Stack(size=3, nodes=Node(value='madA',
                      next=Node(value='mI',
                      next=Node(value='madaM',
                      next=None))))

    :param lst: a list of strings
    :return: a stack of the reversed words in reverse order
    """
    # create empty stack
    reverse_stack = Stack(0, None)

    # TODO: Step 2

    # loop over the strings in lst and push them onto the stack
    # as reversed strings, e.g. 'PANAMA' -> 'AMANAP'.
    # Hint, to reverse a string:
    #       value = 'PANAMA'
    # Use a complete slice and step by negative one:
    #       value[::-1]   # 'AMANAP'

    # TODO: Step 3
    for i in lst:
        i = i[::-1]
        push(reverse_stack, i)

    # return the stack
    return reverse_stack

    # TODO: Step 4


def main():
    """
    The main function reads the strings from the command line
    and creates a stack of reversed strings.
    """
    if len(sys.argv) < 2:
        print('Usage: python3 reverse.py strings')
    else:
        # print out the strings to reverse
        strings = sys.argv[1:]
        print('strings:', strings)

        # reverse each string and push them into a stack
        # by calling list_to_reverse_stack with the list of
        # strings and getting the stack back

        # TODO: Step 1
        reverse_stack = list_to_reverse_stack(strings)

        # print out the stack size and top element

        # TODO: Step 5
        print('stack size:', reverse_stack.size)
        print('stack top:', reverse_stack.nodes.value)

        print('stack elements:')

        # while the stack is not empty, print the element at the
        # top of the stack and then pop it

        # TODO: Step 6
        while reverse_stack.nodes is not None:
            print(reverse_stack.nodes.value)
            pop(reverse_stack)


if __name__ == '__main__':
    main()
