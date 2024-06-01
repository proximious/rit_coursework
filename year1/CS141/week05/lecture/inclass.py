"""
ai9388@rit.edu
Find the length of the longest word in a file.
"""


def longest(line: str) -> int:
    """
    What is the longest word on this line?
    Words are separated by white space.
    :param line: the sequence of words
    :return: the maximum length of the longest word
    """

    maximum = 0
    count = 0

    for c in line:
        if c.isspace():
            if count > maximum:
                maximum = count
            count = 0
        else:
            count += 1

    if count > maximum:
        maximum = count

    return maximum


def palindromes(word: str) -> bool:
    """
    :param word: the word to be tested
    :return: true if the word is reversible
    """
    while len(word) > 2:
        if word[0] != word[len(word) - 1]:
            return False
        word = word[1: len(word) - 1]
    return True


def redact(to_remove: str, file_name: str) -> None:
    """
    Print out the file contents, but with one word hidden with dashes
    :param to_remove: the word to hide
    :param file_name: the file to read
    :return:
    """
    with open(file_name, "r") as file:
        for line in file:
            print(line)
            word = line.strip()
            if word == to_remove:
                word = '-----'
            print(word)


def test_longest():
    for line in "Sphinx of black quartz, judge my vow",:
        print(line + ":", longest(line))


def test_palindrome():
    for word in "joke", "racecar":
        print(word + ":", palindromes(word))


def test_redact():
    file_name = input("what is the name of the txt file you would like to change? ")
    redacting_word = input("What word would you like to remove from the file? ")
    redact(redacting_word, file_name)


if __name__ == "__main__":
    # test_longest()
    # test_palindrome()
    test_redact()
