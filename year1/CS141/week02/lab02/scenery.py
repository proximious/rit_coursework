"""
Alex Iacob
Lab 2: Turtle Scenery
"""

import turtle as tt
import math

W = 200
L = 100

big_facade = int(W) * int(L)
small_facade = int(L) * int(L)


def draw_rectangle(L, W):
    """
    turtle is facing east
    turtle is in upper left corner
    pen is down
    """
    tt.forward(W)
    tt.right(90)
    tt.forward(L)
    tt.right(90)
    tt.forward(W)
    tt.right(90)
    tt.forward(L)
    tt.right(90)  # returns tt or original orientation


def draw_isosceles(W):
    """
    turtle is facing east
    turtle is in bottom left corner
    pen is down
    """
    tt.left(45)
    tt.forward(math.sqrt((W * W) / 2))
    tt.right(90)
    tt.forward(math.sqrt((W * W) / 2))
    tt.right(135)
    tt.forward(W)
    tt.right(180)  # returning turtle to original position


def draw_equilateral(L):
    """
    turtle is facing east
    turtle is bottom left corner
    pen is down
    """
    tt.left(60)
    tt.forward(int(L))
    tt.right(120)
    tt.forward(int(L))
    tt.right(120)
    tt.forward(int(L))
    tt.right(180)  # returns turtle to original position


def draw_tree(L, W):
    """"
    turtle is facing east
    turtle is on bottom right corner
    """
    # brings turtle to starting position
    tt.up()
    tt.forward(W + W / 2)
    tt.left(90)
    tt.forward(L)
    tt.right(90)
    tt.down()

    tt.color('brown')
    tt.begin_fill()
    draw_rectangle(2 * L, W / 15)
    tt.end_fill()

    tt.forward(W / 30)

    tt.color('green')
    tt.begin_fill()
    tt.circle(L / 2)
    tt.end_fill()

    tt.forward(W / 30)


def draw_small_house():
    """
    combines a square, equilateral triangle, and windows to create the small house
    uses "L" alone
    """
    # uses rectangle with same length and width to create a square
    tt.color('light blue')
    tt.begin_fill()
    draw_rectangle(int(L), int(L))
    tt.end_fill()

    # places roof on top of house
    tt.color('black')
    tt.begin_fill()
    draw_equilateral(int(L))
    tt.end_fill()

    # brings the turtle to appropriate location to begin making the first set of windows
    tt.up()
    tt.forward(int(L) / 7)
    tt.right(90)
    tt.forward(int(L) / 7)
    tt.left(90)
    tt.down()

    # creates the first set of windows
    tt.color('white')
    tt.begin_fill()

    draw_rectangle(int(L) / 7, int(L) / 7)
    tt.up()
    tt.forward(int(L) / 3.5)
    tt.down()

    draw_rectangle(int(L) / 7, int(L) / 7)
    tt.up()
    tt.forward(int(L) / 3.5)
    tt.down()

    draw_rectangle(int(L) / 7, int(L) / 7)
    tt.end_fill()

    # brings turtle to starting position for next set of windows
    tt.up()
    tt.backward((int(L) * 4) / 7)
    tt.right(90)
    tt.forward((int(L) * 3) / 7)
    tt.down()
    tt.left(90)

    # creates the second set of windows and the door
    tt.color('white')
    tt.begin_fill()
    draw_rectangle(int(L) / 7, int(L) / 7)

    tt.up()
    tt.forward(int(L) / 3.5)
    tt.end_fill()

    tt.color('brown')
    tt.begin_fill()
    tt.down()
    draw_rectangle(int(L) / 2.4, int(L) / 7)
    tt.end_fill()

    tt.up()
    tt.forward((int(L) * 2) / 7)
    tt.down()
    tt.color('white')
    tt.begin_fill()
    draw_rectangle(int(L) / 7, int(L) / 7)
    tt.end_fill()

    # returning turtle to starting spot and orientation
    tt.color('black')
    tt.up()
    tt.forward((int(L) * 2) / 7)
    tt.left(90)
    tt.forward((int(L) * 4) / 7)
    tt.right(90)
    tt.backward(int(L))


def draw_rectangle_house():
    """
    combines a rectangle, isosceles triangle, and windows
    uses both "L and W"
    """
    tt.color('light gray')
    tt.begin_fill()
    draw_rectangle(int(L), int(W))
    tt.end_fill()

    tt.color('black')
    tt.begin_fill()
    draw_isosceles(int(W))
    tt.end_fill()

    # moves turtle into position to start windows
    tt.up()
    tt.right(90)
    tt.forward(int(L) / 3)
    tt.left(90)
    tt.forward(int(W) / 6)
    tt.down()

    # creates two large windows
    tt.color('white')
    tt.begin_fill()
    draw_rectangle(int(W) / 6, int(W) / 6)
    tt.up()
    tt.forward(int(W) / 2)
    tt.down()
    draw_rectangle(int(W) / 6, int(W) / 6)
    tt.end_fill()

    # moves turtle into position to draw the door
    tt.up()
    tt.left(90)
    tt.forward(int(W) / 6)
    tt.left(90)
    tt.forward((int(W) * 2 / 9))
    tt.left(90)
    tt.forward(int(L) / 2)
    tt.left(90)

    # draws the door
    tt.color('brown')
    tt.begin_fill()
    tt.down()
    draw_rectangle(int(L) / 2, int(L) / 6)
    tt.end_fill()
    tt.up()

    # returns turtle to starting position
    tt.left(90)
    tt.forward(int(L) / 2)
    tt.left(90)
    tt.forward((int(W) * 4) / 9)
    tt.right(180)


def color_tree():
    tt.color('light blue')
    tt.begin_fill()
    draw_tree(int(L), int(W))
    tt.end_fill()


tt.speed(4)
print("The big facade is " + str(big_facade) + " units squared")
print("The small facade is " + str(small_facade) + " units squared")

# takes turtle to starting position

tt.up()
tt.right(180)
tt.forward(W * 1.5)
tt.right(180)
tt.down()

draw_small_house()

tt.up()
tt.forward(int(W))
tt.down()

draw_rectangle_house()

draw_tree(int(L), int(W))

tt.done()

big_facade = int(W) * int(L)
small_facade = int(L) * int(L)
