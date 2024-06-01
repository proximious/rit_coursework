import turtle as t


def spiral(size, depth):
    if depth == 0:
        t.done()

    if size == 0:
        t.done()

    t.forward(size)
    t.right(90)
    spiral(size - 5, depth - 1)


if __name__ == '__main__':
    s = int(input("size?"))
    d = int(input("depth?"))
    spiral(s, d)
