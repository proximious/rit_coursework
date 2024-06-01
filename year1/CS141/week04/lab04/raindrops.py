"""
ALex Iacob
Lab 4: Raindrops
ai9388@rit.edu
"""

import turtle as tt
import math
import random


def init():
    """
    init draws the pond as a square then colors it light blue while setting the turtle back to the center
    and sets its speed to 0, to draw everything faster.
    :return:
    """
    tt.up()
    tt.speed(0)
    tt.setpos(-400, 400)
    tt.begin_fill()
    tt.color('light blue')
    tt.down()
    tt.forward(800)
    tt.right(90)
    tt.forward(800)
    tt.right(90)
    tt.forward(800)
    tt.right(90)
    tt.forward(800)
    tt.end_fill()
    tt.color('black')
    tt.up()
    tt.setpos(0, 0)
    tt.down()
    tt.right(90)


def draw_raindrops(raindrops_quantity):
    """
    Creates the first drop of the raindrop, places it in a random position with a random color
    :param raindrops_quantity: the amount of raindrops to create
    :return:
    """
    if raindrops_quantity == 0:
        return 0
    else:
        radius = random.randint(1, 20)  # creates a random integer between 1 and 20 for the radius of the raindrop

        total_circum = 0

        # brings the turtle to appropriate position and orientation to start the raindrop
        # also creates the ripple effect
        tt.right(90)  # facing south
        tt.up()
        tt.forward(radius)
        tt.left(90)
        tt.down()

        # finds x and y values for center of the raindrop
        x_pos = random.randint(-300, 300)
        y_pos = random.randint(-300, 300)
        tt.up()
        tt.setpos(x_pos, y_pos)
        tt.down()

        # creates a random number for each RGB value for each new color
        tt.colormode(255)
        tt.fillcolor(random.randint(1, 255), random.randint(1, 255), random.randint(1, 255))

        # drawing the initial raindrop
        tt.begin_fill()
        tt.circle(radius)
        tt.end_fill()

        # moves turtle to first ripple position
        tt.up()
        tt.right(90)
        tt.forward(radius)
        tt.left(90)
        tt.down()

        raindrop_ripples(x_pos, y_pos, radius)

        return math.pi * radius * 2 + draw_raindrops(raindrops_quantity - 1)


def raindrop_ripples(x_pos, y_pos, radius):
    #  gets random integer from 2 to 8
    ripples = random.randint(2, 8)
    ripple_radius = radius * 2

    while ripples > 0:
        """
        turtle is a radius length underneath the bottom edge of the raindrop
        turtle is facing east
        """
        # separating lines for stylistic reasons
        if x_pos < abs(x_pos) + (2 * radius) + (ripples * ripple_radius) \
                and \
                y_pos < abs(y_pos) + (2 * radius) + (ripples * ripple_radius):

            tt.color('black')
            tt.circle(ripple_radius)
            ripples = ripples - 1
            ripple_radius = ripple_radius + radius
        else:
            break

        tt.up()
        tt.right(90)
        tt.forward(radius)
        tt.down()
        tt.left(90)


def main():
    """
    draws the pond, asks the user for the amount of raindrops they would like
    if the number is not between 1 and 100, it will prompt the user to
    :return:
    """

    raindrop_quantity = int(input("How many raindrops would you like?\n"))
    init()

    if raindrop_quantity > 100 or raindrop_quantity < 1:
        print("Raindrops will not be created \nRun again and choose a number between 1 and 100\n")
    else:
        print("Recommendation: Maximize the python graphics window")
        print("The total circumference is " + str(draw_raindrops(raindrop_quantity)) + " units.")
        tt.done()


if __name__ == "__main__":
    main()
