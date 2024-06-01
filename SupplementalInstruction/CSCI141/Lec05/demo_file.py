"""
file: demo_file.py
author: bksteele
description: demonstrate reading and (append) writing a file
"""

def write_file(text_file):
    """
    text files demo
    """
    fd = open(text_file, 'a') # 'a' for append. 'w' for overwrite content
    fd.write('First again')
    fd.write('\n')
    fd.write('Third\n')
    fd.write('What the ?\n')
    fd.close()

def read_file( name):
    with open( name) as fd:
        for line in fd:
            print( line.strip())
    # fd.close() done by the with construct

def main():
    write_file('words1.txt')
    read_file('words1.txt')

if __name__ == "__main__":
    main()

