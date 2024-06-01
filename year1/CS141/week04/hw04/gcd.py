"""
Alex Iacob
Homework 4: GCD
ai9388@rit.edu
"""

import math


def gcd_rec(a, b):
    """
    takes the greatest common denominator of the two numbers with a and b as the numbers
    ***uses recursion***
    if b is equal to zero, then the function will simply return a
    :param a:
    :param b:
    :return:
    """
    if b == 0:
        return a
    else:
        return gcd_rec(b, a % b)


def gcd_iter(a, b):
    """
    takes the greatest common denominator
    uses gcd as a temporary variable
    ***uses loops***
    :param a:
    :param b:
    :return:
    """
    while b != 0:
        temp_gcd = b
        b = a % b
        a = temp_gcd

    return a


def test_gcd_rec():
    print("Test cases for the recursive function")
    print("gcd_rec(6, 9) = " + str(gcd_rec(6, 9)))
    print("gcd_rec(3, 9) = " + str(gcd_rec(3, 9)))
    print("gcd_rec(12, 12) = " + str(gcd_rec(12, 12)))
    print("gcd_rec(23, 67) = " + str(gcd_rec(23, 67)))
    print("gcd_rec(112, 30) = " + str(gcd_rec(112, 30)))
    print("gcd_rec(60, 99) = " + str(gcd_rec(60, 99)))
    print("gcd_rec(45, 77) = " + str(gcd_rec(45, 77)))
    print("...")


def test_gcd_iter():
    print("Test cases for the iterative function")
    print("gcd_iter(14, 21) = " + str(gcd_iter(14, 21)))
    print("gcd_iter(12, 144) = " + str(gcd_iter(12, 144)))
    print("gcd_iter(23, 23) = " + str(gcd_iter(23, 23)))
    print("gcd_iter(2, 3) = " + str(gcd_iter(2, 3)))
    print("gcd_iter(56, 32) = " + str(gcd_iter(56, 32)))
    print("gcd_iter(65, 84) = " + str(gcd_iter(65, 84)))
    print("gcd_iter(54, 99) = " + str(gcd_iter(54, 99)))
    print("...")


def main():
    # prompts the user to select a function by typing 1 or 2
    print("Select a GCD Function \n 1: Iteration \n 2: Recursive")
    # ui is short for user input, here the user would type 1 or 2
    ui = input("")

    # asks the user for their numerical input
    a = input("Input the first number: ")
    b = input("Input the second number: ")

    # turns the inputs into numbers.txt
    a = int(a)
    b = int(b)
    ui = int(ui)

    # if the user typed 1 for their input, the iterative method would be used
    # if not, the recursive method would be used
    if ui == 1:
        result = gcd_iter(a, b)
    else:
        result = gcd_rec(a, b)

    print("The GCD is " + str(result))


if __name__ == "__main__":
    # runs the test functions
    test_gcd_rec()
    test_gcd_iter()

    main()