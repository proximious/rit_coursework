"""
@author: Alex Iacob ai9388
         Benson Yan by7662
Prof. Kinsman
November 7, 2022

Homework 6: Agglomeration Shopping Data
"""
import numpy as np
import csv
import sys
from scipy.cluster.hierarchy import linkage, dendrogram
import matplotlib.pyplot as plt

if __name__ == '__main__':
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
    # rounding the data to two decimal points
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

    labels = [num+1 for num in range(0, len(rowsNoId))]
    R = dendrogram(linkage(rowsNoId, method='centroid'), labels)
    plt.show()