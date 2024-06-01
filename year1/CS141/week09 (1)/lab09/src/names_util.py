"""
CSCI-141 Week 9: Dictionaries & Dataclasses
Lab: 07-BabyNames
Author: RIT CS
Author: Alex Iacob

This utility module is used by the main programs to perform the work on the
data and return the desired results.
"""

from dataclasses import dataclass
from operator import attrgetter, itemgetter
from typing import List


# the range of valid years of data
START_YEAR = 1880
END_YEAR = 2018

# indices into the name data when splitting by commas
NAME_INDEX = 0
GENDER_INDEX = 1
COUNT_INDEX = 2

# gender strings
FEMALE = 'F'
MALE = 'M'


def get_filename(year):
    """
    Returns a formatted string for the filename that is associated with a
    given year.
    :param year: the desired year
    :return: a string, e.g. 'yob1990.txt' if year is 1990
    """
    return f'yob{year}.txt'


"""
PROBLEM 1: tops_in_year
"""


@dataclass
class NameInfo:
    """
    A NameInfo structure is used to represent three pieces of data that are
    required by the tops_in_year main program.  For each name we want
    to record the gender and the total count of babies that were born
    in a particular year.
    """
    name: str  # baby's first name
    gender: str  # gender of baby, ('F' = female, 'M' = male)
    count: int  # total babies with the same name and gender born in a year


def get_tops_in_year(year, num):
    """
    For a particular year, find and return the top 'num' babies that were
    born in that year, sorted in descending order by counts.  By default
    'num' is 10.
    :param year: the year
    :param num: the top number of babies
    :return: a list of NameInfo objects containing the top babies for that
        year in descending order by count.
    """
    names = []

    with open(get_filename(year), "r")as file:
        for line in file:
            top_year_values = line.strip().split(',')
            name = top_year_values[NAME_INDEX]
            gender = top_year_values[GENDER_INDEX]
            count = int(top_year_values[COUNT_INDEX])

            names.append(NameInfo(name, gender, count))

        names.sort(key=attrgetter('count'), reverse=True)

    return names[0:num]


"""
PROBLEM 2: top_name_year
"""


@dataclass
class NameCount:
    """
    A NameCount structure is used to store the information required by
    the top_name_year main program.  In the year given, the top baby
    name of the year by total count, combining both genders, is to be
    found and returned.
    """
    name: str  # baby's first name
    count: int  # total babies with the same name (combining genders) in a year
    percentage: float  # how popular was the name in relation to all babies born that year


def get_top_name_year(year):
    """
    For a given year, find and return the top name, combining both genders if
    a name appears as both female and male.
    :param year: the year
    :return: a NameCount object with the top name information
    """
    names = dict()
    total_quantity = 0
    temp_max_count = 0
    temp_max_name = ''

    with open(get_filename(year), "r")as file:
        for line in file:
            top_name_values = line.split(',')
            name = top_name_values[NAME_INDEX]
            count = int(top_name_values[COUNT_INDEX])

            if name in names:
                names[name] += count
            else:
                names[name] = count
            total_quantity += count

    for name in names:
        if names[name] > temp_max_count:
            temp_max_name = name
            temp_max_count = names[name]

    percentage = (temp_max_count / total_quantity) * 100

    return NameCount(temp_max_name, temp_max_count, percentage)


"""
PROBLEM 3: top_10_years
"""


@dataclass
class TopNamesYear:
    """
    A TopNamesYear structure is used by the top_10_years main program in order to find
    the top 'num' names over a range of years by total count.  It stores the
    female and male list of top names (strings).
    """
    females: List[str]  # list of top female names in descending order
    males: List[str]  # list of top male names in descending order


def get_top_years(start_year, end_year, num=10):
    """
    For a range of years, find and return the top 'num' female and male babies
    born over that range, in descending order.  By default 'num' is 10.
    :param start_year: the starting year (assumed to be valid)
    :param end_year: the ending year (assumed to be valid)
    :param num: the number of top names for each gender to generate
    :return: a TopNamesYear that holds the top female and male names in
    separate lists of strings.
    """
    year = start_year
    male_dict = dict()
    female_dict = dict()
    while year <= end_year:
        with open(get_filename(year), "r")as file:
            for line in file:
                top_years = line.split(',')
                name = top_years[NAME_INDEX]
                gender = top_years[GENDER_INDEX]
                count = int(top_years[COUNT_INDEX])

            # creating the dictionaries
                if gender == MALE:
                    if name in male_dict:
                        male_dict[name] += count
                    else:
                        male_dict[name] = count
                else:
                    if name in female_dict:
                        female_dict[name] += count
                    else:
                        female_dict[name] = count
        year += 1

    # turning dictionaries into list of tuples
    male_list = list(male_dict.items())
    males = sorted(male_list, key=itemgetter(1), reverse=True)

    female_list = list(female_dict.items())
    females = sorted(female_list, key=itemgetter(1), reverse=True)

    male_name_list = []
    for i in range(0, num):
        male_name_list.append(males[i][0])

    female_name_list = []
    for i in range(0, num):
        female_name_list.append(females[i][0])

    return TopNamesYear(female_name_list, male_name_list)
