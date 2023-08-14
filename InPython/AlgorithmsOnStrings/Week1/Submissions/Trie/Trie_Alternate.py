# Python3
def build_trie(patterns):
    tree = {}
    # write your code here

    new_node = 0
    for pattern in patterns:
        cur_node = 0
        for i in range(len(pattern)):
            ch = pattern[i]
            if (cur_node in tree):
                if (ch in tree[cur_node]):
                    cur_node = tree[cur_node].get(ch)
                else:
                    new_node = new_node + 1
                    if not (cur_node in tree):
                        tree[cur_node] = {}
                    tree[cur_node][ch] = new_node
                    cur_node = new_node
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