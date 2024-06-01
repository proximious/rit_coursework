"""
Alex Iacob
Homework 5: Favorite Song
ai9388@rit.edu
"""

import turtle as t


def init():
    """
    moves the turtle to an upper left portion of the window
    sets the turtle speed to 0 and activated tracer to draw faster
    """
    t.up()
    t.speed(0)
    t.tracer(300, 30)
    t.goto(-450, 400)
    t.down()


def square(color):
    """
    draws a 10x10 square with the respective color assigned to it
    each square represents a character in a line of the song
    turtle starts on upper left corner facing east
    turtle ends on upper right corner facing east
    :param color:
    :return:
    """
    t.color(color)
    t.begin_fill()
    t.forward(10)
    t.right(90)
    t.forward(10)
    t.right(90)
    t.forward(10)
    t.right(90)
    t.forward(10)
    t.right(90)
    t.forward(10)
    t.end_fill()


def paint_line(line):
    """
    paints a line of squares to represent each character
    depending on the character, a different color will be chosen
    :param line:
    :return:
    """
    for char in line.strip():
        if ord(char) < 70:
            color = '#498579'
        elif 70 <= ord(char) < 100:
            color = '#832e1a'
        elif 100 <= ord(char) < 110:
            color = '#f5f6ee'
        elif 110 <= ord(char) < 122:
            color = '#1a0e0e'
        else:
            color = '#e88d88'

        square(color)


def picture(file_name):
    """
    takes the file name that the user input and runs a for loop for each line to create the entire image
    there is no need to close the file since the 'with' function automatically closes it
    :param file_name:
    :return:
    """
    with open(file_name, "r") as file:
        line_count = 1
        for line in file:
            paint_line(line)
            t.up()
            t.goto(-450, 400 - (10 * int(line_count)))
            t.down()
            line_count += 1


def main():
    """
    song being used is "Party Poison" by My Chemical Romance
    asks the user for the name of the song txt file and calls the picture function with the file
    name as the parameter.
    :return:
    """
    init()

    file_name = input("What is the name of the text file without the extension?\n")
    picture(file_name + '.txt')
    print("Recommendation: Maximize the python graphics window")

    t.done()


if __name__ == "__main__":
    main()
