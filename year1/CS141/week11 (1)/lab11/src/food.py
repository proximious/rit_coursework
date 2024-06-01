"""
A module that represents the valid food types.

author: RITCS
author: Alex Iacob
"""
from dataclasses import dataclass

# The set of valid food items
FOODS = {'beef', 'pork', 'chicken', 'onion', 'pepper', 'tomato', 'mushroom'}

# The set of vegetables
VEGGIES = {'onion', "pepper", 'tomato', 'mushroom'}

# The calories for each food item (a dictionary, where 
# key = food name (string) and value = calories (int)
CALORIES = {
    'beef': 200,
    'chicken': 140,
    'pork': 100,
    'onion': 30,
    'pepper': 25,
    'tomato': 10,
    'mushroom': 7
}


@dataclass(frozen=True)
class Food:
    name: str
    veggie: bool
    calories: int


def food_create(name):
    """
    Create a new food item.
    :param name: the name of the food
    :return: new Food object
    """
    food_item = name
    if food_item in FOODS:
        if food_item in VEGGIES:
            return Food(food_item, True, CALORIES[food_item])
        else:
            return Food(food_item, False, CALORIES[food_item])
