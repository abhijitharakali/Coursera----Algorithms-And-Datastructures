# import resource
# resource.setrlimit(resource.RLIMIT_STACK, (resource.RLIM_INFINITY, resource.RLIM_INFINITY))
# import sys
# import threading
# sys.setrecursionlimit(2**30)
# threading.stack_size(2**62)

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

def preorder(start_node,preord):
    if start_node is None:
        return
    stack = []
    stack.append(start_node)
    while stack:
        current = stack.pop()
        preord.append(current.key)
        if current.right is not None:
            stack.append(current.right)
        if current.left is not None:
            stack.append(current.left)

def postorder(start_node, postord):
    stack = []
    stack.append(start_node)
    while stack:
        current = stack.pop()
        postord.append(current.key)
        if current.left is not None:
            stack.append(current.left)
        if current.right is not None:
            stack.append(current.right)
    postord.reverse()



if __name__ == '__main__':
    n = int(input())
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
    preord = []
    postord = []
    inorder(node_list[0],inord)
    preorder(node_list[0],preord)
    postorder(node_list[0], postord)
    print(' '.join([str(inord[k]) for k in range(len(inord))]))
    print(' '.join([str(preord[k]) for k in range(len(preord))]))
    print(' '.join([str(postord[k]) for k in range(len(postord))]))


