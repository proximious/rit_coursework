"""
Alex Iacob
ai9388@rit.edu
Homework 8: Bumper sort
"""

import random
import time
from merge_sort import merge_sort
from quick_sort import quick_sort


def bumper_sort(data: list, k: int) -> list:
    """
    :param data: unsorted list
    :param k: max value that will be found in the data
    :return: sorted list
    """
    result = []
    hist = [0] * (k + 1)
    for v in data:
        hist[v] += 1
    for i in range(len(hist)):
        for j in range(hist[i]):
            result.append(i)

    return result


def determining_time():
    """
    determines the time for each of the functions while using lists with lengths
    of 1000 and 1000000
    :return: a series of times that it takes to sort each list
    """
    small_data = []
    small_temp = 0
    large_data = []
    large_temp = 0

    while small_temp <= 1000:
        small_data.append(random.randrange(0, 300))
        small_temp += 1

    while large_temp <= 1000000:
        large_data.append(random.randrange(0, 300))
        large_temp += 1

    print('Unsorted:', small_data)
    print('Sorted:', bumper_sort(small_data, 300))

    # determining the times for the small data
    small_start1 = time.process_time()
    bumper_sort(small_data, 300)
    small_end1 = time.process_time()
    print("Bumper sort in :", small_end1 - small_start1)

    small_start2 = time.process_time()
    quick_sort(small_data)
    small_end2 = time.process_time()
    print("Quick sort in :", small_end2 - small_start2)

    small_start3 = time.process_time()
    merge_sort(small_data)
    small_end3 = time.process_time()
    print("Merge sort in :", small_end3 - small_start3)

    small_start4 = time.process_time()
    small_data.sort()
    small_end4 = time.process_time()
    print("Sorted in :", small_end4 - small_start4)

    # determining the times for large data
    large_start1 = time.process_time()
    bumper_sort(large_data, 300)
    large_end1 = time.process_time()
    print("Bumper sort in :", large_end1 - large_start1)

    large_start2 = time.process_time()
    quick_sort(large_data)
    large_end2 = time.process_time()
    print("Quick sort in :", large_end2 - large_start2)

    large_start3 = time.process_time()
    merge_sort(large_data)
    large_end3 = time.process_time()
    print("Merge sort in :", large_end3 - large_start3)

    large_start4 = time.process_time()
    large_data.sort()
    large_end4 = time.process_time()
    print("Sorted in :", large_end4 - large_start4)


def main():
    data = [2, 5, 3, 0, 2, 3, 0, 3]
    print('Unsorted:', data)
    print('Sorted:', bumper_sort(data, 300))
    determining_time()


if __name__ == "__main__":
    main()
