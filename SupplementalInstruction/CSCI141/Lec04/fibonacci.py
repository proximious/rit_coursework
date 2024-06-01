"""
factorial.py
James Heliotis
August 2017

Demonstrate tail-recursive and iterative solutions to the Fibonacci function.
"""

def fibo(n):
    """ Naive implementation of the Fibonacci sequence. Returns the nth number
        in the sequence, e.g. 0, 1, 1, 2, 3, 5, 8, 13, etc.
        :param n: The index of the desired number in the sequence.
        :preconditions: n is non-negative.
    """
    if n == 0:
        return 0
    elif n == 1:
        return 1
    else:
        return fibo(n-1) + fibo(n-2)

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

def fibo_acc( N):
    """
    another accumulative version.
    """
    f_n_minus_2 = 0
    f_n_minus_1 = 1
    return fibo_help( N, f_n_minus_1, f_n_minus_2)

def fibo_help( N, f_n_minus_1, f_n_minus_2):
    if N == 0: return f_n_minus_2
    if N == 1: return f_n_minus_1
    return fibo_help( N - 1, f_n_minus_1 + f_n_minus_2, f_n_minus_1)

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
        

def fibo_simple(n):
    """ Calculates the nth number in the Fibonaccisequence using iteration.
        :param n: The index of the desired number in the sequence
        :preconditions: n is greater than or equal to 0.
    """
    # set up fn_1 and fn_2 to be the first two values in the sequence
    fn_1 = 1
    fn_2 = 0
    while n > 1:
        n = n - 1
        temp = fn_1
        fn_1 = fn_1 + fn_2
        fn_2 = temp
    if n == 0:
        return fn_2
    elif n == 1:
        return fn_1

def stress_test(fib):
    n = 0
    while n <= 100:
        print(n, ":", fib(n))
        n = n + 1
        
def test_fibonaccis():
    """ Test the various implementations side-by-side."""
    n = 0
    while n <= 20:
        print(n, " : ",
              fibo(n),
              fibo_accum(n),
              fibo_loop(n),
              fibo_simple(n),
              fibo_acc(n))
        n = n + 1

    # run this program simultaneously and choose different
    # options to "race" the implementations against each other
    print("1 - naive, 2 - accum, 3 - loop, 4 - simple")
    choice = int(input("Choose which algorithm to stress test: "))
    
    if choice == 1:
        stress_test(fibo)
    elif choice == 2:
        stress_test(fibo_accum)
    elif choice == 3:
        stress_test(fibo_loop)
    elif choice == 4:
        stress_test(fibo_simple)

    
if __name__ == "__main__":
    test_fibonaccis()
