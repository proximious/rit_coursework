from sklearn.neural_network import MLPClassifier
import time
import numpy as np


class NeuralNetwork:
    neural_network = None
    packet_indexes = {}
    packet_keys = {}
    X = []
    Y = []
    training_time = 0
    testing_time = 0

    def __init__(self, X, Y):
        self.X = X
        self.Y = Y
        self.neural_network = MLPClassifier(solver='lbfgs', alpha=1e-5, max_iter=800,
                                            hidden_layer_sizes=(5, 2), random_state=1)

        i = 0
        packet_types = np.unique(np.array(self.Y)).tolist()
        for type in packet_types:
            self.packet_keys[i] = type
            self.packet_indexes[type] = i
            i += 1

    def train_data(self):
        self.training_time = time.time()
        self.neural_network.fit(self.X, self.Y)
        self.training_time = time.time() - self.training_time

    def test_data(self, X, Y):
        self.testing_time = time.time()
        # Y_prediction is the prediction from the neural network after it was fitted with training data
        Y_prediction = self.neural_network.predict(X)

        eval_matrix = np.zeros((len(self.packet_keys), len(self.packet_keys)))

        for i in range(0, len(Y)):
            eval_matrix[self.packet_indexes[Y_prediction[i]]][self.packet_indexes[Y[i]]] += 1
        self.testing_time = time.time() - self.testing_time
        return eval_matrix, self.packet_keys

    def get_times(self):
        return self.training_time, self.testing_time