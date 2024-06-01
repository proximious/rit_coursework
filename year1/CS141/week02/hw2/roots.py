"""
Alex Iacob

"""
import math


def quadratic_formula(a, b, c):
    # creating the discriminant(d), if it is positive: 2 roots | negative: no roots | zero: one root
    d = (b ** 2) - (4 * a * c)

    # printing the function using the user's input
    print("Your function was (" + str(a) + ")x^2 + (" + str(b) + ")x + (" + str(c) + ")")

    # determining whether the discriminant is 0, positive, or negative
    # then giving the user the roots to their respective function

    if d > 0:  # since the discriminant is positive, there will always be 2 roots
        # root 1 and 2 are the quadratic equation in python syntax
        root1 = (-b + math.sqrt((b ** 2) - (4 * a * c))) / (2 * a)
        root2 = (-b - math.sqrt((b ** 2) - (4 * a * c))) / (2 * a)
        print("Your function has 2 roots")
        print("Your roots are x=" + str(root1) + " and x=" + str(root2))
        print("...")  # this is here for stylistic purposes

    elif d == 0:  # since the discriminant is 0, there will always be 1 root
        root1 = (-b + math.sqrt((b ** 2) - (4 * a * c))) / (2 * a)
        print("Your function has 1 root")
        print("Your root is x=" + str(root1))
        print("...")  # this is here for stylistic purposes

    else:  # since the discriminant is negative, there will always be no roots
        print("Your function has 0 roots")
        print("...")  # this is here for stylistic purposes

# functions with 2 roots
# quadratic_formula(1, 5, 6) roots:-2, -3
# quadratic_formula(1, 6, -72) roots:6, -12
# quadratic_formula(2, -9, 10) roots:2.5, 2
# quadratic_formula(6, -2, -28) roots:2.33, -2

# functions with 1 root
# quadratic_formula(1, -2, 1) root: 1
# quadratic_formula(1, -6, 9) root: 3
# quadratic_formula(1, 14, 49) root:-7
# quadratic_formula(3, -6, 3) root: 1

# functions with no roots
# quadratic_formula(1, 4, 5)
# quadratic_formula(1, -3, 10)
# quadratic_formula(5, 3, 5)
# quadratic_formula(1, 3, -5)
