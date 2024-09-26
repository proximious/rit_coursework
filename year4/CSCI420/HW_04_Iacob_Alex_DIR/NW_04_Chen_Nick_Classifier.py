def main():
    # location of file (eg. "./Validation_33.csv")
    input_file = input("Input validation file: ")
    classifier_output = open("NW_04_Chen_Nick_RESULTS.csv", "w")
    classifier_output.write("PulledOver")
    # open input file, skip line 1
    with open(input_file) as f:
        next(f)
        # reads each line of drivers, determines pulled over value based on threshold and attribute 
        for line in f:
            fields = line.split(",")
            precip = int(fields[2])
            if precip > 3:
                classifier_output.write("\nn")
            else:
                classifier_output.write("\ny")


if __name__ == "__main__":
    main()
