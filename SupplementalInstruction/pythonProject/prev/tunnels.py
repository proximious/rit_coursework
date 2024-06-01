"""
tunnels.py
Draws a lot of circles that makes it look like a tunnel
"""

import turtle as t

def draw_tunnels(radius, depth):
    if depth <= 0:
        return 0
    else:
        t.circle(radius)
        return draw_tunnels(radius - 20, depth - 1)


if __name__ == '__main__':
    t.speed(0)
    draw_tunnels(200, 20)
    t.done()
