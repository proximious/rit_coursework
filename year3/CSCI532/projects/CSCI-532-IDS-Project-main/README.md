# CSCI-532-IDS-Project
Brendon Dong, Alex Iacob, Kiersten Reina
## Project 2 - Neural Networks in IDS Design


### run_all.py
This is used to create, run, and evaluate all of the neural networks for each of the IDSs in this project. Constant booleans at the top of the file are used to determine what to run. Extra functions are included for printing and interpreting the confusion matrices, and splitting the initial data to insert into the neural networks.
```
py run_all.py
```



### data_separator.py
This program is run to take data from a set directory ('/original_dataset') and split all of the files in a 3:1 ratio. The larger portion is used as training data ('/training_sets') and the smaller portion is used as testing data ('/testing_sets'). The different files consist of the 5 largest attacks individually, 5 largest attacks together, and the normals. NOTE: The training and testing data directories will be cleared when this is run! This file is almost identical to the one in Project 1, with a few fixes.
```
py data_separator.py
```



### DataCombiner.py
This contains the DataCombiner class, which creates a dictionary that assigns a file's contents to its name as the key. A function is included to classify and separate the contents of this dictionary based on the file names supplied. For example, if "Optimized_IPSweep" and "Optimized_Back" are supplied in the list, then the dictionary is returned with the contents of "Optimized_IPSweep", "Optimized_Back", and then the rest of the files from the directory as "other".
A duplicate DataCombiner2 is included, as we could not figure out how to create a second object of the same class. The second DataCombiner simply overwrote the first one each time, leading to the test and training data drawing from the same directory.



### NeuralNetwork.py
This wraps around the scikit-learn Neural Network implementation with their Multi-Layer Perceptron Classifier. We use this to train and test neural networks, record how much time it takes to do so, and create a confusion matrix to evaluate the IDS and its performance later on.