"""
file: spiral_in.py
author: bksteele's cs141 class
language: python3
spiralling inward generally recursive
"""

import turtle as tt

def initialize( size):
    """
    initialize world coordinates with origin near lower left corner.
    post-conditions: turtle faces East, pen down, at origin.
    """
    tt.setworldcoordinates( -5, -5, size + 5, size + 5)
    tt.pensize( 2)
    #tt.speed( 0)


def spiral_g( size, depth):  # g for 'general'
    """
    draw a sequence of connected lines spiralling inward counterclockwise
    for the given depth count of lines.
    param: size integer shrinks by one unit for each successive segment.
    param: depth count reduces by one each cycle
    pre-conditions: turtle faces East, pen down, at origin.
                    depth >= 0 and size > 0
    """
    if depth == 0:
        print( 0)
        return 0
    tt.fd( size)
    tt.left( 90)
    print( size, end=" + ")
    return size + spiral_g( size - 1, depth - 1)

def spiral_tail( size, depth):
    return spiral_aux( size, depth, 0)

def spiral_aux( size, depth, subtotal):
    if depth == 0:
        return subtotal
    tt.fd( size)
    tt.left( 90)
    return spiral_aux( size - 1, depth - 1, size + subtotal)

def main():
    """
    main function initializes canvas with a fixed coordinate system size,
    prompts for a 'depth' count, and draws the spiral outside in.
    Finally, it prints the distance drawn and waits for window close.
    """
    size = 50
    initialize( size)
    count = int( input( "enter a 'depth' to draw: "))
    distance = spiral_tail( size, count)
    print( "distance", distance)
    print( "close canvas to end the program.")
    tt.done()

if __name__ == "__main__":
    main()

