"""
Session 6B
Alex Iacob ai9388@rit.edu
"""

import turtle as t


# def spiral(a):
#     t.color('black')
#     while a > 0:
#         t.forward(a)
#         t.left(120)
#         a = a - 10
#     return

def draw_rectangle(size):
    t.forward(size)
    t.left(90)
    t.forward(size / 2)
    t.left(90)

    t.forward(size)
    t.left(90)
    t.forward(size / 2)
    t.left(90)


def rec_rec(size, depth):
    t.pendown()
    if depth == 0:
        t.penup()
        return
    if depth == 1:
        draw_rectangle(size)
    draw_rectangle(size)
    t.penup()
    t.back(size / 2)
    rec_rec(size / 2, depth - 1)
    t.forward(size * 1.5)
    t.pendown()
    rec_rec(size / 2, depth - 1)
    t.back(size)

def main():
    # a = int(input("enter value: "))
    # spiral(a)
    # t.done()
    t.speed(0)
    size = int(input("size: "))
    depth = int(input("depth: "))

    rec_rec(size, depth)
    t.done()


main()
