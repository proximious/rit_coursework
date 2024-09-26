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
        if row['HairLn'] <= 8:
            if row['BangLn'] <= 4:
                if row['TailLn'] <= -2:
                    row_class = '+1'
                else:
                    if row['Ht'] <= 168:
                        if row['Reach'] <= 136:
                            row_class = '-1'
                        else:
                            if row['TailLn'] <= 6:
                                if row['Age'] <= 48:
                                    if row['Ht'] <= 152:
                                        if row['Reach'] <= 154:
                                            if row['Ht'] <= 140:
                                                row_class = '-1'
                                            else:
                                                row_class = '-1'
                                        else:
                                            if row['HairLn'] <= 6:
                                                row_class = '-1'
                                            else:
                                                row_class = '+1'
                                    else:
                                        if row['Age'] <= 40:
                                            row_class = '-1'
                                        else:
                                            row_class = '-1'
                                else:
                                    if row['Reach'] <= 150:
                                        row_class = '-1'
                                    else:
                                        if row['Age'] <= 66:
                                            if row['Age'] <= 52:
                                                row_class = '-1'
                                            else:
                                                row_class = '+1'
                                        else:
                                            row_class = '-1'
                            else:
                                if row['HairLn'] <= 6:
                                    row_class = '-1'
                                else:
                                    if row['Reach'] <= 174:
                                        if row['Age'] <= 56:
                                            if row['Ht'] <= 144:
                                                row_class = '-1'
                                            else:
                                                row_class = '-1'
                                        else:
                                            row_class = '-1'
                                    else:
                                        row_class = '+1'
                    else:
                        row_class = '-1'
            else:
                if row['Ht'] <= 168:
                    if row['Reach'] <= 134:
                        if row['Age'] <= 34:
                            if row['BangLn'] <= 6:
                                if row['Age'] <= 28:
                                    if row['Reach'] <= 124:
                                        row_class = '+1'
                                    else:
                                        row_class = '+1'
                                else:
                                    if row['Reach'] <= 124:
                                        row_class = '-1'
                                    else:
                                        if row['Ht'] <= 120:
                                            row_class = '+1'
                                        else:
                                            row_class = '-1'
                            else:
                                row_class = '+1'
                        else:
                            row_class = '-1'
                    else:
                        if row['TailLn'] <= 6:
                            if row['Age'] <= 64:
                                if row['TailLn'] <= 2:
                                    row_class = '+1'
                                else:
                                    if row['BangLn'] <= 6:
                                        if row['HairLn'] <= 6:
                                            if row['Reach'] <= 144:
                                                row_class = '-1'
                                            else:
                                                row_class = '+1'
                                        else:
                                            if row['Ht'] <= 136:
                                                row_class = '+1'
                                            else:
                                                row_class = '+1'
                                    else:
                                        row_class = '+1'
                            else:
                                row_class = '-1'
                        else:
                            if row['BangLn'] <= 6:
                                if row['Age'] <= 52:
                                    if row['HairLn'] <= 6:
                                        if row['Ht'] <= 144:
                                            if row['Reach'] <= 150:
                                                row_class = '-1'
                                            else:
                                                row_class = '+1'
                                        else:
                                            if row['Reach'] <= 156:
                                                row_class = '-1'
                                            else:
                                                row_class = '-1'
                                    else:
                                        if row['Ht'] <= 164:
                                            if row['Ht'] <= 128:
                                                row_class = '+1'
                                            else:
                                                row_class = '+1'
                                        else:
                                            row_class = '-1'
                                else:
                                    if row['Reach'] <= 146:
                                        if row['TailLn'] <= 8:
                                            if row['Ht'] <= 140:
                                                row_class = '-1'
                                            else:
                                                row_class = '-1'
                                        else:
                                            row_class = '-1'
                                    else:
                                        if row['Ht'] <= 140:
                                            row_class = '+1'
                                        else:
                                            if row['Reach'] <= 150:
                                                row_class = '-1'
                                            else:
                                                row_class = '-1'
                            else:
                                if row['Ht'] <= 164:
                                    if row['Age'] <= 68:
                                        if row['Reach'] <= 138:
                                            row_class = '+1'
                                        else:
                                            if row['Ht'] <= 152:
                                                row_class = '+1'
                                            else:
                                                row_class = '+1'
                                    else:
                                        row_class = '-1'
                                else:
                                    row_class = '-1'
                else:
                    row_class = '-1'
        else:
            if row['BangLn'] <= 4:
                if row['HairLn'] <= 10:
                    if row['Ht'] <= 168:
                        if row['Reach'] <= 130:
                            row_class = '-1'
                        else:
                            if row['Age'] <= 44:
                                if row['Ht'] <= 144:
                                    if row['TailLn'] <= 0:
                                        row_class = '+1'
                                    else:
                                        if row['Reach'] <= 148:
                                            if row['Ht'] <= 140:
                                                row_class = '+1'
                                            else:
                                                row_class = '-1'
                                        else:
                                            if row['Age'] <= 38:
                                                row_class = '+1'
                                            else:
                                                row_class = '-1'
                                else:
                                    if row['Age'] <= 32:
                                        if row['Ht'] <= 148:
                                            row_class = '-1'
                                        else:
                                            row_class = '-1'
                                    else:
                                        if row['TailLn'] <= 8:
                                            if row['Age'] <= 36:
                                                row_class = '-1'
                                            else:
                                                row_class = '+1'
                                        else:
                                            if row['TailLn'] <= 18:
                                                row_class = '-1'
                                            else:
                                                row_class = '+1'
                            else:
                                if row['TailLn'] <= 2:
                                    row_class = '+1'
                                else:
                                    if row['Reach'] <= 148:
                                        if row['Age'] <= 48:
                                            if row['TailLn'] <= 16:
                                                row_class = '-1'
                                            else:
                                                row_class = '+1'
                                        else:
                                            if row['Reach'] <= 142:
                                                row_class = '-1'
                                            else:
                                                row_class = '-1'
                                    else:
                                        if row['Ht'] <= 144:
                                            row_class = '+1'
                                        else:
                                            if row['TailLn'] <= 6:
                                                row_class = '+1'
                                            else:
                                                row_class = '-1'
                    else:
                        row_class = '-1'
                else:
                    if row['Ht'] <= 176:
                        if row['HairLn'] <= 12:
                            if row['Age'] <= 42:
                                row_class = '+1'
                            else:
                                if row['Reach'] <= 134:
                                    row_class = '-1'
                                else:
                                    if row['BangLn'] <= 2:
                                        row_class = '-1'
                                    else:
                                        if row['Reach'] <= 174:
                                            if row['Age'] <= 56:
                                                row_class = '+1'
                                            else:
                                                row_class = '+1'
                                        else:
                                            row_class = '-1'
                        else:
                            row_class = '+1'
                    else:
                        row_class = '-1'
            else:
                if row['Ht'] <= 172:
                    if row['Reach'] <= 128:
                        if row['Age'] <= 38:
                            if row['Ht'] <= 120:
                                if row['Reach'] <= 110:
                                    row_class = '-1'
                                else:
                                    if row['Age'] <= 34:
                                        row_class = '+1'
                                    else:
                                        row_class = '+1'
                            else:
                                row_class = '-1'
                        else:
                            if row['Age'] <= 50:
                                if row['Reach'] <= 122:
                                    row_class = '-1'
                                else:
                                    row_class = '+1'
                            else:
                                row_class = '-1'
                    else:
                        if row['HairLn'] <= 10:
                            if row['Age'] <= 56:
                                if row['BangLn'] <= 6:
                                    if row['TailLn'] <= 12:
                                        if row['Reach'] <= 132:
                                            if row['Ht'] <= 124:
                                                row_class = '+1'
                                            else:
                                                row_class = '-1'
                                        else:
                                            if row['Ht'] <= 168:
                                                row_class = '+1'
                                            else:
                                                row_class = '-1'
                                    else:
                                        if row['Reach'] <= 148:
                                            if row['Ht'] <= 136:
                                                row_class = '+1'
                                            else:
                                                row_class = '+1'
                                        else:
                                            if row['Ht'] <= 164:
                                                row_class = '+1'
                                            else:
                                                row_class = '-1'
                                else:
                                    row_class = '+1'
                            else:
                                if row['Reach'] <= 144:
                                    if row['BangLn'] <= 6:
                                        if row['Ht'] <= 124:
                                            row_class = '-1'
                                        else:
                                            if row['Reach'] <= 138:
                                                row_class = '-1'
                                            else:
                                                row_class = '-1'
                                    else:
                                        row_class = '+1'
                                else:
                                    if row['Age'] <= 64:
                                        if row['Ht'] <= 140:
                                            row_class = '+1'
                                        else:
                                            if row['Reach'] <= 148:
                                                row_class = '-1'
                                            else:
                                                row_class = '+1'
                                    else:
                                        if row['TailLn'] <= 6:
                                            row_class = '+1'
                                        else:
                                            if row['Ht'] <= 164:
                                                row_class = '+1'
                                            else:
                                                row_class = '-1'
                        else:
                            row_class = '+1'
                else:
                    if row['HairLn'] <= 10:
                        row_class = '-1'
                    else:
                        row_class = '+1'
        all_results.append(str(row_class))
    write_to_file(all_results)