"""
@author: Alex Iacob ai9388
         Benson Yan by7662
@filename: HW07_ai9388_by7662.py
CSCI 431
Homework 7: PCA and KMeans
"""

from fileinput import filename
import matplotlib.pylab as plt
import numpy as np
from sklearn.cluster import KMeans

#Reading the data:
def read_data(fileName):
    """
    Reads the data given to use and parses them
    :param fileName: name of the file
    :return: using numpy returns the data after being parsed
    """
    data = np.genfromtxt(fileName, delimiter=',', skip_header=1)
    return data


def find_cumulativesum(eigenValues):
    """
    Helper function to calcualte the cumsum
    :param eigenValues: eigenvalues
    :return: the value of the cumulaitve sum
    """
    normal = eigenValues / np.sum(abs(eigenValues))
    cumulative_sum = np.cumsum(normal)
    return cumulative_sum


def graph_cumulativesum_eigval(cumulative_sum):
    """
    Graphs the cumulative sum vs the eigenvalues
    :param cumulative_sum:
    :return:
    """
    plt.figure(1)
    plt.xlabel("Eigenvalues")
    plt.ylabel("Cumulative Sum")
    plt.title("Eigenvalue Vs. Cumulative Sum")
    plt.plot(np.arange(len(cumulative_sum)), cumulative_sum)


def graph_transformed_data(transformed_data):
    """
    Graphs the eigenvecots onto the original agglomeration data
    :param transformed_data: eigenvectors after the dot prodcut
    :return: the graph
    """
    plt.figure(2)
    plt.title("Projected Eigenvectors Onto Original Agglomeration")
    plt.xlabel("Eigenvector One")
    plt.ylabel("Eigenvector Two")
    plt.plot(*transformed_data.T, 'o')


def graph_cluster_kmeans(transformed_data, labels):
    """
    Graphs the 4 different clusters with different colors using the kmeans value
    :param transformed_data:
    :param labels:
    :return:
    """
    plt.figure(3)
    plt.title("Clustered Data")
    plt.xlabel("Eigenvector One")
    plt.ylabel("Eigenvector Two")
    cluster_label = np.unique(labels)
    colors = ['ro', 'go', 'bo', 'yo'] #colors for the different clusters

    assert len(cluster_label) == len(colors) #checks if the cluster length of cluster label is equal to lenght of colors
    for i, label in enumerate(cluster_label):
        plt.plot(*transformed_data[labels == label].T, colors[i])


def find_transform_k_means(eigenvector):
    """
    Finds out agglomeration on the eigenvector and finds the kmeans
    :param eigenvector: the first two eigenvectors
    :return:
    """
    eigendotvector = eigenvector.dot(data.T).T
    graph_transformed_data(eigendotvector)

    #finds the center of the mass

    #n-cluster the number of clusters you want to see on the graph:
    kMeans = KMeans(n_clusters=4).fit(eigendotvector)
    #Then plot cluster transformed graph with K-means:
    graph_cluster_kmeans(eigendotvector, kMeans.labels_)

    #Then find the ceneter of the clusters using the kmeans variables
    center_of_kmeans = kMeans.cluster_centers_ #find the center of the mass
    print("Center of Cluster is: \n", center_of_kmeans)
    print(" ")
    print("Eigenvector:")
    print(eigenvector)

    #Part 10: To check what would happen if we multiplied eigenvector with the center of the mass
    question10 = center_of_kmeans.dot(eigenvector).T
    print("Center of mass dot eigenvector")
    question10 = np.transpose(question10)
    print(question10)

#Test
def vectortimescenter(question10):
    plt.figure(4)
    plt.title("Projected Eigenvectors Onto Original Agglomeration")
    plt.xlabel("Eigenvector One")
    plt.ylabel("Eigenvector Two")
    plt.plot(*question10.T, 'o')

#Finding correlation coeffiecent:
def find_correlationcoeff(data):
    """
    Finding out the eigenvector and egivenvalue using numpy library
    :param data:
    :return:
    """
    cross_corr = np.corrcoef(data, rowvar=False)
    print(cross_corr)
    assert cross_corr.shape == (20,20)
    eigenval, eigenVector = np.linalg.eig(cross_corr)

    #Need to sort them from highest to lowest absolute value:
    #Used the sorted function

    #Find the absolute value from highest to least and puts them into a sorted list where reverse means it will be from highest to lowest
    sort_eigenvalues = sorted(zip(eigenval, eigenVector.T), key=lambda pair: abs(pair[0]), reverse=True)

    eigenval, eigenVector = map(np.array, zip(*sort_eigenvalues))
    print("First three eigenvectors: ")
    print("Eigenvectors: ", eigenVector[:3])
    print("----------------------------------------")

    #plot the cumulative sum of the normalized eigenvalues

    #Finding cumulative sum:
    cumulative_sum = find_cumulativesum(eigenval)
    print("Cumulative Sum: \n")
    print(cumulative_sum)
    graph_cumulativesum_eigval(cumulative_sum)
    print("----------------------------------------")

    #Finding Transformed data and K-means:
    find_transform_k_means(eigenVector[:2])
    print("----------------------------------------")


if __name__ == '__main__':
    fileName = "HW_CLUSTERING_SHOPPING_CART_v2221A_NO_HEADER_and_no_ID_COLUMN.csv"
    data = read_data(fileName)[:,0:]
    find_correlationcoeff(data)
    plt.show()