"""
@author: Alex Iacob ai9388
Prof. Kinsman
September 15, 2022

Homework 1: Otsu's Method
"""

# getting appropriate imports
import pandas as pd
import numpy as np
import math
import matplotlib.pyplot as plt
import warnings


# storing the main data path with the given CSV
DATA_PATH = 'TrafficStation_Data_for_420\TrafficStation_01\Data_3417.csv'

# vehicle speed increments allowed
BIN_SIZE = 1

# PART 1
def quantize_data(path):
    """
    Helper method for Otsu's method
    Used to read the data from DATA_PATH and quantize it in increments of 1

    This is done by utilizing 'pandas' to read the CSV, then using a math floor
    function to separate the values. Utilizing a dictionary to hold every instance
    of each speed for easier use later. The values of the dictionary are sorted
    for easier usage then returned
    """
    quantized_data = {}

    # reading the CSV and separating the values
    given_data = pd.read_csv(path)
    speeds = given_data.values.tolist()
    
    for speed in speeds:
        # flooring to make the numbers easier to read
        floored = math.floor(speed[0]  / BIN_SIZE) * BIN_SIZE

        # if the speed is not in the dictionary we make a new key
        if str(floored) not in quantized_data:
            quantized_data[str(floored)] = []

        # appending the new speed into the dictionary
        quantized_data[str(floored)].append(floored)

    # making new dictionary to hold the sorted version
    sorted_data = {}
    sorted_keys = sorted(quantized_data.keys())

    # filling in the new dictionary
    for key in sorted_keys:
        sorted_data[key] = quantized_data[key]

    # returning just the values since they are the speed
    return list(sorted_data.keys()), list(sorted_data.values())


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


def otsu_method(quantized_data):
    """
    Otsu's method

    Clusters the given data into two groups. Runs a calculation to determine
    which given speed results in the lowest mixed variance
    """
    # setting ideals to infinity so the next calculated threshold and variance
    # is automatically better
    ideal_threshold = math.inf
    ideal_variance = math.inf

    # turning the quantized data to a numpy array
    numpy_quantized_data = np.array(list(quantized_data), dtype=object)

    # creating the mixed array dictionary for storing later
    mixed_variances = {}

    # iterating over every given speed
    for speed in range(numpy_quantized_data[0][0], numpy_quantized_data[-1][0] + 1):
        # splitting the array so the left has speeds <= current speed
        # and right > current speed
        left, right = split_array(quantized_data, speed)

        # calculating the respective weight
        weight_left = len(left) / (len(left) + len(right))
        weight_right = len(right) / (len(left) + len(right))

        # calculating the respective variance
        variance_left = np.var(left)
        variance_right = np.var(right)

        # formula for mixed variance
        mixed_variance = (weight_left * variance_left) + (weight_right * variance_right)

        # adding the mixed variance to the dictionary
        mixed_variances[str(speed)] = mixed_variance

        # checking if the newly acquired variance is better than the ideal
        if mixed_variance < ideal_variance:
            # replacing the old ideal values with better ones
            ideal_variance = mixed_variance
            ideal_threshold = speed

        # sorting the mixed variance dictionary by the keys
        sorted_keys = sorted(mixed_variances.keys())
        sorted_mixed_variances = {}

        for key in sorted_keys:
            sorted_mixed_variances[key] = mixed_variances[key]
    
    return ideal_variance, ideal_threshold, sorted_mixed_variances.values()
        

def plot_speed_histogram(quantized_data, cutoff):
    """
    Plots a histogram of the car speeds with the given data
    """
    # first we have to flatten the data
    flattened_quantized_data = [value for sub_list in quantized_data for value in sub_list]

    # now we can plot
    N, bins, patches = plt.hist(flattened_quantized_data, len(quantized_data), ec='gray')
    plt.xlabel('Vehicle speeds (mp/h) ')
    plt.ylabel('Number of occurances')
    plt.title('Histogram of vehicle speeds')

    # coloring in the subplots for different colors
    start_difference = cutoff - flattened_quantized_data[0]
    total_difference = flattened_quantized_data[-1] - flattened_quantized_data[0]
    for i in range(total_difference):
        if i <= start_difference:
            patches[i].set_fc('red')
        else:
            patches[i].set_fc('blue')    
    
    # creating the legend
    plt.legend(['below threshold', 'above threshold'])

    # adjusting the axis labels
    plt.show()


# PART 2
def plot_results(x_axis_info, y_axis_info):
    """
    The x values are meant to be the given speeds
    The y values are meant to be the mixed variances
    """
    # plotting the information
    plt.xlabel('Speeds')
    plt.ylabel('Mixed Variance')
    plt.title('Mixed variances versus the speed')

    # plotting the information
    plt.plot(x_axis_info, y_axis_info)

    # since each of the values in the s & y axis info are in order, the index
    # of the lowest mixed variance is for the corresponding information
    index_of_lowest_mixed_variance = y_axis_info.index(min(y_axis_info))
    plt.plot(x_axis_info[index_of_lowest_mixed_variance], min(y_axis_info), "ro")

    plt.show()


# PART 3
def regularization(quantized_data):
    """
    The regularization method is slight spinoff of otsu's method where
    cost_function = objective_function + regularization
    """
    norm_factor = 50

    # setting ideals to infinity so the next calculated threshold and variance
    # is automatically better
    ideal_threshold = math.inf
    ideal_variance = math.inf
    ideal_cost_function = math.inf

    # turning the quantized data to a numpy array
    numpy_quantized_data = np.array(list(quantized_data), dtype=object)

    # iterating over every given speed
    for speed in range(numpy_quantized_data[0][0], numpy_quantized_data[-1][0] + 1):
        # splitting the array so the left has speeds <= current speed
        # and right > current speed
        left, right = split_array(quantized_data, speed)

        # calculating the respective weight
        weight_left = len(left) / (len(left) + len(right))
        weight_right = len(right) / (len(left) + len(right))

        # calculating the regularization
        regularization = abs(len(left) - len(right)) / norm_factor

        # calculating the respective variance
        variance_left = np.var(left)
        variance_right = np.var(right)

        # formula for mixed variance
        mixed_variance = (weight_left * variance_left) + (weight_right * variance_right)

        # getting the objective function
        obj_function = mixed_variance

        # calculating the cost function
        cost_function = obj_function + regularization

        # checking if the newly acquired variance is better than the ideal
        if cost_function < ideal_cost_function:
            # replacing the old ideal values with better ones
            ideal_variance = mixed_variance
            ideal_threshold = speed
            ideal_cost_function = cost_function

    return ideal_variance, ideal_threshold, ideal_cost_function

        
if __name__ == '__main__':
    warnings.filterwarnings('ignore')

    # getting the variance and threshold of the given data path
    unique_speeds, all_the_speeds = quantize_data(DATA_PATH)

    otsu_variance, otsu_thresh, all_mixed_variances = otsu_method(all_the_speeds)
    print('OTSU: the variance is: ', otsu_variance)
    print('OTSU: the threshold is: ', otsu_thresh)

    # plotting the speed histogram
    plot_speed_histogram(all_the_speeds, otsu_thresh)

    # plotting the values that we got from the quantized data
    plot_results(unique_speeds, list(all_mixed_variances))

    # running the regularization function
    reg_variance, reg_thresh, reg_cost = regularization(all_the_speeds)
    print('REGULARIZATION: the variance is: ', reg_variance)
    print('REGULARIZATION: the threshold is: ', reg_thresh)

