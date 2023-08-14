class bst_node:
    def __init__(self,key):
        self.key = key
        self.left = None
        self.right = None
    def get_key(self):
        return self.key
    def set_left(self,leftNode):
        self.left = leftNode
    def set_right(self,rightNode):
        self.right = rightNode
    def get_left(self):
        return self.left
    def get_right(self):
        return self.right

def inorder(start_node,inord):
    current = start_node
    stack = []
    while True:
        if current is not None:
            stack.append(current)
            current = current.left
        elif stack:
            current = stack.pop()
            inord.append(current.key)
            current = current.right
        else:
            break

if __name__ == '__main__':
    n = int(input())
    if n <= 1:
        print('CORRECT')
        exit()
    node_list = []
    l = []
    r = []
    for i in range(n):
        k, ll, rr = [int(x) for x in input().split()]
        tempnode = bst_node(k)
        node_list.append(tempnode)
        l.append(ll)
        r.append(rr)
    for j in range(n):
        if l[j] != -1:
            node_list[j].set_left(node_list[l[j]])
        if r[j] != -1:
            node_list[j].set_right(node_list[r[j]])

    inord = []
    inorder(node_list[0], inord)
    isitinorder = True
    if n == 1:
        print('CORRECT')
    else:
        for k in range(n-1):
            if inord[k] > inord[k+1]:
                print('INCORRECT')
                isitinorder = False
                break
        if isitinorder:
            print('CORRECT')
    # print(' '.join([str(inord[k]) for k in range(len(inord))]))