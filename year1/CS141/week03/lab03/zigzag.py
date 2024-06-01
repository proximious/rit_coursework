"""
Alex Iacob
Lab 3: zigzag color
ai9388@rit.edu
"""

import turtle as tt


def zigzag(depth, length):
    """
    turtle is facing east
    turtle is down
    turtle is in the center of the zigzag
    turtle begins and ends at the same position and orientation
    :param length:
    :return:
    """
    def zig(length):
        # makes the first portion of the zigzag and raises the turtle up
        tt.down()
        tt.left(90)
        tt.forward(length / 2)
        tt.right(90)
        tt.forward(length)
        tt.up()

    def zag(length):
        # creates the other segment of the zigzag and raises the turtle up
        tt.down()
        tt.right(90)
        tt.forward(length / 2)
        tt.right(90)
        tt.forward(length)
        tt.up()

    def returning_zig(length):
        # returns the turtle to the center of the zigzag and original orientation from the zig
        tt.up()
        tt.backward(length)
        tt.right(90)
        tt.forward(length / 2)
        tt.left(90)
        tt.down()

    def returning_zag(length):
        # returns the turtle to the center of the zigzag and original orientation from the zig
        tt.up()
        tt.backward(length)
        tt.right(90)
        tt.forward(length / 2)
        tt.right(90)

    # used modulo to determine whether the depth is even or odd
    # if depth is even, it draws red, of odd, it draws blue
    if depth % 2 == 0:
        tt.color('red')
    else:
        tt.color('blue')

    # draws the actual zigzag
    zig(length)

    # determines whether to draw another zigzag depending on the depth
    # if it is greater than or equal to 2, then the function is used, if not, the function is passed
    if depth >= 2:
        tt.down()
        tt.left(45)
        zigzag(depth - 1, length / 2)
        tt.right(45)

    # returns turtle to original position of zigzag to draw the other portion of it
    returning_zig(length)
    zag(length)

    # determines whether it is necessary to draw another zigzag, and if it is, it draws another zigzag
    if depth >= 2:
        tt.down()
        tt.left(45)
        zigzag(depth - 1, length / 2)
        tt.right(45)

    # checks the modulo again to retain the same color as the original
    if depth % 2 == 0:
        tt.color('blue')
    else:
        tt.color('red')

    # returns the final segment back the the starting point and
    returning_zag(length)


def main():
    # asks the user what length and depth they would like for the program to run
    length = input("How long do you want the zigzag to be? ")
    depth = input("What depth would you like the zigzag to be? ")

    tt.speed(0)
    zigzag(int(depth), int(length))
    tt.done()


if __name__ == "__main__":
    main()
