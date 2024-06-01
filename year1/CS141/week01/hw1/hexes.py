"""
Alex Iacob
:precondition: Pen is down. Turtle is facing the direction in which the first
side will be drawn.
:postcondition: Turtle’s state (location, direction, pen position)
is the same as when this function started.
"""
import turtle


def draw_hexagon_segment():
    turtle.forward(100)
    turtle.right(60)


def draw_hexagon():  # face east and creates hexagon by using 6 consecutive line segments
    turtle.speed(0)
    draw_hexagon_segment()
    draw_hexagon_segment()
    draw_hexagon_segment()
    draw_hexagon_segment()
    draw_hexagon_segment()
    draw_hexagon_segment()
    turtle.up()  # raises pen up in order to get turtle to applicable position to create next hexagon
    turtle.forward(100)
    turtle.left(60)
    turtle.down()


def draw_honeycomb():  # function is called 6 times to create 6 hexagons
    draw_hexagon()
    draw_hexagon()
    draw_hexagon()
    draw_hexagon()
    draw_hexagon()
    draw_hexagon()


draw_honeycomb()
turtle.forward(-100)  # honeycomb pattern must be offset by 100px before creating the second
draw_honeycomb()
turtle.forward(100)  # returns turtle to original position
exit()
