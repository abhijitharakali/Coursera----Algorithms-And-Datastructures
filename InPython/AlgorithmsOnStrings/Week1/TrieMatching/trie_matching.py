# Python3

def prefix_trie_matches(text,trie):
    v = 0
    for l in range(len(text)):
        ch = text[l]
        if ch in trie[v]:
            v = trie[v][ch]
            if not (v in trie):
                return True
        else:
            return False

def trie_matching(text,tree):
    posns = []
    for k in range(len(text)):
        if prefix_trie_matches(text[k:],tree):
            posns.append(k)
    return posns

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
    text = input()
    n = int(input())
    patterns = list(input() for i in range(n))
    tree = build_trie(patterns)
    posns = trie_matching(text,tree)
    for pos in posns:
        print(pos, end=' ')