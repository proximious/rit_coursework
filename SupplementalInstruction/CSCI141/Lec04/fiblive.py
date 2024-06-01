"""
fib.py

live code the iterative solution to the Fibonacci function.
"""

def fibo(n):
    """ Naive implementation of Fibonacci sequence.
    Returns the nth number
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


def fibo_loop(n):
    """ Calculates the nth number in the Fibonacci sequence using iteration.
        :param n: The index of the desired number in the sequence
        :preconditions: n is greater than or equal to 0.
    """
    # set up fn_1 and fn_2 to be first two values in the sequence
    # then count down until n < 0 calculating next 2 values in sequence

    pass # stub

def stress_test(fib):
    """ stress test the implementation function fib. """
    n = 0
    while n <= 100:
        print(n, ":", fib(n))
        n = n + 1
    
if __name__ == "__main__":
    stress_test(fibo_loop)
