"""
file: class_spiral_out.py
"""

import turtle as t

def init():
    """ set up canvas and pen
    """
    t.pensize( 2)

def spiral( N, size, acc):
    """
    spiral outward N cycles and return the distance drawn
    """
    if N == 0:
        return acc
    else:
        t.forward( size )
        t.left( 90)
        return spiral( N - 1, size + 10, acc + size)

def main():
    """ draw spiral """
    init()
    count = int( input( "Enter count: "))
    distance = spiral( count, 5, 0 )
    print( 'distance =', distance)
    t.done()

if __name__ == "__main__":
    main()

