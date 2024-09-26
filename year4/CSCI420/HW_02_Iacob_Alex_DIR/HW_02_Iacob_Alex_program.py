"""
@author: Alex Iacob ai9388
Prof. Kinsman
September 25, 2022

Homework 2: Speed Classification
"""

# getting appropriate imports
import pandas as pd
import numpy as np
import math
import matplotlib.pyplot as plt
import warnings

# storing the main data path with the given CSV
DATA_PATH = 'Data_01.csv'

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
    

def plot_speed_histogram(no_intention, has_intention):
    """
    Plotting everyone who is has the intent to speed and lack of intent to speed
    """
    # plotting the two parts of the histogram, alpha = 0.5 allows them to be transparent
    plt.hist(no_intention, alpha = 0.5, label = 'no intention')
    plt.hist(has_intention, alpha = 0.5, label = 'has intention')
    # adding the legend and the axis labels
    plt.legend(loc = 'upper right')
    plt.xlabel('Vehicle speeds (mp/h) ')
    plt.ylabel('Number of occurances')
    plt.title('Histogram of all drivers\' speeds and their intentions')

    # showing the plot
    plt.show()


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


def create_ROC_curve(false_positive_rates, true_positive_rates, ideal_threshold):
    """
    Creating the ROC plot
    This takes the false positive and true positive rates and plots them. With the given
    ideal threshold, we can highlight it to display it as the ideal. 
    """
    # using scatter plot to plot the individual points
    plt.scatter(false_positive_rates.values(), true_positive_rates.values())
    # plotting again in order to connect the points of the scatterplot
    plt.plot(false_positive_rates.values(), true_positive_rates.values())
    # plotting the ideal point with some niceities to make it look pretty
    plt.plot(
        false_positive_rates[ideal_threshold], true_positive_rates[ideal_threshold], 
        marker = 'o', markeredgecolor = [ 0, 0.7, 0 ], markerfacecolor = 'none', 
        markeredgewidth = 5, markersize = 12)
    # editing the plot to add the proper labels
    plt.title('ROC Curve using false and true positive rates')
    plt.xlabel('False Positive rates')
    plt.ylabel('True Positive rates')
    plt.show()


if __name__ == '__main__':
    warnings.filterwarnings('ignore')

    # getting the variance and threshold of the given data path
    unique_speeds, all_the_speeds, speeds_to_intents = quantize_data(DATA_PATH)

    unique_speeds = [int(st) for st in unique_speeds]

    # making the histograms for the people who want to speed and dont
    no_intention, has_intention = find_people_who_intend_to_speed(DATA_PATH)

    # plotting the all of the speeds in one histogram
    plot_speed_histogram(no_intention, has_intention)

    # calculate the ideal threshold
    ideal_threshold, false_positive_rates, true_positive_rates = calculate_ideal_threshold(unique_speeds, no_intention, has_intention)
    print(false_positive_rates)

    # plotting the ROC curve
    create_ROC_curve(false_positive_rates, true_positive_rates, ideal_threshold)