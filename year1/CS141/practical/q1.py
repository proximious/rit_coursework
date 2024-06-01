"""
file: q1.py
language: python3
author: Alex Iacob ai9388@rit.edu
description: Draws sets of circles that look a bit like tunnels.
"""

import turtle

RIGHT_ANGLE = 90

# Parameters you may want to play with

RADIUS = 100
REDUCTION = 0.08
MIN_RADIUS = 12.0
SPEED = 0  # Max turtle speed


def initialize():
    turtle.speed(SPEED)
    turtle.up()
    turtle.goto(0, 200)
    turtle.down()


def terminate():
    print("You may now close the drawing window.")
    turtle.done()


def draw_tunnel_r(radius: float, min_radius: float) -> int:
    """
    Draw a tunnel-like image made of consecutive circles.
    Each circle's starting point is a fraction of the previous circle's
    radius away from the previous circle's staring point. (fraction=REDUCTION)
    The opposite sides (at the 180-degree point) are all coincident.
    This function uses a recursive algorithm.
    See named constants at top of file.
    :param radius: radius of initial ("closest") circle
    :param min_radius: the stopping point. When the calculated radius of the
                       next circle is < min_radius, don't draw it. Just return.
    :post: The turtle is in position to draw the next circle (whose radius is
           too small)
    :return: The number of circles drawn.
    """
    return draw_tunnel_rec(radius, min_radius, 0)


def draw_tunnel_rec(radius: float, min_radius: float, circles: int) -> int:
    """
    A "private" function that adds the accumulator argument and does
    the actual recursive algorithm.
    (This is the code on which the loop function should be based.)
    :param radius: radius of initial ("closest") circle
    :param min_radius: the stopping point. When the calculated radius of the
                       next circle is < min_radius, don't draw it. Just return.
    :param circles: An internal counter for the number of circles drawn
    :post: The turtle is in position to draw the next circle (whose radius is
           too small)
    :return: The number of circles drawn.
    """
    if radius >= min_radius:
        turtle.circle(radius)
        delta = radius * REDUCTION
        turtle.penup()
        turtle.left(RIGHT_ANGLE)
        turtle.forward(delta * 2)
        turtle.right(RIGHT_ANGLE)
        turtle.pendown()
        return draw_tunnel_rec(radius - delta, min_radius, circles + 1)
    else:
        return circles


def draw_tunnel_loop(radius: float, min_radius: float) -> int:
    """
    Draw a tunnel-like image made of consecutive circles.
    Each circle's starting point is a fraction of the previous circle's
    radius away from the previous circle's staring point. (fraction=REDUCTION)
    The opposite sides (at the 180-degree point) are all coincident.
    This function uses a ('while') looping algorithm.
    See named constants at top of file.
    :param radius: radius of initial ("closest") circle
    :param min_radius: the stopping point. When the calculated radius of the
                       next circle is < min_radius, don't draw it. Just return.
    :post: The turtle is in position to draw the next circle (whose radius is
           too small)
    :return: The number of circles drawn.
    """
    circles = 0
    while radius >= min_radius:
        turtle.circle(radius)
        delta = radius * REDUCTION
        turtle.penup()
        turtle.left(RIGHT_ANGLE)
        turtle.forward(delta * 2)
        turtle.right(RIGHT_ANGLE)
        turtle.pendown()
        radius -= delta
        circles += 1

    return circles


def move_down(dist: float) -> None:
    """
    Move to a new starting point before drawing the next tunnel.
    This allows us to separate the multiple drawings and
    test if the draw_tunnel functions do all movements
    relatively (no setpos/goto).
    It should not be changed for the exercise.
    """
    turtle.penup()
    turtle.right(RIGHT_ANGLE)
    turtle.forward(2 * dist)
    turtle.left(RIGHT_ANGLE)
    turtle.pendown()


def tests() -> None:
    """
    Draw four tunnels:
    - two with recursion,
    - two with looping,
    - two with a single circle,
    - two with multiple circles
    """
    initialize()

    # (already written) recursive code tests
    num_c = draw_tunnel_rec(MIN_RADIUS, MIN_RADIUS, 0)
    print(num_c, "circles were drawn.")
    move_down(RADIUS + MIN_RADIUS)
    num_c = draw_tunnel_rec(RADIUS, MIN_RADIUS, 0)
    print(num_c, "circles were drawn.")
    move_down(RADIUS + MIN_RADIUS)

    # Looping code tests
    num_c = draw_tunnel_loop(MIN_RADIUS, MIN_RADIUS)
    print(num_c, "circles were drawn.")
    move_down(RADIUS + MIN_RADIUS)
    num_c = draw_tunnel_loop(RADIUS, MIN_RADIUS)
    print(num_c, "circles were drawn.")
    terminate()


if __name__ == '__main__':
    tests()
