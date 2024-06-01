"""
file: spiral.py
author: cs141 class
"""

import turtle as tt

INCREMENT = 5

def init():
    """
    set up canvas to the default and pen to size 2.
    post-condition: pen is down, facing East, at origin.
    """
    tt.pensize( 2)
    tt.speed( 0)

def spiral( N, size):
    """
    spiral outward N cycles and return the distance drawn.
    """
    if N == 0:
        return 0
    else:
        tt.forward( size )
        tt.left( 90)
        return size + spiral( N - 1, size + INCREMENT)

def main():
    """
    prompt for count of segments to draw, then init and draw.
    """
    start_size = 10
    count = int( input( "Enter count (100 is good): "))
    init()
    distance = spiral( count, start_size)
    print( 'distance =', distance)
    tt.done()

if __name__ == "__main__":
    main()

