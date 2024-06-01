"""
quick_sort
"""

from typing import Tuple


def quick_sort(data: list) -> list:
    if len(data) < 2:
        return data[:]
    else:
        (smaller, equal, larger) = partition(data[0], data)
        return quick_sort(smaller) + equal + quick_sort(larger)


def partition(pivot, data: list) -> Tuple[list, list, list]:
    smaller = []
    equal = []
    larger = []

    for value in data:
        if value < pivot:
            smaller.append(value)
        elif value > pivot:
            larger.append(value)
        else:
            equal.append(value)

    return smaller, equal, larger


def main():
    data = [7,8,11,6,5,8,4,98,4,5]
    answer = quick_sort(data)
    print(data)
    print(answer)


if __name__ == "__main__":
    main()
