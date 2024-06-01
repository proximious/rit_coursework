"""
CSCI-141 Week 9: Dictionaries & Dataclasses
Lab: 07-BabyNames
Author: RIT CS

This is a test module that is given to the students.  It can help them figure out
if something is going wrong with their implementation without having to run the
separate main programs.

This is not a replacement for the main programs though, and students should still
make sure they get outputs that match the ones we have posted in the writeup.
"""

import names_util
import inspect
import os

# a lambda used to test two elements for equality
TEST_EQ = lambda got, wanted: got == wanted


def test(**kwargs):
    """
    The core test function that takes the function, its arguments and
    the expected output.
    :param kwargs: A dictionary where the key 'func' maps to the function,
    and the key 'args' matches to the function's arguments.  The key
    'expected' is used to compare the result of the running the function to
    the expected result
    """
    if 'args' in kwargs:
        # run the function with arguments
        ARGS = str(kwargs['args'])
        try:
            # unpack the args tuple when invoking the function
            RESULT = kwargs['func'](*kwargs['args'])
        except Exception as e:
            print(e)
            RESULT = '???'
    else:
        ARGS = ''
        try:
            RESULT = kwargs['func']()
        except Exception as e:
            print(e)
            RESULT = '???'

    EXPECTED = kwargs['expected']

    # display the results of the test
    print(kwargs['func'].__name__ + '(' + ARGS + ') =', RESULT,
          ', expected:', str(EXPECTED) + ', PASS:', (EXPECTED == RESULT))


def test_open_filename() -> None:
    """
    Test to make sure the file names can be opened.  It uses the supplied
    names_util.get_filename function to take a year and get the filename.
    """
    print(inspect.stack()[0][3] + '()...')
    test(func=os.path.exists, args=(names_util.get_filename(2018),), expected=True)
    test(func=os.path.exists, args=(names_util.get_filename(1990),), expected=True)
    test(func=os.path.exists, args=(names_util.get_filename(1880),), expected=True)
    print()


def test_tops_in_year():
    """
    Test some years for top num in a year.
    """
    print(inspect.stack()[0][3] + '()...')

    # test individual results for tops in year 2000 for num = 10
    print('names_util.get_tops_in_year(2000, 10)...')
    expected_names = 'Jacob', 'Michael', 'Matthew', 'Joshua', 'Emily', \
                     'Christopher', 'Nicholas', 'Andrew', 'Hannah', 'Joseph'
    expected_genders = 'M', 'M', 'M', 'M', 'F', 'M', 'M', 'M', 'F', 'M'
    expected_counts = 34477, 32037, 28573, 27537, 25956, 24932, 24652, \
                      23641, 23082, 22831
    for name, n, g, c in zip(names_util.get_tops_in_year(2000, 10),
                             expected_names,
                             expected_genders,
                             expected_counts):
        test(func=TEST_EQ, args=(name.name, n), expected=True)
        test(func=TEST_EQ, args=(name.gender, g), expected=True)
        test(func=TEST_EQ, args=(name.count, c), expected=True)
        print()

    # test individual results for tops in year 2018 for num = 5
    print('names_util.get_tops_in_year(2018, 5)...')
    expected_names = 'Liam', 'Emma', 'Noah', 'Olivia', 'Ava'
    expected_genders = 'M', 'F', 'M', 'F', 'F'
    expected_counts = 19837, 18688, 18267, 17921, 14924
    for name, n, g, c in zip(names_util.get_tops_in_year(2018, 5),
                             expected_names,
                             expected_genders,
                             expected_counts):
        test(func=TEST_EQ, args=(name.name, n), expected=True)
        test(func=TEST_EQ, args=(name.gender, g), expected=True)
        test(func=TEST_EQ, args=(name.count, c), expected=True)
        print()

    # test individual results for tops in year 1979 for num = 3
    print('names_util.get_tops_in_year(1979, 3)...')
    expected_names = 'Michael', 'Jennifer', 'Christopher'
    expected_genders = 'M', 'F', 'M'
    expected_counts = 67739, 56717, 50677
    for name, n, g, c in zip(names_util.get_tops_in_year(1979, 3),
                             expected_names,
                             expected_genders,
                             expected_counts):
        test(func=TEST_EQ, args=(name.name, n), expected=True)
        test(func=TEST_EQ, args=(name.gender, g), expected=True)
        test(func=TEST_EQ, args=(name.count, c), expected=True)
        print()


def test_top_name_year():
    """
    Test some top name in year for several different years.
    """
    print(inspect.stack()[0][3] + '()...')

    # test top in year for 2005
    print('names_util.get_top_name_year(2005)...')
    name = names_util.get_top_name_year(2005)
    test(func=TEST_EQ, args=(name.name, 'Jacob'), expected=True)
    test(func=TEST_EQ, args=(name.count, 25873), expected=True)
    test(func=TEST_EQ, args=(name.percentage, 0.6733294348774393), expected=True)
    print()

    # test top in year for 1880
    print('names_util.get_top_name_year(1880)...')
    name = names_util.get_top_name_year(1880)
    test(func=TEST_EQ, args=(name.name, 'John'), expected=True)
    test(func=TEST_EQ, args=(name.count, 9701), expected=True)
    test(func=TEST_EQ, args=(name.percentage, 4.814774374143853), expected=True)
    print()

    # test top in year for 1950
    print('names_util.get_top_name_year(1950)...')
    name = names_util.get_top_name_year(1950)
    test(func=TEST_EQ, args=(name.name, 'James'), expected=True)
    test(func=TEST_EQ, args=(name.count, 86476), expected=True)
    test(func=TEST_EQ, args=(name.percentage, 2.4682731735275163), expected=True)
    print()


def test_top_10_years():
    """
    Test some 10 top names over a range of different years
    """
    print(inspect.stack()[0][3] + '()...')

    # test individual results for top 10 in years between 1990 and 1999
    print('names_util.get_top_years(1990, 1999)...')
    expected_females = 'Jessica', 'Ashley', 'Emily', 'Sarah', 'Samantha', \
                       'Amanda', 'Brittany', 'Elizabeth', 'Taylor', 'Megan'
    expected_males = 'Michael', 'Christopher', 'Matthew', 'Joshua', 'Jacob', \
                     'Nicholas', 'Andrew', 'Daniel', 'Tyler', 'Joseph'
    names = names_util.get_top_years(1990, 1999)
    for f_name, m_name, female, male, in zip(names.females, names.males, expected_females, expected_males):
        test(func=TEST_EQ, args=(f_name, female), expected=True)
        test(func=TEST_EQ, args=(m_name, male), expected=True)
        print()

    # test individual results for top 10 in years for just 2001
    print('names_util.get_top_years(2001, 2001)...')
    expected_females = 'Emily', 'Madison', 'Hannah', 'Ashley', 'Alexis', 'Sarah', \
                       'Samantha', 'Abigail', 'Elizabeth', 'Olivia'
    expected_males = 'Jacob', 'Michael', 'Matthew', 'Joshua', 'Christopher', \
                     'Nicholas', 'Andrew', 'Joseph', 'Daniel', 'William'
    names = names_util.get_top_years(2001, 2001)
    for f_name, m_name, female, male, in zip(names.females, names.males, expected_females, expected_males):
        test(func=TEST_EQ, args=(f_name, female), expected=True)
        test(func=TEST_EQ, args=(m_name, male), expected=True)
        print()

    # test individual results for top 10 in years for 1880 - 2018
    print('names_util.get_top_years(1880, 2018)...')
    expected_females = 'Mary', 'Elizabeth', 'Patricia', 'Jennifer', 'Linda', \
                       'Barbara', 'Margaret', 'Susan', 'Dorothy', 'Sarah'
    expected_males = 'James', 'John', 'Robert', 'Michael', 'William', \
                     'David', 'Joseph', 'Richard', 'Charles', 'Thomas'
    names = names_util.get_top_years(1880, 2018)
    for f_name, m_name, female, male, in zip(names.females, names.males, expected_females, expected_males):
        test(func=TEST_EQ, args=(f_name, female), expected=True)
        test(func=TEST_EQ, args=(m_name, male), expected=True)
        print()


def run_tests():
    """
    Run all the tests.
    """
    # test_open_filename()
    # test_tops_in_year()
    # test_top_name_year()
    test_top_10_years()


if __name__ == '__main__':
    run_tests()
