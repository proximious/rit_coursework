"""
CSCI-141 Week 9: Dictionaries & Dataclasses
Lab: 07-BabyNames
Author: RIT CS

This is the second program that prints out the top baby name, ignoring
genders, for a given year.  The top baby name for the year comes
in a NameInfo dataclass object that holds the top name, the
number of babies born that year with that name, and what percentage
that name is in comparison to all the names.

The program requires one command line arguments, the year.

Assuming the working directory is set to the project's data/ directory
when run:

$ python3 2018
Liam was the most popular name in 2018!
Liam appeared 19860 times, accounting for 0.57% of all names that year
"""

import names_util       # get_top_name_year, START_YEAR, END_YEAR
import sys              # argv, stderr.write


def main():
    """
    The main program reads the command line, calls the function to compute
    and return the result, and then prints out details of the result
    """
    # verify the command line contains the arguments
    if len(sys.argv) != 2:
        sys.stderr.write('Usage: python3 top_name_year.py year')
    else:
        # convert the year from the command line string to an integer
        year = int(sys.argv[1])
        # make sure the year provided is in the valid range for our data
        if year < names_util.START_YEAR or year > names_util.END_YEAR:
            sys.stderr.write('Year must be between ' + str(names_util.START_YEAR) +
                             ' and ' + str(names_util.END_YEAR))
        else:
            # call names_util.get_top_name_year and print the results
            top_baby = names_util.get_top_name_year(year)
            print(f'{top_baby.name} was the most popular name in {year}!')
            print(f'{top_baby.name} appeared '
                  f'{top_baby.count} times, accounting for '
                  f'{top_baby.percentage:3.2f}% of all names that year')


if __name__ == '__main__':
    main()
