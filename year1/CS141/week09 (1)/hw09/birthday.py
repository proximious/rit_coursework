"""
Alex Iacob
ai9388@rit.edu
Homework 9: Birthdays
"""

from dataclasses import dataclass


@dataclass(frozen=True)
class Birthday:
    month: str
    day: int
    year: int


def read_file(file_name):
    """
    reads the file and appends each birthday to a list
    :param file_name:
    :return: list with all birthdays in the txt file
    """
    bday_list = list()
    with open(file_name, "r")as file:
        for line in file:
            fields = line.strip()
            month = fields[0:3]
            day = fields[3: len(line) - 5]
            year = fields[len(line) - 5:]

            bday_list.append(Birthday(month, int(day), int(year)))

    return bday_list


def build_dictionary(bday_list):
    """
    builds a dictionary along with how many times that said birthday appeared in the list
    :param bday_list:
    :return: a dictionary of all of the unique birthdays with how many times they occurred
    """
    dictionary = dict()
    count = 1
    for entry in bday_list:
        if entry in dictionary:
            dictionary[entry] += count
        else:
            dictionary[entry] = count

    return dictionary


def birthdays_at_least(dct, min_count):
    """
    takes the top count of birthdays and returns a list of the top birthdays
    :param dct:
    :param min_count:
    :return:
    """
    filtered = []
    for temp in dct:
        if dct[temp] >= min_count:
            filtered.append(temp)

    return filtered


def stringify(dictionary):
    """
    turns the filtered list of birthdays and turns them into strings.
    :param dictionary:
    :return: a list of numerical birthdays
    """
    final_list = []
    for full_bday in dictionary:
        month = str(full_bday.month)
        day = str(full_bday.day)
        year = str(full_bday.year)

        if month == 'JAN':
            month = '01'
        elif month == 'FEB':
            month = '02'
        elif month == 'MAR':
            month = '03'
        elif month == 'APR':
            month = '04'
        elif month == 'MAY':
            month = '05'
        elif month == 'JUN':
            month = '06'
        elif month == 'JUL':
            month = '07'
        elif month == 'AUG':
            month = '08'
        elif month == 'SEP':
            month = '09'
        elif month == 'OCT':
            month = '10'
        elif month == 'NOV':
            month = '11'
        else:
            month = '12'

        final_list.append(month + '/' + day + '/' + year)
    print(final_list)


def main():
    file_name = str(input('What is the file name?\n'))
    min_count = int(input('Enter a minimum count.\n'))

    print("Birthdays occurred at least " + str(min_count) + " times:")
    dictionary = build_dictionary(read_file(file_name))
    filtered_dictionary = birthdays_at_least(dictionary, min_count)

    print(filtered_dictionary)
    stringify(filtered_dictionary)


if __name__ == "__main__":
    main()
