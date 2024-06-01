def populations(pop, gen):
    if gen == 0:
        return 0
    else:
        return pop + populations(pop, gen - 1) - 2


if __name__ == '__main__':
    print(populations(5, 3))
