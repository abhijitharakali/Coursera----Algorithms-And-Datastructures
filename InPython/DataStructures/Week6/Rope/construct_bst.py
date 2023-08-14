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

def log2_ceil(n):
    k = 0
    # s = 0
    if n == 1:
        k = 1
    else:
        m = n
        while m > 0:
            # if m == 1 and s == 0:
            #     break
            # s = s + (m % 2)
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

if __name__ == '__main__':
    instring = input()
    n = len(instring)
    if n < 1:
        exit()
    lst = []
    if n > 3:
        k = log2_ceil(n)
    else:
        k = n
    for i in range((2 ** k) - 1):
        tempnode = Vertex(i,0,None,None,None)
        lst.append(tempnode)
    if n == 1:
        root_indx = 0
        lst[0].size = 1
        k = 1
    elif n == 2:
        root_indx = 1
        lst[0].size = 1
        lst[1].size = 2
        lst[0].parent = lst[1]
        lst[1].left = lst[0]
    elif n == 3:
        root_indx = 1
        lst[0].size = 1
        lst[2].size = 1
        lst[1].size = 3
        lst[0].parent = lst[1]
        lst[2].parent = lst[1]
        lst[1].left = lst[0]
        lst[1].right = lst[2]
    else:
        root_indx = (2**(k-1) - 1)
        j = 1
        l = 0
        while j <= k:
            l = 0
            while (2**(j+1))*l + (2**(j) - 1) < (2**(k) - 1):
                p = (2**(j+1))*l + (2**(j) - 1)
                lft = (2**(j))*(2*l) + (2**(j-1) - 1)
                r = (2**(j))*(2*l+1) + (2**(j-1) - 1)
                lst[lft].parent = lst[p]
                lst[r].parent = lst[p]
                lst[p].left = lst[lft]
                lst[p].right = lst[r]
                lst[lft].size = 1 + (lst[lft].left.size if lst[lft].left != None else 0) + (lst[lft].right.size if lst[lft].right != None else 0)
                lst[r].size = 1 + (lst[r].left.size if lst[r].left != None else 0) + (lst[r].right.size if lst[r].right != None else 0)
                lst[p].size = 1 + (lst[p].left.size if lst[p].left != None else 0) + (lst[p].right.size if lst[p].right != None else 0)
                l += 1
            j += 1

    inord = []
    inorder(lst[root_indx],inord)
    print(' '.join([str(inord[k]) for k in range(len(inord))]))
    root = lst[root_indx]
    leftn = root.left
    print(leftn.parent.indx)