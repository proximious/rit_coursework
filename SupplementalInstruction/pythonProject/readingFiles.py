def msr(count, num):
    if count < 1:
        return 1
    else:
        return (count*num) * msr(count - 1, num)

if __name__ == '__main__':
    # fd = open ('stuff.txt')
    # for line in fd:
    #     print(line.strip())
    # fd.close()
    print(msr(4, 2))
