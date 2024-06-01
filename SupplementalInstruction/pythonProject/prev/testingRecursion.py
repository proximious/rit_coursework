"""
SI Session
Alex Iacob

"""
import turtle as t

"""def factorial(num):
    
    if num != 0:
        print(num)
        num = num - 1
        factorial(num)
    else:
        print(num)
"""


def draw_triangle(distance):
    t.forward(distance)
    t.right(120)
    t.forward(distance)
    t.right(120)
    t.forward(distance)
    t.right(120)

    distance = distance / 2


def draw_triangle0():
    pass


def draw_triangle1(distance):
    draw_triangle(distance)
    t.left(120)
    draw_triangle0()
    t.right(120)
    t.forward(distance)
    t.left(120)
    draw_triangle0()
    t.right(120)
    t.backward(distance)


def draw_triangle2(distance):
    draw_triangle(distance)
    t.left(120)
    t.up()
    t.forward(distance / 2)
    t.right(120)
    t.down()
    draw_triangle1(distance / 2)
    t.right(60)
    t.up()
    t.left(60)
    t.forward(distance)
    t.down()
    draw_triangle1(distance / 2)


def draw_triangle_recursive(distance):
    pass


def draw_spiral(distance):
    if distance <= 0:
        pass
    else:
        t.down()
        t.forward(distance)
        t.right(90)
        distance = distance - 5
        draw_spiral(distance)


if __name__ == '__main__':
    #number = int(input("pick a number"))
    t.speed(1)
    draw_triangle2(100)
    t.done()
