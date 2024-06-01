"""
Alex Iacob
Lab 5: Jertle
ai9388@rit.edu
"""

import turtle as t
import sys
import time

# Turtle Canvas Window Setup ######
                                  #
WORLD_SIZE = 300                  #
MARGIN = 10                       #
WINDOW_SIZE = WORLD_SIZE + MARGIN #
                                  #
###################################

SLEEP_TIME = 5

# The Set of Jertle Commands #####################################
                                                                 #
PENDOWN_CMD = "!1"  # No parameters                              #
PENUP_CMD = "!0"    # No parameters                              #
TURN_CMD = "o^"     # Parameter: angle, to the left, in degrees  #
FORWARD_CMD = "->"  # Parameter: number of units to move         #
CIRCLE_CMD = "()"   # Parameter: radius of circle                #
                                                                 #
##################################################################


### PRE-DEFINED ERROR CODES ###################################
                                                              #
ILLEGAL_COMMAND = 1  # Unrecognized command string            #
MISSING_ARGUMENT = 2 # More arguments needed for this command #
NO_ARG_END = 3       # Can't find the matching closing brace  #
                                                              #
###############################################################


def error(msg, e_code):
    """
    A fatal error has occurred.
    Print an error message and end the program.
    :param msg: the string message to print before ending the program
    :param e_code: the integer error code with which the program exits
    """
    print(msg, file=sys.stderr)
    sys.exit(e_code)


def initialize():
    """
    Set up the turtle world.
    :return: None
    """
    t.setup(WINDOW_SIZE, WINDOW_SIZE)
    t.setworldcoordinates(-MARGIN, -MARGIN, WORLD_SIZE, WORLD_SIZE)


def locate_end_of_arg(line):
    """
    locates the end of the argument for the line of jertle code
    always finds the index of the opening and closing curly bracket
    :param line:
    :return:
    """
    start = None
    end = None

    for c in range(0, len(line)):
        if line[c] == "{":
            start = c
        if line[c] == "}":
            end = c
            break

    # creates a tuple that returns the indexes of opening and closing brackets
    return start, end


def interpret(program):
    # always runs while the program is not an empty string
    while program != "":
        a = locate_end_of_arg(program)

        # 2 error statements to detect whether there is any brackets out of place/not there
        if a[0] is None:
            error('No starting bracket detected', MISSING_ARGUMENT)

        if a[1] is None:
            error('No end bracket detected', NO_ARG_END)

        # creates a temporary variable to store an empty string, to which will replace
        temp = program[a[1] + 1:]

        # creates a string of the actual command that is being run including the value(if applicable)
        cmd = program[:a[1] + 1]

        # determines what command is being run, i.e. move forward/turn/circle/etc
        k = cmd[0: 2]

        # finds the value for the command
        b = float(cmd[ 3:a[1] ])

        if k == PENUP_CMD:
            t.up()
        elif k == PENDOWN_CMD:
            t.down()
        elif k == FORWARD_CMD:
            t.forward(b)
        elif k == TURN_CMD:
            t.left(b)
        elif k == CIRCLE_CMD:
            t.circle(b)
        else:
            error('Illegal command \'' + k + '\'', ILLEGAL_COMMAND)

        # empties the string to run again
        program = temp


def main():
    file_name = str(input("What text file would you like to run without the extension?\n"))

    with open(file_name + '.txt', "r")as file:
        for line in file:
            initialize()
            interpret(line.strip())
            time.sleep(5)
            t.clear()
            t.home()

    t.done()


if __name__ == "__main__":
    main()
