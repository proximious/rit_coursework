"""
Alex Iacob ai9388@rit.edu
SI Session 9/12/21
power.py
"""


def power(base, exponent):
    if exponent == 0:
        return 1
    else:
        return base * power(base, exponent - 1)


def fib(number):
    if number == 0:
        pass
    elif number == 1 or number == 2:
        return 1
    else:
        return fib(number - 1) + fib(number - 2)


def fact(number):
    if number == 0 or number == 1:
        return 1
    else:
        return number * fact(number - 1)


def hanoi(disks):
    if disks == 0:
        return disks
    else:
        return 2 * hanoi(disks - 1) + 1


if __name__ == '__main__':
    print(fib(5))