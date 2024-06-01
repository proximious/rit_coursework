"""
iota.py
author: bksteele
tail recursion exercise. What is wrong?
"""

def iota_1( N, current=1):
    """
    iota N produced the output: 1 2 3 ... N
    pre-conditions: N > 0
    """
    if current == N:
        print()
        return
    else:
        print( current, end=" ")
        iota_1( N - 1, current + 1)
        return


def test_iota( func):
    """
    iota( 4, 1) = iota( 3, 2) = iota( 2, 3)
    prints: 1 2 3
    test the iota N function, func, passed as an argument
    """
    print( func)
    print( "1: ", end=''); func( 1)
    print( "2: ", end=''); func( 2)
    print( "3: ", end=''); func( 3)
    print( "4: ", end=''); func( 4)
    print( "24: ", end=''); func( 24)

if __name__ == "__main__":
    test_iota( iota_1)

