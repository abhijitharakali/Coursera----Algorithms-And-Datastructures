# python3

from sys import stdin

class Vertex:
    def __init__(self, indx, size, left, right, parent):
        (self.indx, self.size, self.left, self.right, self.parent) = (indx, size, left, right, parent)

def update(v):
    if v == None:
        return
    v.size = 1 + (v.left.size if v.left != None else 0) + (v.right.size if v.right != None else 0)
    if v.left != None:
        v.left.parent = v
    if v.right != None:
        v.right.parent = v

def smallRotation(v):
    parent = v.parent
    if parent == None:
        return
    grandparent = v.parent.parent
    if parent.left == v:
        m = v.right
        v.right = parent
        parent.left = m
    else:
        m = v.left
        v.left = parent
        parent.right = m
    update(parent)
    update(v)
    v.parent = grandparent
    if grandparent != None:
        if grandparent.left == parent:
            grandparent.left = v
        else:
            grandparent.right = v


def bigRotation(v):
    if v.parent.left == v and v.parent.parent.left == v.parent:
        # Zig-zig
        smallRotation(v.parent)
        smallRotation(v)
    elif v.parent.right == v and v.parent.parent.right == v.parent:
        # Zig-zig
        smallRotation(v.parent)
        smallRotation(v)
    else:
        # Zig-zag
        smallRotation(v)
        smallRotation(v)


# Makes splay of the given vertex and makes
# it the new root.
def splay(v):
    if v == None:
        return None
    while v.parent != None:
        if v.parent.parent == None:
            smallRotation(v)
            break
        bigRotation(v)
    return v

def find(root,key):
    k = key
    assert 0<= key < root.size
    node = root
    while node:
        left,right = node.left,node.right
        s = left.size if left else 0
        if k == s:
            break
        elif k< s:
            if left:
                node = left
                continue
            break
        else:
            if right:
                k = k-s-1
                node = right
                continue
            break
    splay(node)
    return node

def split(root, key):
    root1 = find(root,key)
    root2 = root1.right
    root1.right = None
    update(root1)
    if root2:
        root2.parent = None
        update(root2)
    return root1,root2

def merge(left, right):
    if left == None:
        return right
    if right == None:
        return left
    while right.left != None:
        right = right.left
    right = splay(right)
    right.left = left
    update(right)
    return right

def log2_ceil(n):
    k = 0
    if n == 1:
        k = 1
    else:
        m = n
        while m > 0:
            m = m//2
            k += 1
    return k

def inorder(start_node,inord):
    current = start_node
    stack = []
    while True:
        if current is not None:
            stack.append(current)
            current = current.left
        elif stack:
            current = stack.pop()
            inord.append(current.indx)
            current = current.right
        else:
            break

def construc_bst(n):
    lst = []
    k = log2_ceil(n)
    if n == 1:
        tempnode = Vertex(0, 0, None, None, None)
        lst.append(tempnode)
    else:
        for i in range((2 ** k) - 1):
            tempnode = Vertex(i, 0, None, None, None)
            lst.append(tempnode)

    if n == 1:
        lst[0].size = 1
        k = 1
    elif n == 2:
        lst[0].size = 1
        lst[1].size = 2
        lst[0].parent = lst[1]
        lst[1].left = lst[0]
    elif n == 3:
        lst[0].size = 1
        lst[2].size = 1
        lst[1].size = 3
        lst[0].parent = lst[1]
        lst[2].parent = lst[1]
        lst[1].left = lst[0]
        lst[1].right = lst[2]
    else:
        j = 1
        l = 0
        while j <= k:
            l = 0
            while (2 ** (j + 1)) * l + (2 ** (j) - 1) < (2 ** (k) - 1):
                p = (2 ** (j + 1)) * l + (2 ** (j) - 1)
                lft = (2 ** (j)) * (2 * l) + (2 ** (j - 1) - 1)
                r = (2 ** (j)) * (2 * l + 1) + (2 ** (j - 1) - 1)
                lst[lft].parent = lst[p]
                lst[r].parent = lst[p]
                lst[p].left = lst[lft]
                lst[p].right = lst[r]
                lst[lft].size = 1 + (lst[lft].left.size if lst[lft].left != None else 0) + (
                    lst[lft].right.size if lst[lft].right != None else 0)
                lst[r].size = 1 + (lst[r].left.size if lst[r].left != None else 0) + (
                    lst[r].right.size if lst[r].right != None else 0)
                lst[p].size = 1 + (lst[p].left.size if lst[p].left != None else 0) + (
                    lst[p].right.size if lst[p].right != None else 0)
                l += 1
            j += 1
    return lst

def process(root,i,j,k):
    middle, right = split(root,j)
    if i > 0:
        left,middle = split(middle,i-1)
    else:
        left = None
    left = merge(left,right)
    if k > 0:
        left,right = split(left,k-1)
    else:
        right = left
        left = None
    root = merge(merge(left,middle),right)
    return root

if __name__ == '__main__':
    instring = input()
    n = len(instring)
    if n < 1:
        exit()
    if n == 1:
        root_indx = 0
    elif n == 2:
        root_indx = 1
    elif n == 3:
        root_indx = 1
    else:
        kk = log2_ceil(n)
        root_indx = (2 ** (kk - 1) - 1)
    node_list = construc_bst(n)
    root = node_list[root_indx]

    nops = int(input())
    for x in range(nops):
        proc = input().split()
        i = int(proc[0])
        j = int(proc[1])
        k = int(proc[2])
        root = process(root,i,j,k)

    inord = []
    inorder(root,inord)
    final_ans = []
    for y in range(n):
        final_ans.append(instring[inord[y]])
    print(''.join(final_ans))
    # print(' '.join([str(inord[k]) for k in range(n)]))
