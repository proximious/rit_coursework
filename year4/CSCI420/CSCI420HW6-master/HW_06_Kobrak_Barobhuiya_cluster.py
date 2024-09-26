"""
A class to represent a cluster
"""
class Cluster:

    """
    The Cluster constructor
    @param members: a list of dicts that represent a member of this cluster
    @param members[0]['id']: the id attribute of this member
    @param members[0]['elements']: a list of all non-id attributes of this member
    @param label: the label assigned to this cluster
    """
    def __init__(self, members, label):
        self.members = members
        self.center = self.calculateCenter()
        self.label = label

    """
    Calculate the centroid of this cluster based on its members
    @return: the centroid vector of this cluster
    """
    def calculateCenter(self):
        centroid = [0 for _ in range(0, len(self.members[0]['elements']))]
        for member in self.members:
            for idx in range(0, len(member['elements'])):
                centroid[idx] += member['elements'][idx]
        centroid = list(map(lambda element: element/len(self.members), centroid))
        return centroid

    """
    Append the members of another cluster into this one, and recalculate this
    cluster's label and centroid

    @param newCluster: a Cluster object
    """
    def mergeCluster(self, newCluster):
        self.members = self.members + newCluster.members
        self.label = min(self.label, newCluster.label)
        self.center = self.calculateCenter()