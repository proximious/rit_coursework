"""
Alex Iacob
Homework 3: Triangles
ai9388@rit.edu
"""

import turtle as tt


def init():
    """
    places the tt in the appropriate position to begin the recursion
    :return:
    """
    tt.left(90)
    tt.up()
    tt.backward(150)
    tt.down()


def triangle(length):
    """
    turtle is facing north and is up
    starts on bottom corner of upside down triangle
    :param length:
    :return:
    """
    tt.down()
    tt.left(30)
    tt.forward(length)
    tt.right(120)
    tt.forward(length)
    tt.right(120)
    tt.forward(length)
    tt.right(150)
    tt.up()


def triangles(length, depth):
    """
    turtle is facing north, and is down
    starts on bottom corner of upside down triangle
    :param length:
    :param depth:
    :return:
    """
    length = int(length)
    if depth == 0:
        pass
    elif depth == 1:
        triangle(length)
    else:
        triangle(length)
        tt.left(30)
        tt.forward(length)
        tt.right(30)
        triangles(length / 2, depth - 1)
        tt.right(90)
        tt.forward(length)
        tt.left(90)
        triangles(length / 2, depth - 1)
        tt.left(150)
        tt.forward(length)
        tt.right(150)


if __name__ == '__main__':
    tt.speed(0)
    length = int(input("How long would you like the triangles to be? "))
    depth = int(input("How many times would you like the triangles to be repeated? "))
    init()
    triangles(length, depth)
    tt.done()
