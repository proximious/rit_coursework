"""
CSCI-141 Computer Science 1 Recitation Exercise
05-Strings and Files
Reversing Encryption

Here is a skeleton for code that can decrypt the file
your friend sent you. You have to write code inside the
function bodies to solve the problem.
Do NOT add functions or modify the function definitions!
Start with how you would decrypt a single letter. Then,
implement the logic for reading the file and decrypting
the contents
"""


def decrypt_letter(letter, offset):
    """
    Decrypt an individual letter and return it
    :param: the letter to be rotated forward
    """
    return chr(((ord(letter) + offset - ord('A')) % 26) + ord('A'))


def read_file(file_path, offset):
    """
    Read the file and decrypt every word,
    printing each word as it is decrypted
    :param: the path to the file containing encrypted message
    """
    with open(file_path, "r") as file:
        for line in file:
            result = ""
            for c in line.strip():
                result += decrypt_letter(c, offset)
            print(result)


def test_decryption(offset):
    """
    Use this function to check your implementation
    of decrypt_letter()
    :return: True if the tests pass, False otherwise
    """
    if decrypt_letter('B', offset) != 'E':
        print("Your result for decrypting B was ", decrypt_letter('B', offset))
        return False
    if decrypt_letter('Z', offset) != 'C':
        print("Your result for decrypting Z was ", decrypt_letter('Z', offset))
        return False
    print('All tests passed!')
    return True


def main():
    test_decryption(-5)
    read_file("encrypted.txt", -5)


if __name__ == '__main__':
    main()
