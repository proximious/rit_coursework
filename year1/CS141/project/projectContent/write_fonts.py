import turtle


def init():
    turtle.speed(0)
    turtle.up()
    turtle.backward(120)
    turtle.left(90)
    turtle.forward(70)
    turtle.right(90)
    turtle.down()


def move_to():
    turtle.up()
    turtle.right(90)
    turtle.forward(30)
    turtle.left(90)
    turtle.down()


def main():
    turtle.setup(300, 300)
    init()
    turtle.hideturtle()

    turtle.color('black')
    turtle.write('Arial, 14', font=("Arial", 14), align = 'left')
    move_to()
    turtle.write('Comic Sans MS, size 14', font=('Comic Sans MS', 14), align = 'left')
    move_to()
    turtle.write('Lucida Grande, size 14', font=('Lucida Grande', 14), align = 'left')
    move_to()
    turtle.write('Tahoma, size 14', font=('Tahoma', 14), align = 'left')
    move_to()
    turtle.write('Verdana, size 14', font=('Verdana', 14), align = 'left')
    move_to()
    turtle.write('Helvetica, size 14', font=('Helvetica', 14), align = 'left')
    move_to()
    turtle.write('Times New Roman, size 14', font=('Times New Roman', 14), align = 'left')

    turtle.done()


if __name__ == '__main__':
    main()
