"""
@author: Alex Iacob ai9388
Prof. Kinsman
September 25, 2022

Homework 2: Speed Classification
"""

def classifier(speed):
    if speed <= 64:
        intent = 1
    else:
        intent = 2
    return intent    