"""
iota2.py
author: bksteele
tail recursion exercise fixed iota_1
"""

def iota_1fixed( N, current=1):
    """
    iota N produced the output: 1 2 3 ... N
    pre-conditions: N > 0
    """
    if N == 0:
        print()
    else:
        print( current, end=" ")
        iota_1fixed( N - 1, current + 1)


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
    print( "42: ", end=''); func( 42)


if __name__ == "__main__":
    test_iota( iota_1fixed)

