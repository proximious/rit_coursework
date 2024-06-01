"""
file: test_lengths.py
language: python3
author: ben k steele, bks@cs.rit.edu
description: test the lengths.py functions module
date: 07/2017
purpose: introduction to strings
"""

import lengths as subject

def test_length():
    """
    test_length : Void -> Void, IO
    test_length tests the longest function
    """
    print( 'Begin testing length')
    print( subject.length( '') == 0, end='\t')
    print( subject.length( 'a') == 1, end='\t')
    print( subject.length( 'ab') == 2, end='\t')
    print( subject.length( 'abc') == 3, end='\t')
    print( '\nEnd testing length')

def test_longest():
    """
    test_longest : Void -> Void, IO
    test_longest tests the longest function
    """
    print( 'Begin testing longest')
    print( subject.longest( '') == 0, end='\t')
    print( subject.longest( 'a') == 1, end='\t')
    print( subject.longest( 'ab c') == 2, end='\t')
    print( subject.longest( 'a bc') == 2, end='\t')
    print( subject.longest( 'a bc d') == 2, end='\t')
    print( subject.longest( 'a bcde fg') == 4, end='\t')
    print( subject.longest( 'a bc defg') == 4, end='\t')
    print( subject.longest( 'a bc defg\nfifth') == 5, end='\t')
    print( subject.longest( \
        'Recursion is another approach to string processing') == 10, end='\t')
    print( '\nEnd testing longest')


if __name__ == "__main__":
    test_length()
    test_longest()
    string = input( "Enter a string to test ")
    print( "length:", subject.length(string))
    print( "longest:", subject.longest(string))

