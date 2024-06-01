"""
file: octy.py
author: SI Session
language: python3.9
purpose: practical exam to draw iterative and recursive octagons
"""

import turtle as t
import random as rand


def init():
    t.setworldcoordinates(-170, -10, 430, 590)
    t.pensize(2)
    t.speed(0)  # don't speed up until the code is working right!

    # colormode lets you use rand.randint( 1, 250) to produce
    # random values for each of the red, green, and blue arguments to
    # make turtle.fillcolor( red, green, blue) make a random color.
    t.colormode(255)


## todo: write your code in here
def octagon(size):
    i = 0
    num1 = rand.randint(1, 250)
    num2 = rand.randint(1, 250)
    num3 = rand.randint(1, 250)
    while i < 4:
        t.fillcolor(num1, num2, num3)
        t.begin_fill()
        t.forward(size)
        t.left(360 / 8)
        t.forward(size)
        t.left(360 / 8)
        t.end_fill()
        i += 1


def recurse_octagon(depth, size):
    if depth == 0:
        return 0
    elif depth <= 1:
        octagon(size)
        return size * 8
    else:
        octagon(size)
        p1 = recurse_octagon(depth - 1, size / 2)
        # reposition to lower right corner
        t.forward(size / 2)
        t.left(360 / 8)
        t.forward(size / 2)
        t.right(360 / 8)
        p2 = recurse_octagon(depth - 1, size / 2)
        t.left(90)
        t.forward(size / 2)
        t.left(45)
        t.forward(size / 2)
        t.right(135)
        p3 = recurse_octagon(depth - 1, size / 2)
        t.backward(size / 2)
        t.left(45)
        t.backward(size / 2)
        t.left(45)
        t.right(90)
        p4 = recurse_octagon(depth - 1, size / 2)
        t.right(90)
        t.forward(size / 2)
        t.left(45)
        t.forward(size / 2)
        t.left(45)

        return (size * 8) + p1 + p2 + p3 + p4


def main():
    """
    todo: update and revise code in here for each part
    """
    init()
    size = 240
    depth = 3
    print("perimeter is ", end="")
    print(recurse_octagon(depth, size))  # replace this with your function calls
    print("close the canvas to quit")
    t.done()
    print("bye")


if __name__ == "__main__":
    main()
