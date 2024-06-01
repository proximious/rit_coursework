"""
@author: SI - Alex Iacob ai9388@rit.edu
@filename: main.py

practice for SI Session:drawing hexagons
"""

import turtle as t

def hexa():
    t.forward(60)
    t.right(60)


"""
pre: pen down, facing east, pen color is black,pen size to default
post: facing north-east, others remain the same
"""
def draw_hexagon():
    t.fillcolor('blue')
    t.pensize(10)
    t.begin_fill()
    hexa()
    hexa()
    hexa()
    hexa()
    hexa()
    hexa()
    t.forward(60)
    t.left(60)
    t.end_fill()

def hexagon():
    draw_hexagon()
    draw_hexagon()
    draw_hexagon()
    draw_hexagon()
    draw_hexagon()
    draw_hexagon()

def process_file(f):
    acc = 0
    for line in f:
        if line[0] == 'A' or line[0] == 'a':
            print(line)
            acc += 1
        else:
            acc += 1
    return acc

def main():
    f = open('words.txt')
    num = process_file(f)
    print(num)

main()
