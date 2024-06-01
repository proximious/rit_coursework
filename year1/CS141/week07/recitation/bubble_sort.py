"""
CSCI-141 Computer Science 1 Recitation Exercise
07-Searching and Sorting

This is starter code for the implementation of bubblesort.
You need to complete the stubbed-out sort() function.
Do NOT add functions or modify the function definitions!
"""


def bubble_sort(lst):
    """
    Takes a list of numbers as input and returns the sorted
    version of that list.
    :param: list of unsorted elements
    :return: the sorted list
    """
    n = len(lst)
    swapped = True
    while swapped:
        swapped = False
        for i in range(1, n):
            if lst[i - 1] > lst[i]:
                lst[i], lst[i - 1] = lst[i - 1], lst[i]
                swapped = True
    return lst


def main():
    """
    Reads input from a file, one line at a time, converts
    each line into a list of numbers.txt
    :return:
    """
    file = open("input.txt")
    int_values = []
    for line in file:
        vline = line.strip()
        values = vline.split(",")
        for val in values:
            int_values += [int(val)]
    print(int_values)
    print(bubble_sort(int_values))


if __name__ == '__main__':
    main()
