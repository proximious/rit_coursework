"""
Alex Iacob
letter height = 100
letter width = 100
space between letters in the same word = 20
space between dfferent word(s) = 50
before drawing any letter:
    turtle position : upper left corner of letter
    turtle orientation : facing east
    pen is : down
"""
import math
import turtle


def draw_t():
    turtle.forward(100)
    turtle.right(180)
    turtle.forward(50)
    turtle.left(90)
    turtle.forward(100)
    turtle.left(90)
    # returning the turtle to upper left and face east
    turtle.up()
    turtle.forward(70)
    turtle.left(90)
    turtle.forward(100)
    turtle.right(90)
    turtle.down()


def draw_o():
    draw_o_segment()
    draw_o_segment()
    draw_o_segment()
    draw_o_segment()
    # returning the turtle to upper left and face east
    turtle.up()
    turtle.forward(120)
    turtle.down()


def draw_o_segment():
    turtle.forward(100)
    turtle.right(90)


def draw_m():
    turtle.right(90)
    turtle.forward(100)
    turtle.right(180)
    turtle.forward(100)
    turtle.right(150)
    turtle.forward(math.sqrt(50 * 50 + 100 * 100))
    turtle.left(120)
    turtle.forward(math.sqrt(100 * 100 + 50 * 50))
    turtle.right(150)
    turtle.forward(100)
    turtle.left(90)
    # returning the turtle to upper left and face east
    turtle.up()
    turtle.forward(20)
    turtle.left(90)
    turtle.forward(100)
    turtle.right(90)
    turtle.down()


def draw_y():
    turtle.right(45)
    turtle.forward(math.sqrt(50 * 50 + 50 * 50))
    turtle.right(45)
    turtle.forward(50)
    turtle.right(180)
    turtle.forward(50)
    turtle.right(45)
    turtle.forward(math.sqrt(50 * 50 + 50 * 50))
    # returning the turtle to upper left and face east
    turtle.up()
    turtle.right(45)
    turtle.forward(50)
    turtle.down()


def draw_g():
    turtle.forward(100)
    turtle.right(180)
    turtle.forward(100)
    turtle.left(90)
    turtle.forward(100)
    turtle.left(90)
    turtle.forward(100)
    turtle.left(90)
    turtle.forward(50)
    turtle.left(90)
    turtle.forward(50)
    # returning the turtle to upper left and face east
    turtle.up()
    turtle.right(180)
    turtle.forward(70)
    turtle.left(90)
    turtle.forward(50)
    turtle.right(90)
    turtle.down()


def draw_u():
    turtle.right(90)
    turtle.forward(100)
    turtle.left(90)
    turtle.forward(100)
    turtle.left(90)
    turtle.forward(100)
    # returning the turtle to upper left and face east
    turtle.up()
    turtle.right(90)
    turtle.forward(20)
    turtle.down()


def draw_n():
    turtle.right(90)
    turtle.forward(100)
    turtle.right(180)
    turtle.forward(100)
    turtle.right(135)
    turtle.forward(math.sqrt(100 * 100 + 100 * 100))
    turtle.left(135)
    turtle.forward(100)
    # returning the turtle to upper left and face east
    turtle.up()
    turtle.right(90)
    turtle.forward(20)
    turtle.down()


def space():  # space is needed if you want to make multiple words
    turtle.up()
    turtle.forward(50)
    turtle.down()


def draw_outline():
    turtle.right(90)
    turtle.forward(120)
    turtle.right(90)
    turtle.forward(1080)
    turtle.right(90)
    turtle.forward(140)
    turtle.right(90)
    turtle.forward(1080)
    turtle.right(90)
    turtle.forward(20)


turtle.speed(0)
turtle.up()
turtle.right(180)
turtle.forward(500)
turtle.right(180)
turtle.down()

draw_t()
draw_o()
draw_m()
draw_m()
draw_y()
space()
draw_g()
draw_u()
draw_n()
draw_outline()
turtle.done()