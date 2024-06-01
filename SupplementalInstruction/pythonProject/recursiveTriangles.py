"""
@author: Alex Iacob ai9388
@filename: recursiveTriangles.py

runs recursive triangles
"""

import turtle as tt


def draw_triangle(size):
    tt.left(60)
    tt.fd(size)
    tt.left(120)
    tt.fd(size)
    tt.left(120)
    tt.fd(size)
    tt.left(60)


def draw_triangles(size, depth):
    if depth == 0:
        pass
    else:
        draw_triangle(size)
        tt.left(60)
        tt.fd(size)
        tt.right(60)
        draw_triangles(size / 3, depth - 1)
        tt.backward(size)
        draw_triangles(size / 3, depth - 1)
        tt.left(-60)
        tt.fd(size)
        tt.left(60)


if __name__ == '__main__':
    tt.speed(0)
    draw_triangles(100, 3)
    tt.done()
