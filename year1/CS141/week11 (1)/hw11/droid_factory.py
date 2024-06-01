"""
Alex Iacob
ai9388@rit.edu
Homework 8: Droid Factory
"""

import cs_queue
from dataclasses import dataclass
import sys


@dataclass
class Droids:
    head: bool
    body: bool
    arms: bool
    legs: bool
    serial_number: int


def conveyor(file_name):
    """
    creates a queue with all of the droid parts in the file
    :param file_name:
    :return: queue with all parts
    """
    conveyor_belt = cs_queue.make_empty_queue()
    with open(file_name, "r") as file:
        for line in file:
            indiv_line = line.strip()
            cs_queue.enqueue(conveyor_belt, indiv_line)
        return conveyor_belt


def build_droid(serial_number, conveyor_belt):
    """
    builds only one droid
    :param serial_number:
    :param conveyor_belt:
    :return: dataclass
    """
    droid = Droids(False, False, False, False, serial_number)
    while cs_queue.is_empty(conveyor_belt) is False:
        current_part = cs_queue.front(conveyor_belt)

        if current_part == 'head' and droid.head is False:
            cs_queue.dequeue(conveyor_belt)
            print('\t attaching', current_part, '...')
            droid.head = True

        elif current_part == 'body' and droid.body is False:
            cs_queue.dequeue(conveyor_belt)
            print('\t attaching', current_part, '...')
            droid.body = True

        elif current_part == 'arms' and droid.arms is False:
            cs_queue.dequeue(conveyor_belt)
            print('\t attaching', current_part, '...')
            droid.arms = True

        elif current_part == 'legs' and droid.legs is False:
            cs_queue.dequeue(conveyor_belt)
            print('\t attaching', current_part, '...')
            droid.legs = True

        else:
            cs_queue.dequeue(conveyor_belt)
            print('\t', current_part, 'is not needed, placing back...')
            cs_queue.enqueue(conveyor_belt, current_part)

        # checks to see if the dataclass is true
        if droid.head is True and droid.body is True and droid.arms is True and droid.legs is True:
            return droid


def build_droids(conveyor_belt):
    """
    loops through the function as long as it is not empty and builds all of the droids
    possible with the parts given
    :param conveyor_belt:
    :return:
    """
    serial_number = 10000
    while cs_queue.is_empty(conveyor_belt) is False:
        print('Starting new droid assembly, serial number:', serial_number)
        if build_droid(serial_number, conveyor_belt):
            print('\t Combining parts...')
            print('Droid is built, serial number:', serial_number, '\n')
            serial_number += 1
        else:
            print('Insufficient parts for serial number:', serial_number, ',production has been stopped.')


def main():
    print('Input text file in the command prompt.')
    if len(sys.argv) != 2:
        print('Usage: python3 droid_factory.py file_name')
    else:
        file_name = sys.argv[1]
        print('Starting droid assembly...\n')
        conveyor_belt = conveyor(file_name)
        build_droids(conveyor_belt)
        print('\nDroid assembly is finished, clocking out.')


if __name__ == '__main__':
    main()
