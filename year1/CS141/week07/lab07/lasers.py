"""
Lab 6: Laser Towers
Alex Iacob
ai9388@rit.edu
"""

import sys


def tuple_create(num):
    """
    creates a tuple of all of towers' statuses, index, and sum
    starts off as an empty list, and for each index where a tower can be created,
    its status and sum is determined.
    :param num:
    :return: complete list
    """
    lst = []
    # for up towers
    for i in range(1, len(num) - 2):
        total_sum = int(num[i - 1]) + int(num[i + 1]) + int(num[i + 2])
        tower_status = 'upward'
        values = (total_sum, i, tower_status)
        lst.append(values)

    # for down towers
    for i in range(2, len(num) - 1):
        total_sum = int(num[i - 2]) + int(num[i - 1]) + int(num[i + 1])
        tower_status = 'downward'
        values = (total_sum, i, tower_status)
        lst.append(values)

    return lst


def sorting(lst):
    """
    function sorts the tuples given in descending order using bubble sort
    function sorts the tuples by their total sums
    :param lst: unsorted list
    :return:
    """
    n = len(lst)
    swapped = True
    while swapped:
        swapped = False
        for i in range(1, n):
            if lst[i - 1][0] < lst[i][0]:
                lst[i], lst[i - 1] = lst[i - 1], lst[i]
                swapped = True
    return lst


def splicing(lst, num):
    """
    splices the function of any repeating indexes
    :param lst: sorted list without being spliced
    :param num: the
    :return:
    """
    spliced_lst = []
    idx_lst = []
    k = 1
    for i in range(len(lst)):
        if lst[i][1] == 1 or lst[i][1] == (len(num) - 2):
            spliced_lst.append(lst[i])
            idx_lst.append(i)

        for j in range(k, len(lst)):
            if i == j:
                pass
            elif i in idx_lst:
                break
            elif lst[i][1] == lst[j][1]:
                spliced_lst.append(lst[i])
                idx_lst.append(i)
                k += 1
            else:
                pass

    return spliced_lst


def main():
    """
    asks the user for the file name and creates an empty list to be filled with tuples from the function 'tuple_create'
    :return:
    """
    # inputs
    file_name = sys.argv[1]
    tower_quantity = sys.argv[2]

    # creating an empty list
    total_list = []

    with open(file_name, "r")as file:
        """
        opens the text file and reads the line in the file
        creates a list that is filled with tuples from 'create_tuple'
        program ends if there are less than 4 numbers in the text file
        """
        num = file.readline().split()

        for n in num:
            total_list.append(int(n))

        if len(total_list) < 4:
            print('Not enough numbers in the text file for towers to be placed, please add more.')
        else:
            if int(tower_quantity) > len(total_list):
                print("That many towers cannot be placed, choose a smaller number.")
            else:
                # creates an unsorted list that was made by 'tuple_create' and prints out the unordered list of numbers
                #
                unsorted_list = tuple_create(num)
                print('List of numbers: ', num)
                print(tower_quantity, 'towers')

                # sorts the list by total sum
                sorted_list = sorting(unsorted_list)

                # splices/parses the sorted list to get rid of the lower sum with the same index
                spliced_list = splicing(sorted_list, num)

                # sorting the spliced list to make sure the values are in decreasing order
                sorted_spliced_list = sorting(spliced_list)

                # prints out a statement with each respective value for each tower
                for w in range(int(tower_quantity)):
                    print('Tower is facing ' + sorted_spliced_list[w][2] + ' at index ' +
                          str(sorted_spliced_list[w][1]) + ' scoring a total of ' + str(sorted_spliced_list[w][0]))


if __name__ == "__main__":
    main()

"""
Steps to complete lab:
    ask the user for the file name and number of towers---------------------------------*
    open the file-----------------------------------------------------------------------*
    read the lines----------------------------------------------------------------------*
    if there are not enough numbers, terminate the program------------------------------*
    create a list with the numbers and indexes of the text file-------------------------*
    create a function that determines the sum, index, and status of the tower-----------*
    create a function that sorts the tuples by their total sum--------------------------*
    return the sorted list--------------------------------------------------------------*
    create a function that splices the smaller total sum *if* the indexes are the same--*
    return the entire sorted list with unique indexes-----------------------------------*
    splice the sorted list up until the user's input for number of towers---------------*
    return the spliced list and the number of towers------------------------------------*
    make the list look nicer------------------------------------------------------------*
"""
