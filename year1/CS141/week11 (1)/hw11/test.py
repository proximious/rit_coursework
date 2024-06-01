"""
tests
"""
import droid_factory


def tests():
    # task 2
    file_name = 'droid_parts_test.txt'
    print('task #2')
    print('\t', droid_factory.conveyor(file_name))
    print('\t Function should return arms, arms, legs, body, head, head.\n')

    # task 3
    print('task #3')
    print('\t Starting new droid')
    print('\t', droid_factory.build_droid(10000, droid_factory.conveyor(file_name)))
    print('Function should return all true with serial number 10000.\n')

    # task 4
    print('task #4')
    print(droid_factory.build_droids(droid_factory.conveyor(file_name)))
    print('Droid 10000 will be built, but Droid 10001 will not be due to insufficient parts.')


if __name__ == '__main__':
    tests()
