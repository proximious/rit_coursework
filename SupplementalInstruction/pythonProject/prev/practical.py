"""
file: octy.py
author: Alex Iacob
language: python3.9
purpose: practical exam to draw iterative and recursive octagons
"""

import turtle as t
import random as rand

total_perimeter = 0


def init():
    t.setworldcoordinates(-170, -10, 430, 590)
    t.pensize(2)
    t.speed(0)  # don't speed up until the code is working right!

    # colormode lets you use rand.randint( 1, 250) to produce
    # random values for each of the red, green, and blue arguments to
    # make turtle.fillcolor( red, green, blue) make a random color.
    perimeter = 0
    t.colormode(255)


def octagon(size):
    perimeter = 0
    red = rand.randint(0, 255)
    blue = rand.randint(0, 255)
    green = rand.randint(0, 255)
    t.fillcolor(red, blue, green)
    for i in range(0, 4):
        t.begin_fill()
        t.forward(size)
        t.left(45)
        t.forward(size)
        t.left(45)
        t.end_fill()
        perimeter += size * 2
    return perimeter


## todo: write your code in here
def draw(size, depth):
    if depth == 0:
        return 0  # perimeter
    elif depth == 1:
        octagon(size)
    else:
        octagon(size)

        # draw bottom left octagon
        draw(size / 2, depth - 1)
        # position for next octagon
        t.forward(size / 2)
        t.left(45)
        t.forward(size / 2)
        t.right(45)

        # draw bottom right octagon
        draw(size / 2, depth - 1)
        # position for next octagon
        t.left(135)
        t.forward(size / 2)
        t.right(45)
        t.forward(size / 2)
        t.left(90)
        t.forward(size / 2)
        t.left(45)
        t.forward(size / 2)
        t.left(135)

        # draw upper left octagon
        draw(size / 2, depth - 1)
        # position for the next octagon
        t.forward(size / 2)
        t.left(45)
        t.forward(size / 2)
        t.right(45)

        # draw upper right octagon
        draw(size / 2, depth - 1)
        # return to home position
        t.right(135)
        t.forward(size / 2)
        t.right(45)
        t.forward(size / 2)
        t.left(90)
        t.forward(size / 2)
        t.left(45)
        t.forward(size / 2)
        t.left(45)


def main():
    """
    todo: update and revise code in here for each part
    """
    init()
    # size = int(input("enter size: "))
    size = 240  # testing purposes
    # depth = int(input("enter depth: "))
    depth = 6  # testing purposes
    draw(size, depth)  # replace this with your function calls
    print("close the canvas to quit")
    t.done()
    print("bye")


if __name__ == "__main__":
    main()
