"""
file: mobiles.py
language: python3
author: CS.RIT.EDU
author: Alex Iacob ai9388@rit.edu
description: Build mobiles using a tree data structure.
date: 10/2015, 11/2019
purpose: starter code for the tree mobiles lab
"""

############################################################
#                                                          #
#    IMPLEMENT THE STRUCTURE DEFINITIONS PER REQUIREMENTS, # 
#    AND                                                   #
#    IMPLEMENT THE MOBILE CREATION AND ANALYSIS FUNCTIONS. #
#        See the 'define structure here' text below,       #
#        the 'Create mobiles from mobile files' text,      #
#        and the heading 'mobile analysis functions'.      #
#                                                          #
#    (See also the 'pass' statements to replace.)          #
#                                                          #
############################################################

from dataclasses import dataclass
from typing import Union


############################################################
# structure definitions
############################################################

@dataclass
class Ball:
    """
    class Ball represents a ball of some weight hanging from a cord.
    field description:
    cord: length of the hanging cord in inches
    weight: weight of the ball in ounces (diameter of ball in a drawing)
    """
    name: str
    cord: float
    weight: float


@dataclass
class Rod:
    """
    class Rod represents a horizontal rod part of a mobile with
    a left-side mobile on the end of a left arm of some length,
    and a right-side mobile on the end of a right arm of some length.
    In the middle between the two arms is a cord of some length
    from which the rod instance hangs.
    field description:
    leftmobile: subordinate mobile is a mobile type.
    left_arm_length: length of the right arm in inches
    cord: length of the hanging cord in inches
    right_arm_length: length of the right arm in inches
    right_mobile: subordinate mobile is a mobile type.

    An assembled mobile has valid left and right subordinate mobiles;
    an unassembled mobile does not have valid subordinate mobiles.
    """
    name: str
    leftmobile: Union['Rod', Ball, str]
    leftarm: float
    cord: float
    rightarm: float
    rightmobile: Union['Rod', Ball, str]


#########################################################
# Create mobiles from mobile files
#########################################################

def read_mobile(file):
    """
    read_mobile : OpenFileObject -> Dictionary( Ball | Rod )
    read_mobile reads the open file's content and
    builds a mobile 'parts dictionary' from the specification in the file.
    The parts dictionary returned has components for assembling the mobile.
    If the mobile is a simple mobile, the returned value is
    a parts dictionary containing a Ball instance.
    If the mobile is complex, the returned value is a parts list of
    the Rod instance representing the top-most mobile component and
    the other parts.
    The connection point for each part is a string that identifies
    the key name of the part to be attached at that point.

    If there is an error in the mobile specification, then
    return an empty parts dictionary.

    # an example of the file format. 'B10' is key for the 10 oz ball.
    # blank lines and '#' comment lines are permitted.
    B10 40 10

    top B10 240 66 80 B30
    B30 55 30
    """
    mobile_dict = {}
    for line in file:
        mob_parts = line.strip().split()
        if '#' in mob_parts:
            print(line, end='')
        elif not mob_parts:
            pass
        elif len(mob_parts) == 3:
            mobile_part = Ball(mob_parts[0], float(mob_parts[1]), float(mob_parts[2]))
            mobile_dict[mob_parts[0]] = mobile_part
        else:
            mobile_part = Rod(mob_parts[0], mob_parts[1], float(mob_parts[2]), float(mob_parts[3]),
                              float(mob_parts[4]), mob_parts[5])
            mobile_dict[mob_parts[0]] = mobile_part

    if 'top' in mobile_dict:
        return mobile_dict
    else:
        return dict()


def construct_mobile(parts):
    """
    construct_mobile : Dictionary( Rod | Ball ) -> Ball | Rod | NoneType

    construct_mobile reads the parts to put together the
    mobile's components and return a completed mobile object.
    The construct_mobile operation 'patches entries' in the parts.

    The parts dictionary has the components for assembling the mobile.
    Each Rod in parts has a key name of its left and right
    subordinate mobiles.  construct_mobile reads the key to
    get the subordinate part and attach it at the slot where
    the key was located within the component.

    The top mounting point of the mobile has key 'top' in parts.

    If the completed mobile object is a simple mobile, then
    the top returned value is a Ball instance.
    If the completed mobile is a complex mobile, then
    the top returned value is a Rod instance.

    If the parts dictionary contains no recognizable mobile specification,
    or there is an error in the mobile specification, then 
    return None.
    """

    for key in parts:
        mobile_part = parts[key]
        if isinstance(mobile_part, Rod):
            mobile_part.leftmobile = parts[mobile_part.leftmobile]
            mobile_part.rightmobile = parts[mobile_part.rightmobile]

    return parts['top']


############################################################
# mobile analysis functions
############################################################

def is_balanced(the_mobile):
    """
    is_balanced : Mobile -> Boolean

    is_balanced is trivially True if the_mobile is a simple ball. 

    Otherwise the_mobile is balanced if the product of the left side
    arm length and the left side is approximately equal to the 
    product of the right side arm length and the right side, AND
    both the right and left subordinate mobiles are also balanced.

    The approximation of balance is measured by checking
    that the absolute value of the difference between
    the two products is less than 1.0.

    If the_mobile is not valid, then produce an exception
    with the message 'Error: Not a valid mobile\n\t{mobile}',

    pre-conditions: the_mobile is a proper mobile instance.
    """

    if isinstance(the_mobile, Ball):
        return True
    else:
        product_left = float(the_mobile.leftarm) * float(weight(the_mobile.leftmobile))
        product_right = float(the_mobile.rightarm) * float(weight(the_mobile.leftmobile))

        if product_left - product_right <= 1 or product_right - product_left <= 1:
            return True
        else:
            return False


def weight(the_mobile):
    """
    weight : Mobile -> Number
    weight of the the_mobile is the total weight of all its Balls.

    If the_mobile is not valid, then produce an exception
    with the message 'Error: Not a valid mobile\n\t{mobile}',

    pre-conditions: the_mobile is a proper mobile instance.
    """
    total_weight = 0
    if the_mobile is False:
        raise Exception("Error: Not a valid mobile\n\t" + str(the_mobile))
    else:
        if isinstance(the_mobile, Ball):
            total_weight += float(the_mobile.weight)
        else:
            return total_weight + float(weight(the_mobile.leftmobile)) + \
                   float(weight(the_mobile.rightmobile))
    return total_weight


def height(the_mobile):
    """
    height : mobile -> Number
    height of the the_mobile is the height of all tallest side.

    If the_mobile is not valid, then produce an exception
    with the message 'Error: Not a valid mobile\n\t{mobile}',

    pre-conditions: the_mobile is a proper mobile instance.
    """
    if the_mobile is False:
        raise Exception("Error: Not a valid mobile\n\t" + str(the_mobile))
    else:
        if isinstance(the_mobile, Ball):
            return the_mobile.weight + the_mobile.cord
        else:
            return the_mobile.cord + max(height(the_mobile.leftmobile),
                                         height(the_mobile.rightmobile))
