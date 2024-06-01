"""
This program includes a function to test
whether a given codeword string is a valid encoding
of a given original string using a Caesar cipher.

It returns an integer value, indicating the
forward shift (e.g A (original) -> B (codeword)
is a forward shift of 1).

It is assumed that the original string and codeword string
both comprise only capitalized English letters A-Z.

It is also assumed that neither the original string
nor the codeword string is the empty string.

A valid encoding results in a return value
in the range 0-25, indicating the forward shift
value used in the cipher.
A return value of 0 indicates a valid Caesar cipher
that used no shift at all.

A -1 is returned to indicate that the given
codeword string is not a valid encoding of the original
string using a Caesar cipher.

The function has bug(s).

There are no tests (yet).

Your job is to
1) include in this program a sufficient
suite of pass/fail tests to thoroughly
test the function and expose all error(s).

2) Generate a screenshot that
demonstrates your use of a debugger
to step through the function. Specifically it should
illustrate the execution point of a test at
which the function makes (or is about to make)
a mistake.

3) fix the code and document your fix(es).
Include additional tests if you feel it
necessary to thoroughly test the function.

You will submit your updated version of this
file (along with a separate document containing
the screenshot and answered questions).

File:  test_debug1.py
Author: CS @ RIT
Author: Alex Iacob

"""


def test_preconditions(original, codeword):
    """
    tests the preconditions by making sure their ASCII characters are within the correct range
    :param original:
    :param codeword:
    :return:
    """
    # sets temporary variable to 0
    result = 0
    # checks whether the characters in original and codeword are capital letters
    # if so, then result gets +1 for every capital letter
    for c in original:
        if 65 <= ord(c) <= 90:
            result += 1
        else:
            result = 0

    for c in codeword:
        if 65 <= ord(c) <= 90:
            result += 1
        else:
            result = 0

    # checks whether the lengths of the inputs are the same
    # if they are, then the program immediately returns false
    # otherwise if the result is equal to the lengths of the combined words, the function returns true
    if len(original) != len(codeword):
        return False
    else:
        if result == len(original) + len(codeword):
            return True
        else:
            return False


def caesar(original, codeword):
    """
    Tests whether the provided codeword string
    represents a valid encoding of the original
    string using a Caesar cipher.
    Pre-condition:  both the codeword and original
    are strings containing only capitalized English
    letters.
    Pre-condition:  neither the codeword nor the
    original string is the empty string.
    :param original: The original string.
    :param codeword: The encoded string.
    :return: Integer in the range 0-25 if the codeword
    represents a valid encoding of the original
    string using a Caesar cipher.
    """
    # test_preconditions tests whether the
    if test_preconditions(original, codeword):
        shift = ord(codeword[0]) - ord(original[0])

        for index in range(len(codeword)):
            if ord(codeword[index]) - ord(original[index]) != shift:
                return False
        return shift
    else:
        return False


def tests():
    print('The shift for ABCD and BCDE is', caesar('ABCD', 'BCDE'))

    print('The shift for WXYZ and BCDE is', caesar('WXYZ', 'BCDE'))

    print('The shift for ABC and XYZ is', caesar('ABC', 'XYZ'))

    print('The shift for ABCD and ZBA is', caesar('ABCD', 'ZAB'))

    print('The shift for BDED and ACDC is', caesar('BDED', 'ACDC'))

    print('The shift for BAC and ABC is', caesar('BAC', 'ABC'))


if __name__ == "__main__":
    tests()
