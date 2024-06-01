"""
september 30 2019 lecture
collatz theorem
"""


def run(n: int) -> int:
    """
    Performs the collatz conjecture calculation
    :param n: initial value that the user inputs
    :return: the number of steps it took to get to 1
    """
    if n < 1:
        return 0
    elif n == 1:
        return 0
    elif n % 2 == 0:
        return 1 + run(n // 2)
    else:
        return 1 + run((n * 3) + 1)


def song():
    with open('song.txt', "r") as file:
        line_num = 0
        char_num = 0
        for line in file:
            line_num += 1
            for char in line:
                char_num += 1
        print('the number of characters is', char_num)
        print('the number of lines is', line_num)


if __name__ == "__main__":
    song()
