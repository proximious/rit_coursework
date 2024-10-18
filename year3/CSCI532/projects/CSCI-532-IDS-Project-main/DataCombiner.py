import os

NORMAL_FILE = "Optimized_Normal"


class DataCombiner:
    # Convert files into a dictionary where each value is a list of all of the file lines.
    # This allows for easy separation and sorting later on.
    file_dict = {}
    file_sizes = {}

    def __init__(self, path):
        for file_name in os.listdir(path):
            file_contents = []
            file_path = os.path.join(path, file_name)
            og_file = open(file_path, 'r')
            for line in og_file:
                stripped_line = line.strip()
                if stripped_line == '':
                    continue
                else:
                    file_contents.append([float(x) for x in stripped_line.split(',')])
            self.file_dict[file_name] = file_contents
            self.file_sizes[file_name] = os.path.getsize(file_path)
            og_file.close()

    def get_separate_data(self, file_list):
        """
        Run this to get data from specified file names.
        :param file_list: A list containing the names of files to separate out.
        :return: Returns a dictionary of data classified by file name from the file_list and "other".
        """
        sorted_data = {}
        other = []
        for file_name in self.file_sizes.keys():
            if file_name in file_list:
                sorted_data[file_name] = self.file_dict[file_name]
            else:
                other = other + self.file_dict[file_name]
        sorted_data["other"] = other
        return sorted_data

class DataCombiner2:
    """
    Python was never creating a second DataCombiner, resulting in the same object being used and reinitialized.
    So... here's the super hacky way to get around that.
    """
    # Convert files into a dictionary where each value is a list of all of the file lines.
    # This allows for easy separation and sorting later on.
    file_dict = {}
    file_sizes = {}

    def __init__(self, path):
        for file_name in os.listdir(path):
            file_contents = []
            file_path = os.path.join(path, file_name)
            og_file = open(file_path, 'r')
            for line in og_file:
                stripped_line = line.strip()
                if stripped_line == '':
                    continue
                else:
                    file_contents.append([float(x) for x in stripped_line.split(',')])
            self.file_dict[file_name] = file_contents
            self.file_sizes[file_name] = os.path.getsize(file_path)
            og_file.close()

    def get_separate_data(self, file_list):
        """
        Run this to get data from specified file names.
        :param file_list: A list containing the names of files to separate out.
        :return: Returns a dictionary of data classified by file name from the file_list and "other".
        """
        sorted_data = {}
        other = []
        for file_name in self.file_sizes.keys():
            if file_name in file_list:
                sorted_data[file_name] = self.file_dict[file_name]
            else:
                other = other + self.file_dict[file_name]
        sorted_data["other"] = other
        return sorted_data
