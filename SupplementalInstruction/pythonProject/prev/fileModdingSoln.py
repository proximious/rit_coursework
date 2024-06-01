"""
Alex Iacob
ai9388@rit.edu

fileModdingSoln.py
"""


def verify_extension(name):
    if name[-4:] == ".txt":
        return True
    else:
        return False


if __name__ == '__main__':
    filename = input("Enter a file name > ")
    if not verify_extension(filename):
        print("invalid extension")
        pass

    with open(filename, "r") as f:
        final_result = ""
        for line in f:
            print("Original line: ", line)
            print("Stripped line: ", line.strip())
            print("----------")

        input("Continue with enter >")

    with open(filename, "r") as f:
        print("Getting second char of every word")
        for line in f:
            print("Original line: ", line.strip())
            print("Second char: ", line.strip()[1])
            final_result += line.strip()[1]
            print("----------")

    #print(final_result)
