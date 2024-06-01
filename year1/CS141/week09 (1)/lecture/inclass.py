"""
10/21/19

"""
from typing import Dict


def file_demo(file_name) -> Dict[str, int]:
    cookie_result = dict()
    with open(file_name, "r")as file:
        for line in file:
            parts = line.split(",")
            count = int(parts[1])
            if parts[0] in cookie_result:
                cookie_result[parts[1]] += count
            else:
                cookie_result[parts[0]] = count

    return cookie_result


def main():
    file_name = 'cookies.txt'
    file_demo(file_name)