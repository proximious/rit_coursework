"""
Alex Iacob
ai9388@rit.edu

fileModding.py
"""

if __name__ == '__main__':
    text = open("info.txt", "r")
    x = text.readlines()
    for i in x:
        sep = i.strip()
        print(sep[1], end="")

