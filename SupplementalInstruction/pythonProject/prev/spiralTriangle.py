"""
Alex Iacob ai9388@rit.edu

recursiveTriangles.py
"""

import turtle as t


def recursive_triangle(size):
    length = 0
    while size >= 10:
        t.forward(size)
        length += size
        t.left(120)
        size -= 10
    print("The total length is: ", length)


if __name__ == '__main__':
    t.speed(0)
    size = int(input("size: "))
    recursive_triangle(size)
    t.done()
