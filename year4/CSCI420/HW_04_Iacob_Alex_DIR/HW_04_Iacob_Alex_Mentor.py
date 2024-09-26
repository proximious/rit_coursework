"""
@author: Alex Iacob ai9388
Prof. Kinsman
October 17, 2022

Homework 4: One dimensional classifier
"""

# getting appropriate imports
import math


# storing the main data path with the given CSV
DATA_PATH = 'Data_01.csv'


# file names to write to
CLASSIFIER_FILE = 'NW_04_Iacob_Alex_Classifier.py'
RESULTS_FILE = 'NW_04_Iacob_Alex_RESULTS.csv'


def quantize_data(path):
    """
    Separating all of the data for use later
    """

    # preparing all of the attributes to return
    all_attributes = {
        'Brightness': [],
        'Weight': [],
        'Precip': [],
        'LaneChanges': [],
        'Speed': []
    }

    normal_drivers = {}
    risky_drivers = {}

    with open(path) as file:
        next(file)
        for line in file:
            fields = line.split(',')

            # splitting apart each line in the file
            recID = fields[0]
            brightness = int(fields[1])
            weight = int(fields[2])
            precip = int(fields[3])
            lane_changes = int(fields[4])
            speed = int(float(fields[5]))
            pulled_over = fields[6].strip("\n")

            # separating the reckless drivers from the normal ones
            if pulled_over == 'y':
                risky_drivers[recID] = [brightness, weight, precip, lane_changes, speed]
            else:
                normal_drivers[recID] = [brightness, weight, precip, lane_changes, speed]            

            # adding in all of the attributes to the dictionary
            all_attributes['Brightness'].append(brightness)
            all_attributes['Weight'].append(weight)
            all_attributes['Precip'].append(precip)
            all_attributes['LaneChanges'].append(lane_changes)
            all_attributes['Speed'].append(speed)

    # return everything
    return all_attributes, risky_drivers, normal_drivers


def calculate_ideal_thresholds(all_attributes, risky_drivers, normal_drivers):
    """
    Calcultes the ideal attribute and ideal threshold for the given data set
    """
    # creating dictionaries to hold the lowest and highest values for each
    # individual attribute
    lower_bounds = {
        'Brightness': 0,
        'Weight': 0,
        'Precip': 0,
        'LaneChanges': 0,
        'Speed': 0
    }
    upper_bounds = {
        'Brightness': 0,
        'Weight': 0,
        'Precip': 0,
        'LaneChanges': 0,
        'Speed': 0
    }

    # saving each of the lowest and greatest value per attribute
    for key in all_attributes.keys():
        lower_bounds[key] = min(all_attributes[key])
        upper_bounds[key] = max(all_attributes[key])
    

    ideal_threshold = -math.inf
    ideal_threshold_mistakes = -math.inf
    

    return 0, 0



def write_to_classifier_file():
    pass


if __name__ == '__main__':
    all_attributes, risky_drivers, normal_drivers = quantize_data(DATA_PATH)

    ideal_attribute, ideal_threshold = calculate_ideal_thresholds(all_attributes, risky_drivers, normal_drivers)
