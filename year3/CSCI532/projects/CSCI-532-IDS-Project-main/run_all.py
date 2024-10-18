import data_separator
import pandas as pd
from DataCombiner import DataCombiner
from DataCombiner import DataCombiner2
from NeuralNetwork import NeuralNetwork

RUN_DATA_SEPARATOR = False

RUN_IPSWEEP = True
RUN_NEPTUNE = True
RUN_PORTSWEEP = True
RUN_NMAP = True
RUN_BACK = True

RUN_ALL_FIVE = True
RUN_ANOMALY = True


def split_to_X_and_Y(dictionary_of_lists):
    """
    Takes in data classified in a dictionary and splits it to an X and Y used for classification.
    """
    X = []
    Y = []
    for key in dictionary_of_lists:
        X = X + dictionary_of_lists[key]
        Y = Y + [key] * len(dictionary_of_lists[key])
    return X, Y


def run_ipsweep_nn(training_data_combiner, testing_data_combiner):
    # ==========================
    # MISUSE IDS CLASSIFICATION
    # (IPSweep)
    # ==========================
    training_data = training_data_combiner.get_separate_data(["Optimized_IPSweep"])
    testing_data = testing_data_combiner.get_separate_data(["Optimized_IPSweep"])
    train_X, train_Y = split_to_X_and_Y(training_data)
    test_X, test_Y = split_to_X_and_Y(testing_data)

    print('Running IPSweep...')
    ipsweep = NeuralNetwork(train_X, train_Y)
    print('Training IPSweep...')
    ipsweep.train_data()
    print('Testing IPSweep...')
    eval_matrix = ipsweep.test_data(test_X, test_Y)[0]
    times = ipsweep.get_times()
    print_stats(eval_matrix[0][0], eval_matrix[1][1], eval_matrix[0][1], eval_matrix[1][0], times)


def run_neptune_nn(training_data_combiner, testing_data_combiner):
    # ==========================
    # MISUSE IDS CLASSIFICATION
    # (Neptune)
    # ==========================
    training_data = training_data_combiner.get_separate_data(["Optimized_Neptune"])
    testing_data = testing_data_combiner.get_separate_data(["Optimized_Neptune"])
    train_X, train_Y = split_to_X_and_Y(training_data)
    test_X, test_Y = split_to_X_and_Y(testing_data)

    print('Running Neptune...')
    neptune = NeuralNetwork(train_X, train_Y)
    print('Training Neptune...')
    neptune.train_data()
    print('Testing Neptune...')
    eval_matrix = neptune.test_data(test_X, test_Y)[0]
    times = neptune.get_times()
    print_stats(eval_matrix[0][0], eval_matrix[1][1], eval_matrix[0][1], eval_matrix[1][0], times)


def run_portsweep_nn(training_data_combiner, testing_data_combiner):
    # ==========================
    # MISUSE IDS CLASSIFICATION
    # (PortSweep)
    # ==========================
    training_data = training_data_combiner.get_separate_data(["Optimized_PortSweep"])
    testing_data = testing_data_combiner.get_separate_data(["Optimized_PortSweep"])
    train_X, train_Y = split_to_X_and_Y(training_data)
    test_X, test_Y = split_to_X_and_Y(testing_data)

    print('Running PortSweep...')
    portSweep = NeuralNetwork(train_X, train_Y)
    print('Training PortSweep...')
    portSweep.train_data()
    print('Testing PortSweep...')
    eval_matrix = portSweep.test_data(test_X, test_Y)[0]
    times = portSweep.get_times()
    print_stats(eval_matrix[0][0], eval_matrix[1][1], eval_matrix[0][1], eval_matrix[1][0], times)


def run_nmap_nn(training_data_combiner, testing_data_combiner):
    # ==========================
    # MISUSE IDS CLASSIFICATION
    # (NMap)
    # ==========================
    training_data = training_data_combiner.get_separate_data(["Optimized_NMap"])
    testing_data = testing_data_combiner.get_separate_data(["Optimized_NMap"])
    train_X, train_Y = split_to_X_and_Y(training_data)
    test_X, test_Y = split_to_X_and_Y(testing_data)

    print('Running NMap...')
    nMap = NeuralNetwork(train_X, train_Y)
    print('Training NMap...')
    nMap.train_data()
    print('Testing NMap...')
    eval_matrix = nMap.test_data(test_X, test_Y)[0]
    times = nMap.get_times()
    print_stats(eval_matrix[0][0], eval_matrix[1][1], eval_matrix[0][1], eval_matrix[1][0], times)


def run_back_nn(training_data_combiner, testing_data_combiner):
    # ==========================
    # MISUSE IDS CLASSIFICATION
    # (Back)
    # ==========================
    training_data = training_data_combiner.get_separate_data(["Optimized_Back"])
    testing_data = testing_data_combiner.get_separate_data(["Optimized_Back"])
    train_X, train_Y = split_to_X_and_Y(training_data)
    test_X, test_Y = split_to_X_and_Y(testing_data)

    print('Running Back...')
    back = NeuralNetwork(train_X, train_Y)
    print('Training Back...')
    back.train_data()
    print('Testing Back...')
    eval_matrix = back.test_data(test_X, test_Y)[0]
    times = back.get_times()
    print_stats(eval_matrix[0][0], eval_matrix[1][1], eval_matrix[0][1], eval_matrix[1][0], times)


def run_five_nn(training_data_combiner, testing_data_combiner):
    # ===========================================
    # FIVE ATTACKS CLASSIFICATION
    # (IPSweep, Neptune, PortSweep, NMap, Back)
    # ===========================================
    training_data = training_data_combiner.get_separate_data(["Optimized_IPSweep", "Optimized_Smurf",
                                                              "Optimized_Satan", "Optimized_Neptune",
                                                              "Optimized_PortSweep"])
    testing_data = testing_data_combiner.get_separate_data(["Optimized_IPSweep", "Optimized_Smurf",
                                                            "Optimized_Satan", "Optimized_Neptune",
                                                            "Optimized_PortSweep"])
    train_X, train_Y = split_to_X_and_Y(training_data)
    test_X, test_Y = split_to_X_and_Y(testing_data)

    print('Running all five attacks...')
    five_attacks = NeuralNetwork(train_X, train_Y)
    print('Training all five attacks...')
    five_attacks.train_data()
    print('Testing all five attacks...')
    eval_matrix, packet_keys = five_attacks.test_data(test_X, test_Y)
    times = five_attacks.get_times()
    order = [packet_keys[0], packet_keys[1], packet_keys[2], packet_keys[3], packet_keys[4], packet_keys[5]]
    df = pd.DataFrame(eval_matrix, order, order)
    print(df)
    df.to_csv("five_attacks_confusion_matrix.csv")

    print("Training time (seconds): " + "{:.5}s".format(times[0]) + "\n"
    + "Testing time (seconds): " + "{:.5}s".format(times[1]))


def run_anomaly_nn(training_data_combiner, testing_data_combiner):
    # ==========================
    # ANOMALY IDS CLASSIFICATION
    # ==========================
    training_data = training_data_combiner.get_separate_data(["Optimized_Normal"])
    testing_data = testing_data_combiner.get_separate_data(["Optimized_Normal"])
    train_X, train_Y = split_to_X_and_Y(training_data)
    test_X, test_Y = split_to_X_and_Y(testing_data)

    print('Running Anomaly...')
    anomaly = NeuralNetwork(train_X, train_Y)
    print('Training Anomaly...')
    anomaly.train_data()
    print('Testing Anomaly...')
    eval_matrix = anomaly.test_data(test_X, test_Y)[0]
    times = anomaly.get_times()
    print_stats(eval_matrix[0][0], eval_matrix[1][1], eval_matrix[0][1], eval_matrix[1][0], times)


def print_stats(true_positive, true_negative, false_positive, false_negative, times):
    total_packets = true_positive + true_negative + false_positive + false_negative
    statistics = ("Total Packets: " + str(total_packets) + "\n" +
                  "----------------------------------------------------\n" +
                  "True Positives: " + str(true_positive) + " ; " + "{:.5%}".format(true_positive / total_packets) +
                  " of total packets\n" +
                  "True Negatives: " + str(true_negative) + " ; " + "{:.5%}".format(true_negative / total_packets) +
                  " of total packets\n" +
                  "False Positives: " + str(false_positive) + " ; " + "{:.5%}".format(
                false_positive / total_packets) +
                  " of total packets\n" +
                  "False Negatives: " + str(false_negative) + " ; " + "{:.5%}".format(
                false_negative / total_packets) +
                  " of total packets\n" +
                  "----------------------------------------------------\n" +
                  "Accuracy: " + "{:.5%}\n".format((true_positive + true_negative) / total_packets) +
                  "False Positive Rate: " + "{:.5%}\n".format(false_positive / (false_positive + true_negative)) +
                  "False Negative Rate: " + "{:.5%}\n".format(false_negative / (false_negative + true_positive)) +
                  "----------------------------------------------------\n" +
                  "Training time (seconds): " + "{:.5}s".format(times[0]) + "\n"
                  + "Testing time (seconds): " + "{:.5}s\n".format(times[1]))
    print("\n" + statistics)


def main():
    if RUN_DATA_SEPARATOR:
        data_separator.main()

    testing_data_combiner = DataCombiner(data_separator.TEST_PATH)
    training_data_combiner = DataCombiner2(data_separator.TRAIN_PATH)


    if RUN_IPSWEEP:
        run_ipsweep_nn(training_data_combiner, testing_data_combiner)
    if RUN_NEPTUNE:
        run_neptune_nn(training_data_combiner, testing_data_combiner)
    if RUN_PORTSWEEP:
        run_portsweep_nn(training_data_combiner, testing_data_combiner)
    if RUN_NMAP:
        run_nmap_nn(training_data_combiner, testing_data_combiner)
    if RUN_BACK:
        run_back_nn(training_data_combiner, testing_data_combiner)

    if RUN_ALL_FIVE:
        run_five_nn(training_data_combiner, testing_data_combiner)
    if RUN_ANOMALY:
        run_anomaly_nn(training_data_combiner, testing_data_combiner)

if __name__ == "__main__":
    main()
