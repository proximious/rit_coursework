"""
5A SI Session
"""


def add(a, b):
    return a + b


def subtract(a, b):
    return a - b


def multiply(a, b):
    return a * b


def divide(a, b):
    return a / b


if __name__ == '__main__':
    print("What would you like to do? +, -, *, /")
    print("Enter 'q' for any input to quit")

    operator = input("operator: ")


    while operator != "q":

        n1 = input("first number: ")
        if n1 == "q":
            break
        n1 = int(n1)

        n2 = input("second number: ")
        if n2 == "q":
            break
        n2 = int(n2)

        if operator == "+":
            print(add(n1, n2))
        elif operator == "-":
            print(subtract(n1, n2))
        elif operator == "*":
            print(multiply(n1, n2))
        elif operator == "/":
            print(divide(n1, n2))

        print("What would you like to do? +, -, *, /")
        print("Enter 'q' for any input to quit")
        operator = input("operator: ")


