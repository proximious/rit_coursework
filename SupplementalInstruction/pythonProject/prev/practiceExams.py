"""

"""

import turtle as tt
import math

LEN1 = 100
LEN2 = LEN1 / (2 * math.sqrt(3))  # about 29


def draw_part():
    tt.forward(LEN2)
    tt.pendown()
    tt.right(90)
    tt.forward(LEN1 / 2)
    tt.right(120)
    tt.forward(LEN1)
    tt.right(120)
    tt.forward(LEN1)
    tt.right(120)
    tt.forward(LEN1 / 2)
    tt.left(90)
    tt.penup()
    tt.backward(LEN2)


# def reverse ( s1 ):
#     return reverse_rec(s1, “”);
#
# def reverse_rec(s1, acc):
#     if s1 = “:
#         return acc
#     else:
#     return reverse_rec(s1[ 1 : ], s1[ 0 ], acc)


if __name__ == '__main__':
    # tt.penup()
    # tt.left(90)
    # draw_part()
    # tt.left(180)
    # draw_part()
    # tt.done()

    # c = "CompSci44EIOU"
    # print(c[0:13:4])

    x = 15
    t = '22'
    print(t > x)
