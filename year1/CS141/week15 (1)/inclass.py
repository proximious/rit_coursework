import turtle as tt


def main():
    tt.speed(0)
    acc = 0
    while acc <= 360:
        tt.forward(5)
        tt.right(1)
        acc += 1


if __name__ == '__main__':
    main()