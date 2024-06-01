"""
Alex Iacob ai9388@rit.edu
"""
import turtle as t


def draw_single_rectangle(size):
    t.forward(2 * size)
    t.left(90)
    t.forward(size)
    t.left(90)

    t.forward(2 * size)
    t.left(90)
    t.forward(size)
    t.left(90)


def recursive_rectangles(size, depth):
    if depth == 0:
        pass
    elif depth == 1:
        draw_single_rectangle(size)
    else:
        draw_single_rectangle(size)
        t.backward(size)
        recursive_rectangles(size / 2, depth - 1)
        t.forward(size * 3)
        recursive_rectangles(size / 2, depth - 1)
        t.backward(size * 2)


if __name__ == '__main__':
    t.speed(0)
    t.backward(100)
    recursive_rectangles(200, 3)
    t.done()
