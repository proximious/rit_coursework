"""
file: q2.py
language: python3
author: Alex Iacob ai9388@rit.edu
description: Counts characters in lines of a file.
"""


def count_characters_in(file_name: str, c: str) -> str:
    """
    Read the named file and PRINT how many times a given character appears
    on each separate line of the file.
    :param file_name: the file whose lines are read
    :param c: the character whose occurrences per line are to be counted
    """
    quantity = 0
    with open(file_name, "r") as file:
        for line in file:
            for char in line:
                if char == c:
                    quantity += 1

    return str(quantity)


def tests():
    print("The letter a has appeared " + count_characters_in("disobedience.txt", 'a') + " times in the document.")
    print("The letter w has appeared " + count_characters_in("disobedience.txt", 'w') + " times in the document.")
    print("The character = has appeared " + count_characters_in("q2.py", '=') + " times in the document.")


if __name__ == "__main__":
    tests()
