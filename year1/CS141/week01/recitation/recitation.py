"""
precondition: turtle is down, facing east on upper left part of square
"""

import turtle

def draw_square():
    turtle.color('green')
    turtle.down()
    turtle.forward(100)
    turtle.right(90)
    turtle.forward(100)
    turtle.right(90)
    turtle.forward(100)
    turtle.right(90)
    turtle.forward(100)
    turtle.up()

def draw_circle():
    turtle.color('blue')
    turtle.up()
    turtle.right(90)
    turtle.forward(50)
    turtle.right(90)
    turtle.forward(75)
    turtle.left(90)
    turtle.down()
    turtle.circle(25)
    turtle.up()
    #returning to original position



def draw_triangle():
    pass

def main():
    turtle.pensize(4)
    draw_square()
    draw_circle()
    draw_triangle()
    turtle.done()

main()