"""
class_fact.py a factorial function
"""

def fact_rec( N ):
    """
    factorial(N) = 1 if N is 0. otherwise factorial(N) = N * factorial(N - 1)
    """
    if N == 1 or N == 0:
        return 1
    else:
        return N * fact_rec( N - 1)

def fact_tail( N):
    """
    factorial(N) = 1 if N is 0. otherwise factorial(N) = N * factorial(N - 1)
    """
    return fact_aux( N, 1)

def fact_aux( N, acc):
    if N == 0:
        return acc
    else:
        return fact_aux( N - 1, acc * N )
        # fact_tail(5) = fact_aux( 5, 1) = fact_aux( 4, 1 * 5)

def fact_iter( N):
    """
    factorial(N) = 1 if N is 0. otherwise factorial(N) = N * factorial(N - 1)
    """
    result = 1
    while N > 0:
        # compute partial result
        result = result * N
        # count down N
        N -= 1     # same as N = N - 1
    return result


def fact_iter2( N):
    result = 1
    while True:
        if N == 0:
            break   # ends the while loop
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
    num = int( input( "Enter N for N! " ))
    print( fact_rec( num))
    print( fact_tail( num))
    print( fact_iter( num))
    print( fact_iter2( num))
    print( fact_iter3( num))

if __name__ == "__main__":
    main()
