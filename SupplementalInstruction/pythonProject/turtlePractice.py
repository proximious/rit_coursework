import turtle as tt
import math


def square(size, color):
    tt.color(color)
    tt.fd(size)
    tt.left(90)
    tt.fd(size)
    tt.left(90)
    tt.fd(size)
    tt.left(90)
    tt.fd(size)
    tt.left(90)


def rec_rectangles(size, depth):
    if depth == 0:
        pass
    elif depth == 1:
        square(size, 'black')
    else:
        square(size, 'black')
        tt.backward(size / 2)
        rec_rectangles(size / 2, depth - 1)
        tt.forward(size * (3 / 2))
        rec_rectangles(size / 2, depth - 1)
        tt.backward(size)


def rectangle3(size):
    square(size / 2, 'blue')
    square(size, 'red')
    square(size / 2, 'blue')


def rabbits(n):
    if n == 1:
        return 1
    else:
        return 4 * rabbits(n - 1) - 1


def circles_i(r):
    total_area = 0
    while r >= 25:
        tt.circle(r)
        total_area += (math.pi * (r ** 2))
        r = (3 * r) / 4
    return total_area

def other(r, area = 0):
    while r >= 25:
        tt.circle(r)
        area += math.pi * r * r
        r = (3/4) * r

    return area

def other2(r):
    A = 0
    while r >= 25:
        tt.circle(r)
        A += math.pi * r * r
        r = (3 / 4) * r
    return A




def d(m):
    l = 100
    for i in range(4):
        while (l < m):
            tt.forward(l)
            tt.left(90)
            tt.forward(100)
            tt.left(90)
    tt.done()

def f(num, t = 0):
    if num == 1:
        return 1 + t
    else:
        t = t + 1/num
        print(t)
        return f(num - 1, t)




if __name__ == '__main__':
    f(3, 5)
