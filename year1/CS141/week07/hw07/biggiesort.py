"""
Homework 7: Biggie sort
Alex Iacob
ai9388@rit.edu
"""


def biggie_sort(unsorted):
    """
    sorts a list of unsorted numbers.txt by using booleans
    the function begins with 'swapped' being equal to True, then immediately setting it to False
    when the number in front of the current index is larger than it, they swap
    once everything is swapped into place, swapped remains False, making it exit the loop
    :param unsorted:
    :return: unsorted:
    """
    n = len(unsorted)
    swapped = True
    while swapped is True:
        swapped = False
        for i in range(1, n):
            if unsorted[i - 1] > unsorted[i]:
                # swaps the numbers if the number before the current index is lower than it
                unsorted[i], unsorted[i - 1] = unsorted[i - 1], unsorted[i]
                swapped = True
    return unsorted


def main():
    """
    asks the user for the text file with random numbers.txt, then opens the file and creates an unordered list
    the line gets stripped of any possible spaces and gets appended into an unordered list
    at the end, both unsorted and sorted lists are printed
    :return:
    """
    file_name = str(input('What is the name of the text file? \n'))

    with open(file_name + '.txt', "r") as file:
        # creates an empty list
        unsorted_list = []

        for line in file:
            # strips the line of any excess spaces and splits them with a comma
            stripped_line = line.strip()
            numbers = stripped_line.split(',')

            for num in numbers:
                unsorted_list += [int(num)]

    print('Unsorted List:', unsorted_list)

    print('Sorted List:  ', biggie_sort(unsorted_list))


if __name__ == '__main__':
    main()
