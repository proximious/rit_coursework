"""
CSCI-141 Week 9: Dictionaries & Dataclasses
Lab: 07-BabyNames
Author: RIT CS

This is the third program that computes the top 10 female and top 10 male
baby names over a range of years.

The program requires two command line arguments, the start year, followed by
the end year.

Assuming the working directory is set to the project's data/ directory
when run:

$ python3 top_10_years 1880 2018
TOP 10 BABY NAMES OF 1880-2018
RANK      FEMALE NAME         MALE NAME
1         Mary                James
2         Elizabeth           John
3         Patricia            Robert
4         Jennifer            Michael
5         Linda               William
6         Barbara             David
7         Margaret            Joseph
8         Susan               Richard
9         Dorothy             Charles
10        Sarah               Thomas
"""

import names_util       # get_most_popular_name_year, START_YEAR, END_YEAR
import sys              # argv, stderr.write

# constants for the printed table
RANK = 'RANK'
FEMALE_NAME = 'FEMALE NAME'
MALE_NAME = 'MALE NAME'


def main():
    """
    The main program reads the command line, calls the function to compute
    and return the results, and then prints out the results.
    """
    # verify the command line contains the arguments
    if len(sys.argv) != 3:
        sys.stderr.write('Usage: python3 top_10_years.py start-year end-year')
    else:
        # convert the start and end year from command line strings to integers
        start_year, end_year = int(sys.argv[1]), int(sys.argv[2])
        # verify start year is less than or equal to the end year
        if start_year > end_year:
            sys.std.write('Start year must be less than or equal to end year')
        # verify both years fall within the valid range for the data
        elif start_year < names_util.START_YEAR or end_year > names_util.END_YEAR:
            sys.stderr.write('Year must be between ' + str(names_util.START_YEAR) +
                             ' and ' + str(names_util.END_YEAR))
        else:
            # call names_util.get_top_years and print the results
            top_year = names_util.get_top_years(start_year, end_year)
            print(f'TOP 10 BABY NAMES OF {start_year}-{end_year}')
            print(f'{RANK:<10}{FEMALE_NAME:<20}{MALE_NAME:<20}')
            rank = 1
            for female, male in zip(top_year.females, top_year.males):
                print(f'{rank:<10}{female:<20}{male:<20}')
                rank += 1


if __name__ == '__main__':
    main()
