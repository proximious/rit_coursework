"""
@author: Alex Iacob ai9388
Prof. Kinsman
October 26, 2022

Homework 5: Decision tree creation
"""

# getting necessary imports
import csv
import math
import textwrap
import pandas as pd

# storing the main data path with the given CSV
DATA_PATH = 'Abominable_Data_HW_LABELED_TRAINING_DATA__v770_2221.csv'
VALIDATION_DATA = 'Abominable_VALIDATION_Data_FOR_STUDENTS_v770_2221.csv'
CLASSIFIER_FILE = 'HW05_ai9388_by7662_Trained_Classifier.py'
CLASSIFICATIONS_FILE = 'HW05_ai9388_by7662__MyClassifications.csv'
TRAINED_CLASSIFICATIONS = 'trained_classifications.csv'

FOUR = '    '
EIGHT = '        '

# Decision tree class
class DecisionTree:
    def __init__(self, condition, left, right, depth):
        """
        Initializing the class using a recursive definition of the class
        """
        self.condition = condition
        self.left = left
        self.right = right
        self.depth = depth

    def __str__(self):
        tabs = EIGHT
        for i in range(0, self.depth):
            tabs += FOUR

        if self.left is None and self.right is None:
            return self.condition

        return f"if {self.condition}:\n{tabs}    {self.left}\n{tabs}else:\n{tabs}    {self.right}"

def calculate_gini(data):
    """
    Calculates the GINI index from the list of node's data
    :param data: the list of nodes
    :return: the gini index for each nodes
    """
    # getting the number of assam snow folks
    numClassA = len([point for point in data if point['ClassID'] == -1])
    # getting the number of bhutan snow folks
    numClassB = len([point for point in data if point['ClassID'] == 1])

    totalSize = numClassA + numClassB

    return 1 - (numClassA/totalSize)**2 - (numClassB/totalSize)**2


def calculate_weighted_gini(left, right):
    """
    Calculates the weighted GINI index using the left node and right node of each data set
    :param left: list of nodes where attribute <= threshold
    :param right: list of nodes where attributes > threshold
    :return: the weighted gini index
    """
    # formula for the weighted gini follows as
    # (percent of left nodes) * gini_index of left nodes + (percent of right nodes) * gini_index of right nodes

    left_gini = (len(left) / (len(left) + len(right))) * calculate_gini(left)
    right_gini = (len(right) / (len(left) + len(right))) * calculate_gini(right)

    return left_gini + right_gini


def find_best_split_condition(tree_data):
    """
    Used to find out what value will be the best list
    It calls the weightedGiniIndex function using the left node and right node

    :param tree_data:
    :return: ideal_split_condition, ideal_left_node, ideal_right_node
    """
    # making default values
    best_weighted_gini = math.inf
    ideal_left_node = []
    ideal_right_node = []
    ideal_split_condition = ''

    for attr in tree_data[0].keys():
        # we have to skip over the ClassID and ClassName
        if attr == 'ClassID' or attr == 'ClassName':
            continue
        
        # getting a set of all of the thresholds
        thresholds = list(set([point[attr] for point in tree_data]))
        
        for threshold in thresholds:
            # left_node corresponds to if clause in decision tree node
            # left nodes if the attribute is less than or equal to threshold
            left_node = [point for point in tree_data if point[attr] <= threshold]
            
            # right_node corresponds to else clause in decision tree node
            # right nodes if attribute is greater than threshold
            right_node = [point for point in tree_data if point[attr] > threshold] 

            if len(left_node) != 0 and len(right_node) != 0:
                # calls the weighted gini to get the value
                calculted_gini = calculate_weighted_gini(left_node, right_node) 

                # if calculated gini value is less than our ideal, we replace our values
                if calculted_gini < best_weighted_gini:
                    best_weighted_gini = calculted_gini
                    ideal_left_node = left_node
                    ideal_right_node = right_node
                    # puts it into a row from the template
                    ideal_split_condition = f'row[\'{attr}\'] <= ' + str(threshold) 
    
    return ideal_split_condition, ideal_left_node, ideal_right_node


def generate_decision_tree(tree_data, depth = 0):
    """
    Creates a decision tree utilizing the DecisionTree class
    :param tree_data: the snowfolk data
    :param depth: the depth of the decision tree
    """
    # getting the number of assam snow folks and their percentage
    numClassA = len([point for point in tree_data if point['ClassID'] == -1])
    percentageClassA = numClassA / len(tree_data)

    # getting the number of bhutan snow folks and their percentage
    numClassB = len([point for point in tree_data if point['ClassID'] == 1])
    percentageClassB = numClassB / len(tree_data)

    # we know that our base case should be IF
    # either percentage is > 89%
    # number of nodes < 23 data points per node 
    # depth > 9 levels of decision nodes
    if (percentageClassA > 0.89 or percentageClassB > 0.89 or depth > 9 or len(tree_data) < 23):
        if numClassA >= numClassB:
            return DecisionTree('row_class = \'-1\'', None, None, depth)
        else:
            return DecisionTree('row_class = \'+1\'', None, None, depth)
    else:
        
        ideal_split_condition, ideal_left_node, ideal_right_node = find_best_split_condition(tree_data)

        # using a recursive definition, we can continuously generate decision trees 
        # by taking its left and right nodes
        return DecisionTree(ideal_split_condition, generate_decision_tree(ideal_left_node, depth + 1), 
                            generate_decision_tree(ideal_right_node, depth + 1), depth)


def write_to_file(decision_tree, destination_file):
    """
    Writing the decision tree to a given file
    :param decision_tree: the string version of the decision tree
    :param destination_file: the file we're writing to
    """
    with open(destination_file, 'w') as file:
        # writing the heading to the file
        file.write(textwrap.dedent('''\
            """
            @author: Alex Iacob ai9388
                     Benson Yan by7662
            Prof. Kinsman
            October 26, 2022

            Homework 5: Decision tree creation
            """

            import csv

            DATA_PATH = 'Abominable_VALIDATION_Data_FOR_STUDENTS_v770_2221.csv'
            RESULTS_FILE = 'HW05_ai9388_by7662__MyClassifications.csv'

            def write_to_file(all_results):
                with open(RESULTS_FILE, 'w', newline='') as file:
                    writer = csv.writer(file)
                    header = ['ClassID']
                    writer.writerow(header)
                    for row_class in all_results:
                        writer.writerow(row_class)

            if __name__ == '__main__':
                data = []
                with open(DATA_PATH) as csv_data:
                    csv_data = csv.reader(csv_data)
                    header = next(csv_data, None)
                    current_row = next(csv_data, None)
                    while current_row != None:
                        data_point = dict()
                        for index in range(0, len(header)):
                            try:  
                                data_point[header[index]] = float(current_row[index])
                            except ValueError:
                                data_point[header[index]] = current_row[index]
                        data.append(data_point)
                        current_row = next(csv_data, None)
                
                all_results = []
                for row in data:
                    row_class = None
        '''))
        
        file.write("        " + decision_tree +
        "\n        all_results.append(str(row_class))" + "\n    write_to_file(all_results)")


def create_confusion_matrix(actual, predicted):
    y_actual = pd.Series(actual, name='Actual')
    y_predicted = pd.Series(predicted, name='Predicted')

    #create confusion matrix
    print(pd.crosstab(y_actual, y_predicted))
    
if __name__ == '__main__':
    # storing all snowfolk data in array
    snowfolk_data = []

    # reading through the data path
    with open(DATA_PATH) as csvFile:
        csv_data = csv.reader(csvFile)

        # skipping over the header
        header = next(csv_data, None)

        # moving onto the next row
        current_row = next(csv_data, None)

        # iterating until the end of file
        while current_row != None:
            # making a set per each individual snow folk
            data_point = dict()

            for index in range(0, len(header)):
                if header[index] == 'ClassName':
                    data_point[header[index]] = current_row[index]
                elif header[index] == 'ClassID':
                    data_point[header[index]] = int(current_row[index])
                elif header[index] == 'Ht':
                    # quantizing every height to nearest 4 cm
                    data_point[header[index]] = round(float(current_row[index]) / 4) * 4
                else:
                    # everything else should be quantized to nearest 2 value
                    data_point[header[index]] = round(float(current_row[index]) / 2) * 2
            
            # saving individual snowfolk's data in the array
            snowfolk_data.append(data_point)

            # moving onto the next snowfolk
            current_row = next(csv_data, None)

    # creating the decision tree
    decision_tree = generate_decision_tree(snowfolk_data)

    # writing the created decision tree to the classifier file
    write_to_file(str(decision_tree), CLASSIFIER_FILE)

    # saving the actual classifications
    actual_classifications = []
    class_ids = pd.read_csv(DATA_PATH, usecols=['ClassID'])
    list_of_lists = class_ids.values.tolist()
    
    for sub_list in list_of_lists:
        actual_classifications += sub_list

    # we have to go through the actuals and turn everything into a string
    # to be able to hold a +1 instead of just 1
    for i in range(len(actual_classifications)):
        if actual_classifications[i] == -1:
            actual_classifications[i] = '-1'
        elif actual_classifications[i] == 1:
            actual_classifications[i] = '+1'

    predicted_classifications = []
    with open(TRAINED_CLASSIFICATIONS, 'r') as file:
        # skipping the header
        next(file)

        for line in file:
            # appending only the sign and 1, not the \n on the line
            predicted_classifications.append(line[0:2])

    # making the confusion matrix
    create_confusion_matrix(actual_classifications, predicted_classifications)
