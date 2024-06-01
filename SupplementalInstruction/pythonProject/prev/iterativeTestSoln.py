"""
Alex Iacob
ai9388@rit.edu

iterativeTestSoln.py
"""


def add(a, b):
    return a + b


def subtract(a, b):
    return a - b


def multiply(a, b):
    return a * b


def divide(a, b):
    return a / b


def math_operation(user_input, a, b):
    a = int(a)
    b = int(b)

    if user_input == "+":
        print(add(a, b))
    elif user_input == "-":
        print(subtract(a, b))
    elif user_input == "*":
        print(multiply(a, b))
    elif user_input == "/":
        print(divide(a, b))
    else:
        print("Faulty input\n")


if __name__ == '__main__':
    while True:
        print("What would you like to do? +, -, *, /")
        print("Enter 'q' for any input to quit\n")
        operator = input("operator: ")
        num1 = input("first number: ")
        num2 = input("second number: ")

        if operator == "q" or num1 == "q" or num2 == "q":
            print("Quitting...")
            break
        else:
            math_operation(operator, num1, num2)
