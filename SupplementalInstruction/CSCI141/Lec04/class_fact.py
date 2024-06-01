"""
class_fact.py a factorial function
"""

def fact_rec( N ):
    """
    factorial(N) = 1 if N is 0. otherwise factorial(N) = N * factorial(N - 1)
    """
    if N == 0 or N == 1:
        return 1
    else:
        return fact_rec( N - 1) * N


def fact_tail( N, acc=1 ):
    """
    factorial(N) = 1 if N is 0. otherwise factorial(N) = N * factorial(N - 1)
    """
    if N == 0:
        return acc
    else:
        return fact_tail( N - 1, N * acc )


def fact_iter( N):
    """
    factorial(N) = 1 if N is 0. otherwise factorial(N) = N * factorial(N - 1)
    """
    result  = 1
    while N > 0:
        result = result * N
        N = N - 1
    return result


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
    num = int( input( "Enter N for N! " ))
    print( fact_rec( num))
    print( fact_tail( num))
    #print( fact_iter( num))
    #print( fact_iter2( num))
    #print( fact_iter3( num))

if __name__ == "__main__":
    main()
