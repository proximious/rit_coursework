"""
Alex Iacob
ai9388@rit.edu
Lab 8: DNA
"""

from linked_code import LinkNode
import linked_code


# 1
def convert_to_nodes(dna_string):
    """
    takes a string and returns a linked list
    :param dna_string:
    :return: linked list
    """
    if len(dna_string) <= 0:
        return None
    else:
        return LinkNode(dna_string[0], convert_to_nodes(dna_string[1:]))


# 2
def convert_to_string(dna_seq):
    """
    takes a linked list and turns it into a string
    :param dna_seq:
    :return: string
    """
    if dna_seq is None:
        return ''
    else:
        dna_string = str(dna_seq.value)
        dna_string += (convert_to_string(dna_seq.rest))

    return dna_string


# 3
def is_match(dna_seq1, dna_seq2):
    """
    tests whether two dna sequences are the same or not
    :param dna_seq1:
    :param dna_seq2:
    :return: Boolean
    """
    if dna_seq1 is None and dna_seq2 is None:
        return True
    elif dna_seq1 is None or dna_seq2 is None:
        return False
    elif dna_seq1.value == dna_seq2.value:
        return is_match(dna_seq1.rest, dna_seq2.rest)
    else:
        return False


# 4
def is_pairing(dna_seq1, dna_seq2):
    """
    tests whether each individual pairings are valid or not
    :param dna_seq1:
    :param dna_seq2:
    :return: Boolean
    """
    if dna_seq1 is None and dna_seq2 is None:
        return True
    elif dna_seq1 is None or dna_seq2 is None:
        return False
    elif dna_seq1.value == 'A':
        if dna_seq2.value == 'T':
            return is_pairing(dna_seq1.rest, dna_seq2.rest)
        else:
            return False
    elif dna_seq1.value == 'T':
        if dna_seq2.value == 'A':
            return is_pairing(dna_seq1.rest, dna_seq2.rest)
        else:
            return False
    elif dna_seq1.value == 'C':
        if dna_seq2.value == 'G':
            return is_pairing(dna_seq1.rest, dna_seq2.rest)
        else:
            return False
    elif dna_seq1.value == 'G':
        if dna_seq2.value == 'C':
            return is_pairing(dna_seq1.rest, dna_seq2.rest)
        else:
            return False
    elif dna_seq1.value == dna_seq2.value:
        return is_pairing(dna_seq1.rest, dna_seq2.rest)
    else:
        return False


# 5
def is_palindrome(dna_seq):
    """
    tests whether the dna sequence is a palindrome or not
    :param dna_seq:
    :return: Boolean
    """
    reversed_seq = linked_code.reverse_iter(dna_seq)
    if is_match(dna_seq, reversed_seq) is True:
        return True
    else:
        return False


# 6
def substitution(dna_seq1, idx, base):
    if idx < 0:
        raise IndexError('Index out of range')
    else:
        removed_seq = linked_code.remove_at(idx, dna_seq1)
        return linked_code.insert_at(idx, base, removed_seq)


# 7
def insertion(dna_seq1, dna_seq2, idx):
    """
    inserts dna_seq2 into a certain index in dna_seq1
    :param dna_seq1:
    :param dna_seq2:
    :param idx:
    :return: a combined dna sequence
    """
    if idx <= 0:
        return linked_code.concatenate(dna_seq2, dna_seq1)
    elif dna_seq1 is None:
        raise IndexError("Invalid insertion index")
    else:
        return LinkNode(dna_seq1.value, insertion(dna_seq1.rest, dna_seq2, idx - 1))


# 8
def deletion(dna_seq, idx, segment_size):
    """
    delete a certain amount of values from a dna sequence
    :param dna_seq:
    :param idx:
    :param segment_size:
    :return: cropped linked sequence
    """
    if segment_size == 0:
        return dna_seq
    elif idx + segment_size > linked_code.length_rec(dna_seq):
        raise IndexError('Index out of range')
    else:
        removed_seq = dna_seq
        while segment_size > 0:
            removed_seq = linked_code.remove_at(idx, removed_seq)
            segment_size -= 1

        return removed_seq


# 9
def duplication(dna_seq, idx, segment_size):
    """
    duplicates a certain segment from the original sequence into an index
    :param dna_seq:
    :param idx:
    :param segment_size:
    :return: the duplicated sequence
    """
    if segment_size == 0:
        return dna_seq
    elif idx + segment_size > linked_code.length_rec(dna_seq):
        raise IndexError('Index out of range')
    else:
        duplicated_seq = dna_seq
        while segment_size > 0:
            duplicated_seq = linked_code.insert_at(idx, linked_code.value_at(dna_seq, idx), duplicated_seq)
            segment_size -= 1
            idx += 1

        return duplicated_seq
