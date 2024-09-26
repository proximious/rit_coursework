"""
@author: Alex Iacob ai9388
Prof. Kinsman
October 2, 2022

Homework 3: Mentor Program
"""

# getting appropriate imports
import textwrap
import pandas as pd
import math
import warnings

# storing the main data path with the given CSV
DATA_PATH = 'TrafficStation_01\Data_01.csv'


# file names to write to
CLASSIFIER_FILE = 'NW_03_Iacob_Alex_Classifier.py'
RESULTS_FILE = 'NW_03_Iacob_Alex_RESULTS.csv'


# vehicle speed increments allowed
BIN_SIZE = 1

def quantize_data(path):
    """
    Helper method to the speed classification
    """
    quantized_data = {}
    speeds_to_intent = {}

    # reading the CSV and separating the valuesv
    data = pd.read_csv(path)
    speeds = data['SPEED'].tolist()
    intents = data['INTENT'].tolist() 

    for i in range(len(speeds)):
        # flooring to make the numbers easier to read
        floored = math.floor(speeds[i] / BIN_SIZE) * BIN_SIZE

        # if the speed is not in the dictionary we make a new key
        if str(floored) not in quantized_data:
            quantized_data[str(floored)] = []        

        # appending the new speed into the dictionary
        quantized_data[str(floored)].append(floored)

        speeds_to_intent[str(floored)] = intents[i]

    # making new dictionary to hold the sorted version
    sorted_data = {}
    sorted_keys = sorted(quantized_data.keys())

    # filling in the new dictionary
    for key in sorted_keys:
        sorted_data[key] = quantized_data[key]

    # returning just the values since they are the speed
    return list(sorted_data.keys()), list(sorted_data.values()), speeds_to_intent


def split_array(quantized_data, cutoff):
    """
    Helper method for Otsu's method

    Separates the quantized data utiliziing the cutoff value such that
    the left array is less than or equal to the cutoff and the right array
    is greater than the cutoff
    """
    # starting index
    cutoff_index = 0

    # looping through every index in quantized data
    for count in range(len(quantized_data)):
        # covering for when the cutoff isnt in the quantized data
        if quantized_data[count][0] <= cutoff:
            cutoff_index = count

    # using the cutoff to split the array
    left = quantized_data[: cutoff_index + 1]
    right = quantized_data[ cutoff_index + 1 :]

    # flattening the arrays for easier use later
    left_flattened = [value for sub_list in left for value in sub_list]
    right_flattened = [value for sub_list in right for value in sub_list]

    # returning two values together
    return left_flattened, right_flattened


def find_people_who_intend_to_speed(path):
    """
    Determining who intends to speed or not based on the their intention
    Anyone who has a 0 or 1 intention is normal drivers
    Anyone who has a 2 intention is intentionally speeding
    """
    # reading in the info from the data file
    data = pd.read_csv(path)
    speeds = data['SPEED'].tolist()
    intents = data['INTENT'].tolist() 

    # making the empty arrays for the different groups of people
    speeding_people = []
    not_speeding_people = []

    # looping through the given indices
    for i in range(len(speeds)):
        # if the current intention is either 0 or 1, the person is not speeding
        if intents[i] == 0 or intents[i] == 1:
            not_speeding_people.append(speeds[i])
        # if the current intention is 2, the person is speeding    
        elif intents[i] == 2:
            speeding_people.append(speeds[i])

    return not_speeding_people, speeding_people
    

def calculate_ideal_threshold(unique_speeds, no_intention, has_intention):
    """
    Calculates the best threshold for the given dual arrays
    This mainly works by going over every possible threshold and calculating the 
    true/false positive and false negative values then storing them
    The minimum value is then recorded for future use
    """
    # making the ideal values
    ideal_threshold = math.inf
    ideal_mixed = math.inf

    # keeping track of the rates for future roots
    false_positive_rates = {}
    true_positive_rates = {}

    # iterating over every possible speed to try to find the best threshold
    for threshold in unique_speeds:
        # making all of the possible values
        # we don't care about true negatives because people who are not speeding
        # regardless of their intention should not be fined
        true_positive = 0
        false_positive = 0
        false_negative = 0

        # if the driver with no intention's speed is greater than the threshold
        # that means that we have a false alarm
        for speed in no_intention:
            if speed > threshold:
                false_positive += 1  

        # if the driver with an intention's speed is less than or equal to the threshold
        # that means we have a miss, otherwise it is good
        for speed in has_intention:
            if speed <= threshold:
                false_negative += 1
            else:
                true_positive += 1

        false_positive_rates[threshold] = false_positive / len(no_intention)
        true_positive_rates[threshold] = true_positive / len(has_intention)

        # making the mixed value
        mixed = false_positive + false_negative

        if mixed < ideal_mixed:
            ideal_mixed = mixed
            ideal_threshold = threshold

    return ideal_threshold, false_positive_rates, true_positive_rates    


def write_to_classifier_file(classifier_path, ideal_threshold):
    with open(classifier_path, 'w') as file:
        # writing the heading to the file
        file.write(textwrap.dedent('''\
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
        '''))

        s = 'IDEAL_THRESHOLD = ' + str(ideal_threshold) + '\n\n'
        file.write(s)
        
        file.write(textwrap.dedent('''\
            
            def classifier(speed):
                """
                Classifies the given speed
                """
                if speed <= IDEAL_THRESHOLD:
                    intent = 1
                else:
                    intent = 2
                return intent

        '''))

        file.write(textwrap.dedent('''\

            def read_csv(path):
                """
                Reads in all of the speeds and returns all of them as array
                """
                data = pd.read_csv(path)
                speeds = data['SPEED'].tolist()

                return speeds

        '''))

        file.write(textwrap.dedent('''\
            
            def classify_all_speeds(speeds):
                """
                Iterates over all of the speeds and determines if the driver is speeding
                """
                classified_intentions = []

                for speed in speeds:
                    intention = classifier(speed)
                    classified_intentions.append(str(intention))

                return classified_intentions

        '''))

        file.write(textwrap.dedent('''\

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

        '''))

        file.write(textwrap.dedent('''\

            if __name__ == '__main__':
                all_speeds = read_csv(DATA_PATH)

                intentions = classify_all_speeds(all_speeds)

                write_intentions_to_file(RESULTS_FILE, intentions)
        '''))


if __name__ == '__main__':
    warnings.filterwarnings('ignore')

    # getting the variance and threshold of the given data path
    unique_speeds, all_the_speeds, speeds_to_intents = quantize_data(DATA_PATH)

    unique_speeds = [int(st) for st in unique_speeds]

    # making the histograms for the people who want to speed and dont
    no_intention, has_intention = find_people_who_intend_to_speed(DATA_PATH)

    # calculate the ideal threshold
    ideal_threshold, false_positive_rates, true_positive_rates = \
    calculate_ideal_threshold(unique_speeds, no_intention, has_intention)
    print('the ideal threshold: ', ideal_threshold)
    print('the best false positive rate: ', false_positive_rates[ideal_threshold])
    print('the best true positive rate: ', true_positive_rates[ideal_threshold])

    # writing to classifier file
    write_to_classifier_file(CLASSIFIER_FILE, ideal_threshold)