import turtle as tt


def init():
    """
    places the tt in the appropriate position to begin the recursion
    :return:
    """
    tt.speed(0)
    tt.left(90)
    tt.up()
    tt.backward(150)
    tt.down()


def triangle(length):
    """
    turtle is facing north and is up
    starts on bottom corner of upside down triangle
    :param length:
    :return:
    """
    tt.down()
    tt.left(30)
    tt.forward(length)
    tt.right(120)
    tt.forward(length)
    tt.right(120)
    tt.forward(length)
    tt.right(150)
    tt.up()


def triangles(length, depth):

    # while depth > 0:
    #     triangle(length)
    #     tt.left(30)
    #     tt.forward(length)
    #     tt.right(30)
    #
    #     triangle(length / 2)
    #
    #     tt.right(90)
    #     tt.forward(length)
    #     tt.left(90)
    #
    #     length /= 2
    #     depth -= 1 # depth = depth - 1

    length = int(length)
    while depth > 0:
        triangle(length)
        tt.left(30)
        tt.forward(length)
        tt.right(30)

        triangle(length / 2)

        tt.right(90)
        tt.forward(length)
        tt.left(90)

        triangle(length / 2)

        tt.left(150)
        tt.forward(length)
        tt.right(150)

        depth -= 1
        length /= 2


if __name__ == '__main__':
    #length = int(input("How long would you like the triangles to be? "))
    #depth = int(input("How many times would you like the triangles to be repeated? "))
    init()
    triangles(200, 3)
    tt.done()
