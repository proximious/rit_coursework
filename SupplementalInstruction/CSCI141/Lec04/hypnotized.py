"""
file: hypnotized.py
author: bksteele
language: python3
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

def draw_hypnotized( size, depth):
    """
    """
    if depth == 0:
        print()
        return 0
    tt.fd( size)
    tt.left( 90)
    print( size, end=" + ")
    return size + draw_hypnotized( size - 1, depth - 1)

def main():
    """
    main function initializes canvas, prompts for a 'depth' count,
    and draws the hypnotized spiral outside in.
    Finally, it prints the distance drawn and waits for window close.
    """
    size = 50
    initialize( size)
    count = int( input( "enter a 'depth' to draw: "))
    distance = draw_hypnotized( size, count)
    print( "distance", distance)
    print( "close canvas to end the program.")
    tt.done()

if __name__ == "__main__":
    main()
