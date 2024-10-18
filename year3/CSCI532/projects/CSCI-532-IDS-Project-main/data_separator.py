import os

OG_PATH = "original_dataset"
TRAIN_PATH = "training_sets"
TEST_PATH = "testing_sets"


def create_empty_dir(path):
    if not os.path.exists(path):
        os.mkdir(path)


def main():
    """
    Input the names of files. Reads in the data from the original dataset and split it into a training set and a
    testing set, at roughly a 3:1 packet ratio.
    """

    create_empty_dir(TRAIN_PATH)
    create_empty_dir(TEST_PATH)

    # WARNING: REMOVES ALL FILES IN TEST AND TRAINING PATHS!
    print("Clearing " + TRAIN_PATH + " and " + TEST_PATH + " directories...")
    for file_name in os.listdir(TRAIN_PATH):
        path = os.path.join(TRAIN_PATH, file_name)
        os.remove(path)
    for file_name in os.listdir(TEST_PATH):
        path = os.path.join(TEST_PATH, file_name)
        os.remove(path)

    for file_name in os.listdir(OG_PATH):
        path = os.path.join(OG_PATH, file_name)
        og_file = open(path, 'r')

        print("Separating file \'" + path + "\'...")

        path = os.path.join(TRAIN_PATH, file_name)
        train_file = open(path, 'w')

        path = os.path.join(TEST_PATH, file_name)
        test_file = open(path, 'w')

        line_count = 0
        for line in og_file:
            line_count += 1
            if line.strip() == '':
                continue
            elif (line_count % 4 == 1):
                test_file.write(line)
            else:
                train_file.write(line)
        og_file.close()
        train_file.close()
        test_file.close()

    print("All files separated!")



if __name__ == "__main__":
    main()
