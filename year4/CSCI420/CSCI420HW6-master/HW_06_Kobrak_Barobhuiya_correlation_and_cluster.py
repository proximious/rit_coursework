"""
This program outputs information on the cross-correlation and clustering of
the given dataset.

@package numpy
@package scipy
@package matplotlib
"""
import numpy as np
import csv
import sys
from scipy.cluster.hierarchy import linkage, dendrogram
import matplotlib.pyplot as plt
from HW_06_Kobrak_Barobhuiya_cluster import Cluster

"""
Calculate the euclidean distance between 2 arbitrary length
vectors
@param vector1: an n-length list
@param vector2: an n-length list
@return: the L2 distance between the vectors
"""
def euclideanDist(vector1, vector2):
    if len(vector1) != len(vector2): return None
    dist = 0
    for idx in range(0, len(vector1)):
        dist += pow(vector1[idx] - vector2[idx], 2)
    return pow(dist, 1/2)


"""
Helper function to track the last 18 clusters that get merged
into larger functions during the agglomerative clustering process

@param arr: list to track the small clusters in
@param newSize: the size of the next smallest cluster that got merged
"""
def trackLast18SmallerClusters(arr, newSize):
    arr.append(newSize)
    if len(arr) > 18:
        arr.pop(0)

"""
Given a list of data rows, generate a hierarchical clustering using the centroid
as the linkage method, and the euclidean distance between the centroids. This function
keeps track of clusters created by appending them to a list passed in

@param matrix: list of data rows
@param resultantClusters: every time a new cluster is generated, append it to this list
@return: a list of the last 18 clusters that got absorbed into a bigger cluster
"""
def aggloCluster(matrix, resultantClusters):
    # for each row in matrix, create a singleton cluster
    clusters = [Cluster([ {'id': matrix[idx][0], 'elements': matrix[idx][1:]} ], idx+1) for idx in range(0, len(matrix))]
    resultantClusters.extend(clusters)
    sizesOfLast18SmallerClusters = []
    while len(clusters) > 1:
        minDistance = np.inf
        closestClusters = (None, None)
        for idx in range(0, len(clusters)-1):
            for kdx in range(idx+1, len(clusters)):
                distance = euclideanDist(clusters[idx].center, clusters[kdx].center)
                if distance < minDistance:
                    minDistance = distance
                    closestClusters = (clusters[idx], clusters[kdx])
        biggerCluster = None
        smallerCluster = None
        if len(closestClusters[0].members) > len(closestClusters[1].members):
            biggerCluster = closestClusters[0]
            smallerCluster = closestClusters[1]
        else:
            biggerCluster = closestClusters[1]
            smallerCluster = closestClusters[0]
        trackLast18SmallerClusters(sizesOfLast18SmallerClusters, len(smallerCluster.members))
        biggerCluster.mergeCluster(smallerCluster)
        resultantClusters.append(biggerCluster)
        clusters.remove(smallerCluster)
    return sizesOfLast18SmallerClusters


"""
Print a cluster in a format that is easier to read

@param rows: the data rows that make up the cluster's members
@param header: a list of the column names for each entry in a cluster member
"""
def printCluster(rows, header):
    for colName in header:
        print(colName, end='\t')
    print()
    for member in rows:
        for el in member:
            print(el, end='\t')
        print()

def main():
    filename = sys.argv[1]
    # rows is the csv data
    rows = []
    header = []
    # read in the data
    with open(filename, 'r') as dataFile:
        csvData = csv.reader(dataFile)
        header = list(map(lambda colName: colName.strip(), next(csvData)))
        curRow = next(csvData, None)
        while curRow != None:
            rows.append(list(map(lambda el: float(el), curRow)))
            curRow = next(csvData, None)
    
    # create a version of the csv data that doesn't have the id attribute
    rowsNoId = list(map(lambda row: row[1:], rows))
    # generate the correlation matrix
    correlationMatrix = np.corrcoef(rowsNoId, rowvar=False)
    correlationMatrix = np.around(correlationMatrix, decimals=2)

    # Start code to print findings on attribute correlations
    print(end='\t')
    for colName in header[1:]:
        print(colName, end='\t')
    print()
    bestCorrelation = 0
    bestAttributes = (None, None)
    for row in range(0, len(header[1:])):
        print(header[1:][row], end='\t')
        for col in range(0, len(header[1:])):
            # check the top 
            if row < col and abs(bestCorrelation) < abs(correlationMatrix[row,col]):
                bestCorrelation = correlationMatrix[row,col]
                bestAttributes = (header[1:][row], header[1:][col])
            print(correlationMatrix[row,col], end='\t')
        print()
    print('Strongest Correlation: ', bestCorrelation, bestAttributes)
    # End code to print findings on attribute correlations

    # the array of all clusters generated in the process of agglomerative clustering
    resultantClusters = []
    # run the agglomerative clustering, print the average cluster prototype
    sizesOfLast18Clusters = aggloCluster(rows, resultantClusters)
    print('Sizes of Last 18 Smallest Clusters: ', sizesOfLast18Clusters)
    print('Average Cluster Prototype: ', resultantClusters[-1].center)

    # create a list of all the sizes of the clusters generated
    length_of_clusters = []
    for cluster in resultantClusters:
        length_of_clusters.append(len(cluster.members))

    # print the sizes of all the clusters along with the minimum/maximum sizes 
    for idx in range(0, len(length_of_clusters)):
        print(length_of_clusters[idx], end=', ')
        if idx != 0 and idx % 18 == 0:
            print()
    print("The minimum size of cluster is: ", min(length_of_clusters))
    print("The maximum size of cluster is: ", max(length_of_clusters))

    # generate a dendrogram of the data
    plt.figure(figsize=(9, 3))
    R = dendrogram(linkage(rowsNoId, method='centroid'), labels=[num+1 for num in range(0, len(rowsNoId))])

    """
    From here until the end of main, we are using the "natural" clustering found by the scipy
    dendrogram method to partition our dataset into four "natural" clusters, and printing out
    the centroid of of each of these four clusters.
    """
    print('\n\t==================')
    print('\tNATURAL CLUSTERS')
    print('\t==================')

    cluster1 = []
    cluster2 = []
    cluster3 = []
    cluster4 = []
    for idx in range(0, len(R['ivl'])):
        color =  R['leaves_color_list'][idx]
        if color == 'C1':
            cluster1.append(rows[R['ivl'][idx]-1])
        elif color == 'C2':
            cluster2.append(rows[R['ivl'][idx]-1])
        elif color == 'C3':
            cluster3.append(rows[R['ivl'][idx]-1])
        else:
            cluster4.append(rows[R['ivl'][idx]-1])


    c1COM = Cluster([ {'id': member[0], 'elements': member[1:]} for member in cluster1], 1).center
    c2COM = Cluster([ {'id': member[0], 'elements': member[1:]} for member in cluster2], 2).center
    c3COM = Cluster([ {'id': member[0], 'elements': member[1:]} for member in cluster3], 3).center
    c4COM = Cluster([ {'id': member[0], 'elements': member[1:]} for member in cluster4], 4).center

    print('\nC1\n')
    printCluster([list(map(lambda el: round(el), c1COM))], header[1:])
    print('\nC2\n')
    printCluster([list(map(lambda el: round(el), c2COM))], header[1:])
    print('\nC3\n')
    printCluster([list(map(lambda el: round(el), c3COM))], header[1:])
    print('\nC4\n')
    printCluster([list(map(lambda el: round(el), c4COM))], header[1:])

    # display the generated dendrogram
    plt.show()
    


if __name__ == '__main__':
    main()