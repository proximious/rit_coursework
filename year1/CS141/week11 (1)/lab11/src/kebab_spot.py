"""
A dataclass that represents "spots" on the skewer and functions that work
with it.

author: RITCS
author: Alex Iacob
"""

from dataclasses import dataclass
from typing import Union
from food import Food


@dataclass
class KebabSpot:
    """
    Class: KebabSpot
    Description: This class is used to represent an individual
        spot on the skewer.  Each spot contains a Food 'item',
        and a reference to the 'next' spot.
    """
    item: Food
    next: Union[None, 'KebabSpot']


def spot_create(item, next):
    """
    Create a new food item spot on the skewer
    :param item (Food): new food item
    :param next: next spot
    :return: new spot
    """
    return KebabSpot(item, next)


def spot_name(spot):
    """
    Return the name of the food item in this spot.
    :param: spot (KebabSpot): the current spot on the skewer
    :return: food name
    """
    food_name = spot.item.name
    return food_name


def spot_size(spot):
    """
    Return the number of elements from this KebabSpot instance to the end
    of the skewer.
    :param: spot (KebabSpot): the current spot on the skewer
    :return: the number of elements (int)
    """
    size = 0
    while spot is not None:
        size += 1
        spot = spot.next
    return size


def spot_has(spot, name):
    """
    Return whether there are is a food item from this spot to the end of
    the skewer.
    :param: spot (KebabSpot): the current spot on the skewer
    :param name: the name (string) being searched for.
    :return True if any of the spots hold a Food item that equals the
    name, False otherwise.
"""
    if spot is None:
        return False
    elif spot.item.name == name:
        return True
    else:
        return spot_has(spot.next, name)


def spot_string_em(spot):
    """
    Return a string that contains the list of items in the skewer from
    this spot down, with a comma after each entry.
    :param: spot (KebabSpot): the current spot on the skewer
    :return A string containing the names of each of the Food items from
    this spot down.
    """
    kebabs = ''
    while spot is not None:
        if spot.next is None:
            kebabs += spot.item.name
        else:
            kebabs += spot.item.name + ', '
        spot = spot.next
    return kebabs


def spot_calories(spot):
    """
    return the total calories of the skewer
    :param spot:
    :return: total calories
    """
    total_calories = 0
    node = spot
    while node is not None:
        total_calories += node.item.calories
        node = node.next
    return total_calories


def spot_vegan(spot):
    """
    checks whether the skewer is vegan or not by checking if each element is in the veggie dictionary
    :param spot:
    :return: boolean
    """
    node = spot
    while node is not None:
        if node.item.veggie is False:
            return False
        else:
            node = node.next

    return True
