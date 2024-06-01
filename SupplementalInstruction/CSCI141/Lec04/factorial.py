"""
file: factorial.py
author: bksteele and cs141 class
description: factorial function written several ways
"""

def fact_rec( N ):
    """
    fact_rec(N) math: N! is 0 if N is 0; otherwise N! = N * (N - 1)!
    pre-condition: integer N >= 0
    """
    if N == 0:
        return 1
    else:
        return N * fact_rec( N - 1 )

def fact_tail( N ):
    """
    fact_tail(N) math: N! is 0 if N is 0; otherwise N! = N * (N - 1)!
    pre-condition: integer N >= 0
    """
    return fact_aux( N, 1 )

def fact_aux( N, acc ):
    """
    fact_aux(N) = 1 if N is 0. otherwise factorial(N) = N * factorial(N - 1)
    param: N integer
    param: acc integer accumulated, partial computation result
    pre-condition: integer N >= 0
    """
    if N == 0:
        return acc
    else:
        return fact_aux( N - 1, N * acc)


def fact_iter( N):
    result = 1
    while True:
        if N == 0:
            return result
        else:
            result =  N  * result
            N = N - 1

def fact_iter2( N):
    result = 1
    while True:
        if N == 0:
            break
        else:
            result =  N  * result
            N = N - 1
    return result

def fact_iter3( N):
    result = 1
    while not N == 0:
        result =  N  * result
        N = N - 1
    return result

def main():
    num = int( input( "What is N? " ))
    print( "fact_rec", fact_rec( num))
    print( "fact_tail", fact_tail( num))
    print( "fact_iter", fact_iter( num))
    print( "fact_iter2", fact_iter2( num))
    print( "fact_iter3", fact_iter3( num))

if __name__ == "__main__":
    main()
