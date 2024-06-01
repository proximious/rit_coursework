"""
file: demo_file.py
author: bksteele
description: demonstrate reading and (append) writing a file
"""

def write_file(text_file):
    """
    text files demo
    """
    fd = open(text_file, 'a')
    fd.write('First again')
    fd.write('\n')
    fd.write('Third\n')
    fd.write('What?\n')
    fd.close()

def read_file( name):
    with open( name) as fd:
        for line in fd:
            print( line.strip())

def main():
    write_file('words1.txt')
    read_file('words1.txt')

if __name__ == "__main__":
    main()

