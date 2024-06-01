"""
7B SI Session
10/7/21
File makes a binary search on a sorted list
"""


def b_search(lst, num):
    """
    return the index of the num in list using binary search
    on sorted list
    :param lst: the list
    :param num: the goal number
    :return: index of goal number
    """
    midpoint = len(lst) // 2 # gets the midpoint of the list

    if lst[midpoint] == num:
        print(lst[midpoint])
        return midpoint
    elif lst[midpoint] < num:
        print(lst[midpoint + 1:])
        return b_search(lst[midpoint + 1:], num)
    elif lst[midpoint] > num:
        print(lst[: midpoint])
        return b_search(lst[: midpoint], num)


if __name__ == '__main__':
    lst = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    num = int(input("enter number: "))
    b_search(lst, num)
