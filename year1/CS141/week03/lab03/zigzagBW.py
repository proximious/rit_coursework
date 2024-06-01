"""
Alex Iacob
Lab 3: zigzag black and white
ai9388@rit.edu
"""

import turtle as tt
import math


def draw_zigzag_recursive(depth, length):
    """
    turtle is facing east
    turtle is down
    turtle is in the center of the zigzag
    turtle begins and ends at same starting position and orientation
    :param length:
    :return:
    """
    tt.left(90)
    tt.forward(length / 2)
    tt.right(90)
    tt.forward(length)

    if depth >= 2:
        draw_zigzag_recursive(depth - 1, length / 2)

    tt.backward(length)
    tt.right(90)
    tt.forward(length)
    tt.right(90)
    tt.forward(length)

    if depth >= 2:
        draw_zigzag_recursive(depth - 1, length / 2)

    tt.backward(length)
    tt.right(90)
    tt.forward(length / 2)
    tt.right(90)


def main():
    depth = input("What depth would you like the zigzag to be? ")
    length = input("How long do you want the zigzag to be? ")

    tt.speed(0)
    draw_zigzag_recursive(int(depth), int(length))
    tt.done()


if __name__ == "__main__":
    main()
