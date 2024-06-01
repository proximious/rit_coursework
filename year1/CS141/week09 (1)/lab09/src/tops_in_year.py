"""
CSCI-141 Week 9: Dictionaries & Dataclasses
Lab: 07-BabyNames
Author: RIT CS

This is the first program that prints out the top baby names, combining
genders, for a given year.  The baby names are in a list of NameInfo
objects that are ordered by descending count, e.g. the first name is the
most popular baby name.

The program requires two command line arguments, the year, followed by
the number of top names to display.

Assuming the working directory is set to the project's data/ directory
when run:

$ python3 tops_in_year.py 2018 5
TOP 5 BABY NAMES IN 2018
RANK      NAME                GENDER    COUNT
1         Liam                M         19837
2         Emma                F         18688
3         Noah                M         18267
4         Olivia              F         17921
5         Ava                 F         14924
"""

import names_util  # top, START_YEAR, END_YEAR
import sys  # argv, stderr.write

# constants for the printed table
RANK = 'RANK'
GENDER = 'GENDER'
NAME = 'NAME'
COUNT = 'COUNT'


def main():
    """
    The main program reads the command line, calls the function to compute
    and return the results, and then prints out the results.
    """
    # verify the command line contains the arguments
    if len(sys.argv) != 3:
        sys.stderr.write('Usage: python3 tops_in_year.py year num')
    else:
        # convert year and num from command line strings to integers
        year, num = int(sys.argv[1]), int(sys.argv[2])
        # make sure the year provided is in the valid range for our data
        if year < names_util.START_YEAR or year > names_util.END_YEAR:
            sys.stderr.write('Year must be between ' + str(names_util.START_YEAR) +
                             ' and ' + str(names_util.END_YEAR))
        else:
            # call names_util.get_tops_in_year and print the results
            print(f'TOP {num} BABY NAMES IN {year}')
            print(f'{RANK:<10}{NAME:<20}{GENDER:<10}{COUNT:<10}')
            rank = 1
            for name in names_util.get_tops_in_year(year, num):
                print(f'{rank:<10}{name.name:<20}{name.gender:<10}{name.count:<10}')
                rank += 1


if __name__ == '__main__':
    main()
