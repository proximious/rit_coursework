"""
CSCI-141 Computer Science 1 Presentation Code
13-Trees
Binary Trees

Example code with a general binary tree using recursive functions for
traversals, size and height.
"""

from week13.recitation.btnode import *


def preorder(node):
    """
    The preorder traversal order is: parent, left, right.
    :param node: current node
    """
    if node != None:
        print(node.value, end=' ')
        preorder(node.left)
        preorder(node.right)


def inorder(node):
    """
    The inorder traversal order is: left, parent, right.
    :param node: current node
    """
    if node != None:
        inorder(node.left)
        print(node.value, end=' ')
        inorder(node.right)


def postorder(node):
    """
    The postorder traversal order is: left, right, parent.
    :param node: current node
    """
    if node != None:
        postorder(node.left)
        postorder(node.right)
        print(node.value, end=' ')


def test_traverse(tree, msg):
    """
    Test the three traversals of a tree
    :param tree: the root BTNode of the tree
    :param msg: helper message
    """
    print('traversing', msg, '->', tree)

    print('preorder: ', end='')
    preorder(tree)
    print()

    print('inorder: ', end='')
    inorder(tree)
    print()

    print('postorder: ', end='')
    postorder(tree)
    print()
    print()


def size(node):
    """
    Get the number of elements in the tree.
    :param node: the current node in the tree (BTNode)
    :return: the size of the tree at this node
    """
    if node == None:
        return 0
    else:
        return 1 + size(node.left) + size(node.right)


def test_size(tree, msg, expected_size):
    """
    Test routine for tree size.
    :param tree: the root BTNode of tree
    :param msg: a helper message
    :param expected_size: the expected size of the tree
    """
    print('size', msg, '=>', size(tree), '==', expected_size, '?', size(tree) == expected_size)


def height(node):
    """
    Get the height of a tree.  The height of an empty tree is -1.  The height of a leaf
    node is 0.  Otherwise the height of a tree is the larger of the heights of the left
    and right branches.
    :param node: current node (BTNode) in the tree
    :return: the height of the tree at this node
    """
    if node == None:
        return -1
    else:
        return 1 + max(height(node.left), height(node.right))


def test_height(tree, msg, expected_height):
    """
    Test routine for tree height.
    :param tree: the root BTNode of tree
    :param msg: a helper message
    :param expected_height: the expected height of the tree
    :return:
    """
    print('height', msg, '=>', height(tree), '==', expected_height, '?', height(tree) == expected_height)


def test_binary_tree():
    """
    The main routine creates three different binary trees and tests them for
    traversing, size and height.
    """
    # test traversals
    tree_A = mk_btnode_leaf('A')
    test_traverse(tree_A, "tree_A")

    tree_B = mk_btnode(10, mk_btnode_leaf(20), mk_btnode_leaf(30))
    test_traverse(tree_B, "tree_B")

    tree_C = mk_btnode('A',
                       mk_btnode('B',
                                 None,
                                 mk_btnode_leaf('D')),
                       mk_btnode('C',
                                 mk_btnode('E',
                                           mk_btnode_leaf('G'),
                                           None),
                                 mk_btnode('F',
                                           mk_btnode_leaf('H'),
                                           mk_btnode_leaf('I'))))
    test_traverse(tree_C, "tree_C")

    # test size
    test_size(tree_A, 'tree_A', 1)
    test_size(tree_B, 'tree_B', 3)
    test_size(tree_C, 'tree_C', 9)
    print()

    # test height
    test_height(tree_C, 'tree_C', 3)
    test_height(tree_C.left, 'tree_C left', 1)
    test_height(tree_C.right, 'tree_C right', 2)


if __name__ == '__main__':
    test_binary_tree()

"""
$ python3 binary_tree.py
traversing tree_A -> BTNode(value='A', left=None, right=None)
preorder: A 
inorder: A 
postorder: A 

traversing tree_B -> BTNode(value=10, left=BTNode(value=20, left=None, right=None), right=BTNode(value=30, left=None, right=None))
preorder: 10 20 30 
inorder: 20 10 30 
postorder: 20 30 10 

traversing tree_C -> BTNode(value='A', left=BTNode(value='B', left=None, right=BTNode(value='D', left=None, right=None)), right=BTNode(value='C', left=BTNode(value='E', left=BTNode(value='G', left=None, right=None), right=None), right=BTNode(value='F', left=BTNode(value='H', left=None, right=None), right=BTNode(value='I', left=None, right=None))))
preorder: A B D C E G F H I 
inorder: B D A G E C H F I 
postorder: D B G E H I F C A 

size tree_A => 1 == 1 ? True
size tree_B => 3 == 3 ? True
size tree_C => 9 == 9 ? True

height tree_C => 3 == 3 ? True
height tree_C left => 1 == 1 ? True
height tree_C right => 2 == 2 ? True
"""
