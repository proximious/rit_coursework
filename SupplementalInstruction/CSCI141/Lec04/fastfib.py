"""
fastfibo.py
"""

def fibo_accum(n, fn_1=1, fn_2=0):
    """ Calculates the nth number in the Fibonacci sequence using tail
        recursion.
        :param n: The index of the desired number in the sequence
        :param fn_1: The calculated value of F(n-1)
        :param fn_2: The calculated value of F(n-2)
        :preconditions: n is greater than or equal to 0.
    """
    if n == 0:
        return fn_2
    elif n == 1:
        return fn_1
    else:
        temp = fn_1
        fn_1 = fn_1 + fn_2 # new F(n-1) = old F(n-1) + old F(n-2)
        fn_2 = temp        # new F(n-2) = old F(n-1)
        return fibo_accum(n - 1, fn_1, fn_2)

def fibo_loop(n):
    """ Calculates the nth number in the Fibonacci sequence using iteration.
        :param n: The index of the desired number in the sequence
        :preconditions: n is greater than or equal to 0.
    """
    # set up fn_1 and fn_2 to be the first two values in the sequence
    fn_1 = 1
    fn_2 = 0
    while True:
        if n == 0:
            return fn_2
        elif n == 1:
            return fn_1
        else:
            n = n - 1
            temp = fn_1
            fn_1 = fn_1 + fn_2
            fn_2 = temp
        

if __name__ == "__main__":
    N = 35
    print( "fibo_accum(", N, ")", fibo_accum( N))
    print( "fibo_loop(", N, ")", fibo_loop( N))

