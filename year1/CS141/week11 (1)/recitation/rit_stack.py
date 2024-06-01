"""
CSCI-141 Computer Science 1 Presentation Code
11-StacksQueues
Stacks

This is the definition of a Stack structure and its supporting routines.
"""

from week11.recitation.node import Node, Union
from dataclasses import dataclass


@dataclass  # (frozen=True)       # unfrozen because operations change field values
class Stack:
    size: int  # the number of elements in the stack
    nodes: Union[None, Node]  # the collection of nodes starting with the top node


def mk_empty_stack():
    """
    Create and return a new empty stack.
    :return: a new stack with size initialized to zero and nodes initialized
    to None (empty).
    """
    return Stack(0, None)


def push(stack, element):
    """
    Add a new element to the top of the stack by changing the stack.
    :param stack: the stack
    :param element: the new element to push
    :return None
    """
    stack.nodes = Node(element, stack.nodes)
    stack.size = stack.size + 1


def top(stack):
    """
    Return top element on stack without changing the stack.
    :pre: stack is not empty
    :param stack: the stack
    :return: the top element on the stack
    """
    if empty_stack(stack):
        raise IndexError('top of empty stack')

    return stack.nodes.value


def pop(stack):
    """
    Remove the top element in the stack by changing the stack.
    :pre: stack is not empty
    :param stack: the stack
    :return: None
    """
    if empty_stack(stack):
        raise IndexError('pop on empty stack')

    stack.nodes = stack.nodes.next
    stack.size = stack.size - 1


def empty_stack(stack):
    """
    Is the stack empty?
    :param stack: the stack
    :return: whether the stack is empty or not
    """
    return stack.nodes is None


def size(stack):
    """
    How many elements are currently in the stack?
    :param stack: the stack
    :return: the size of the stack
    """
    return stack.size


def main():
    """ The main routine tests the stack data structure. """
    # begin with an empty stack
    stack_str = mk_empty_stack()
    print('Creating empty stack...')
    print('Stack empty?', True == empty_stack(stack_str))
    print('Stack size is 0?', 0 == size(stack_str))

    # add first element
    print('push A...')
    push(stack_str, 'A')
    print('Stack not empty?', False == empty_stack(stack_str))
    print('Stack size is 1?', 1 == size(stack_str))
    print('top is A?', 'A' == top(stack_str))

    # add second element
    print('push B...')
    push(stack_str, 'B')
    print('top is B?', 'B' == top(stack_str))

    # add third element
    print('push C...')
    push(stack_str, 'C')
    print('top is C?', 'C' == top(stack_str))
    print('Stack size is 3?', 3 == size(stack_str))

    # pop top element, C
    print('pop...')
    pop(stack_str)
    print('Stack not empty?', False == empty_stack(stack_str))
    print('Stack size is 3?', 2 == size(stack_str))
    print('top is B?', 'B' == top(stack_str))

    # add fourth element
    print('push D...')
    push(stack_str, 'D')
    print('top is D?', 'D' == top(stack_str))

    # empty the stack
    print('Emptying the stack...')
    while not empty_stack(stack_str):
        print('Top of stack:', top(stack_str))
        print('pop...')
        pop(stack_str)


if __name__ == '__main__':
    main()
