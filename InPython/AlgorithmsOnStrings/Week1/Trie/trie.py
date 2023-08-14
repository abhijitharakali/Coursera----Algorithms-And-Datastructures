#Uses python3

# Return the trie built from patterns
# in the form of a dictionary of dictionaries,
# e.g. {0:{'A':1,'T':2},1:{'C':3}}
# where the key of the external dictionary is
# the node ID (integer), and the internal dictionary
# contains all the trie edges outgoing from the corresponding
# node, and the keys are the letters on those edges, and the
# values are the node IDs to which these edges lead.
def build_trie(patterns):
    tree = {}
    # write your code here

    new_node = 0
    for pattern in patterns:
        cur_node = 0
        for i in range(len(pattern)):
            ch = pattern[i]
            if (cur_node in tree) and (ch in tree[cur_node]):
                cur_node = tree[cur_node].get(ch)
            else:
                new_node = new_node + 1
                if not (cur_node in tree):
                    tree[cur_node] = {}
                tree[cur_node][ch] = new_node
                cur_node = new_node
    return tree


if __name__ == '__main__':
    n = int(input())
    patterns = list(input() for i in range(n))
    tree = build_trie(patterns)
    for node in tree:
        for c in tree[node]:
            print("{}->{}:{}".format(node, tree[node][c], c))