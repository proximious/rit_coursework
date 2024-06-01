"""
Alex Iacob
ai9388@rit.edu

SI Session Kahoot questions
"""

import turtle as t


# 5A
def iterate(length, depth):
    while depth > 1:
        t.forward(length)
        t.left(90)
        depth = depth - 1


def iterate2(num, depth):
    result = 0
    while depth > 0:
        if result % 2 == 0:
            result = result + num
        else:
            result = result + 1
        depth = depth - 1
    print(result)


# 5B
def syntax_question():
    for i in range(101):
        print(i)


def foo():
    luck = "Good Luck!"
    walk = "Walk in the Park"
    x = 4
    y = 9

    print(luck[-5:-2])


def file_reader():
    f = open("words.txt")
    total_characters = 0
    total_lines = 0
    for line in f:
        line = line.strip()
        total_lines += 1
        for ch in line:
            total_characters += 1

    print("total lines = ", total_lines)
    print("total chars = ", total_characters)


def series(k):
    total = 0
    for i in range(k):
        total += (1 / 2) ** i
    return total


def find_the_q(filename):
    num_q = 0
    file = open(filename, "r")
    for line in file:
        for char in line:
            if char == "q":
                num_q += 1
    file.close()
    return num_q


def foo():
    s = 'sea and sun'
    print(s[2:])
    print(s[4:7])

def splitter(text):
    split_text = text.split(',')
    print(len(split_text))

if __name__ == '__main__':
    # t = '0, 0, 0, 0, 0.5454, 0.08314, 0, 0, 0, 0.2, 0, 0.1, 0.1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.001, 0.002, 0, 0, 0, ' \
    #     '0.05, 0.1, 0, 0.1, 0.001, 0.001, 0.1, 0, 0.1, 0, 0, 0, 0, 0 '
    # splitter(t)

    iterate2(5, 3)