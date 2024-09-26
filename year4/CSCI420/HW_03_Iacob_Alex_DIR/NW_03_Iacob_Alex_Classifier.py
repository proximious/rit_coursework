"""
@author: Alex Iacob ai9388@rit.edu
Prof. Kinsman
October 2, 2022

Homework 3: Mentor Program
"""

import pandas as pd
import csv

# making the results file name
RESULTS_FILE = 'NW_03_Iacob_Alex_RESULTS.csv'
# the data path to read from
DATA_PATH = 'TrafficStation_01\Data_01.csv'
# importing the ideal threshold from mentor file
IDEAL_THRESHOLD = 64


def classifier(speed):
    """
    Classifies the given speed
    """
    if speed <= IDEAL_THRESHOLD:
        intent = 1
    else:
        intent = 2
    return intent


def read_csv(path):
    """
    Reads in all of the speeds and returns all of them as array
    """
    data = pd.read_csv(path)
    speeds = data['SPEED'].tolist()

    return speeds


def classify_all_speeds(speeds):
    """
    Iterates over all of the speeds and determines if the driver is speeding
    """
    classified_intentions = []

    for speed in speeds:
        intention = classifier(speed)
        classified_intentions.append(str(intention))

    return classified_intentions


def write_intentions_to_file(filename, intentions):
    """
    Writes all of the intentions to one column in a file
    """
    with open(filename, 'w', newline='') as file:
        writer = csv.writer(file)
        header = ['INTENT']
        writer.writerow(header)
        for intention in intentions:
            writer.writerow(intention)


if __name__ == '__main__':
    all_speeds = read_csv(DATA_PATH)

    intentions = classify_all_speeds(all_speeds)

    write_intentions_to_file(RESULTS_FILE, intentions)
