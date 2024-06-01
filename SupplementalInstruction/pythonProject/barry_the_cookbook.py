"""
SI Mock session

"""

from dataclasses import dataclass


@dataclass(frozen=True)
class Recipe:
    name: str
    ingredients: list
    servings: int
    steps: list
    cook_time: int


def add_recipes_to_barry():
    r1 = Recipe('PBJ', ['peanut butter', 'jelly'], 5,
                ['freeze peanut butter in sheets', 'put jelly between slabs of peanut butter'], 300)
    r2 = Recipe('cookies', ['frozen Pillsbury cookies'], 18,
                ['grease baking sheet', 'put frozen cookies on sheet', 'put in oven'], 10)
    r3 = Recipe('gamer girl juice', ['pink starburst juice', 'pink lemonade vodka', 'ultra rosa monster'], 1,
                ['shake juice and vodka', 'combine with monster'], 3)
    r4 = Recipe('turducken', ['turkey', 'duck', 'chicken'], 10,
                ['put chicken in duck', 'put chicken duck thing in turkey', 'put in oven', 'cry'], 180)
    r5 = Recipe('coffee', ['water', 'coffee grounds', 'milk', 'sugar'], 1,
                ['put water and coffee in coffee machine', 'wait for coffee to brew',
                 'top with milk and sugar to taste'], 5)
    r6 = Recipe('water', ['two hydrogen', 'oxygen'], 1,
                ['achieve catalyst to bond hydrogen atoms to oxygen atom', 'get good'], 9)
    r7 = Recipe('gamer girl juice', ['pink starburst juice', 'pink lemonade vodka', 'ultra rosa monster'], 1,
                ['shake juice and vodka', 'combine with monster'], 3)

    barry = dict()
    data = [r1, r2, r3, r4, r5, r6, r7]

    for item in data:
        barry[item.name] = item

    print(len(barry.keys()))


if __name__ == '__main__':
    add_recipes_to_barry()
