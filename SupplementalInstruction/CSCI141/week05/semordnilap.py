"""
file: semordnilap.py
language: python3
author: ben k steele, bks@cs.rit.edu
description: print the reverse of a word to see if it is another valid word.
"""

def reverse_r( st):
    """
    reverse: String -> String
    reverse returns the reverse of the string st.
    """
    return 'not implemented'

def reverse_i( st):
    """
    reverse: String -> String
    reverse returns the reverse of the string st.
    """
    return 'not implemented'

def reverse_by_index( st):
    """
    reverse_by_index: String -> String
    reverse_by_index returns the reverse of the string st using range().
    """
    return  'not implemented'

def test_reverse( func):
    """
    test_reverse : (Function : String -> String) -> Void, IO
    test_reverse tests the passed-in function
    """
    # demonstrates the ability to pass a function in Python
    print( 'Begin testing', str( func))
    print( func( '') == "", end='\t')
    print( func( 'a') == "a", end='\t')
    print( func( 'ab') == "ba", end='\t')
    print( func( 'abc') == "cba", end='\t')
    print( func( 'ab d') == "d ba", end='\t')
    print( '\nEnd testing')

def main():
    """
    main prompts for a word and prints the reverse of the sequence.
    """
    word = input( "Enter a word to reverse: ")
    print( "word:", word)
    print( "reverse:", reverse( word))


if __name__ == "__main__":
    test_reverse( reverse_r)
    test_reverse( reverse_i)
    test_reverse( reverse_by_index)
    main()

