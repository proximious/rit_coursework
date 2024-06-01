"""
iota_sol.py
author: bksteele
tail recursion solution variations
"""

def iota_1( N, current=1):
    """
    iota_1 N produces output: 1 2 3 ... N by accumulative tail recursion.
    pre-conditions: N > 0
    """
    if N == 0:
        print()
    else:
        print( current, end=" ")
        iota_1( N - 1, current + 1)


def iota_2( N, current=1):
    """
    iota_2 N produces output: 1 2 3 ... N by nested auxiliary tail recursion.
    pre-conditions: N > 0
    """
    def iota( N, current=1, cycle=0):     # what the?? a nested definition
        if cycle >= N:
            print()
        else:
            print( current, end=" ")
            iota( N, current + 1, cycle + 1)
    iota( N, current, 0)


def iota_3( N, current=1, cycle=0):
    """
    iota_3 N produces output: 1 2 3 ... N by accumulative tail recursion.
    pre-conditions: N > 0
    """
    if cycle >= N:
        print()
    else:
        print( current, end=" ")
        iota_3( N, current + 1, cycle + 1)

def iota_4( N):
    """
    iota_4 N produces output: 1 2 3 ... N by while ... mutating assignment.
    pre-conditions: N > 0
    """
    num = 1
    while num <= N:
        print( num, end=" ")
        num += 1
    print()


def test_iota( func):
    """
    test the iota N function, func, passed as an argument
    """
    print( func)
    print( "1: ", end=''); func( 1)
    print( "2: ", end=''); func( 2)
    print( "3: ", end=''); func( 3)
    print( "4: ", end=''); func( 4)
    print( "24: ", end=''); func( 24)
    print( "36: ", end=''); func( 36)

if __name__ == "__main__":
    test_iota( iota_1)
    test_iota( iota_2)
    test_iota( iota_3)
    test_iota( iota_4)

