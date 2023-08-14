class bst_node:
    def __init__(self,index,key):
        self.key = key
        self.left = None
        self.right = None
        self.depth = 0
        self.index = index
    def get_index(self):
        return self.index
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
    def get_depth(self):
        return self.depth
    def set_depth(self,depth):
        self.depth = depth

def inorder(start_node,inord,indx):
    current = start_node
    stack = []
    while True:
        if current is not None:
            stack.append(current)
            current = current.left
        elif stack:
            current = stack.pop()
            inord.append(current.key)
            indx.append(current.get_index())
            current = current.right
        else:
            break

if __name__ == '__main__':
    n = int(input())
    if n == 0:
        print('CORRECT')
        exit()
    node_list = []
    l = []
    r = []

    # print(str(maxi)+' '+str(mini))
    for i in range(n):
        k, ll, rr = [int(x) for x in input().split()]
        # print(k - (pow(2,31)-1) > 0)
        # print(k - (-1*pow(2,31) < 0))
        if k > (pow(2,31)-1) or k < (-1*pow(2,31)):
            print('INCORRECT')
            exit()
        tempnode = bst_node(i,k)
        node_list.append(tempnode)
        l.append(ll)
        r.append(rr)
    for j in range(n):
        d = node_list[j].get_depth()
        if l[j] != -1:
            node_list[j].set_left(node_list[l[j]])
            node_list[l[j]].set_depth(d+1)
        if r[j] != -1:
            node_list[j].set_right(node_list[r[j]])
            node_list[r[j]].set_depth(d+1)

    inord = []
    indx = []
    inorder(node_list[0], inord, indx)
    isitinorder = True
    if n == 1:
        print('CORRECT')
    else:
        for k in range(n-1):
            l1 = indx[k]
            l2 = indx[k+1]
            if inord[k] > inord[k+1]:
                print('INCORRECT')
                isitinorder = False
                break
            elif inord[k] == inord[k+1] and node_list[l1].get_depth() > node_list[l2].get_depth():
                print('INCORRECT')
                isitinorder = False
                break
        if isitinorder:
            print('CORRECT')
