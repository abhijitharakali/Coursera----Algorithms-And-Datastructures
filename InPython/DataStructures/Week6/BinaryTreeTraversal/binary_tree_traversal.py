# import resource
# resource.setrlimit(resource.RLIMIT_STACK, (resource.RLIM_INFINITY, resource.RLIM_INFINITY))
import sys
import threading
sys.setrecursionlimit(2**30)
threading.stack_size(2**62)

class bst_node:
    def __init__(self,index,key):
        self.index = index
        self.key = key
        self.left = None
        self.right = None
    def get_key(self):
        return self.key
    def get_index(self):
        return self.index
    def set_left(self,leftNode):
        self.left = leftNode
    def set_right(self,rightNode):
        self.right = rightNode
    def get_left(self):
        return self.left
    def get_right(self):
        return self.right

def inorder(node_list,start_node,inord):
    if start_node == None:
        return
    inorder(node_list,start_node.get_left(),inord)
    inord.append(start_node.get_key())
    inorder(node_list,start_node.get_right(),inord)

def preorder(node_list,start_node,preord):
    if start_node == None:
        return
    preord.append(start_node.get_key())
    preorder(node_list,start_node.get_left(),preord)
    preorder(node_list,start_node.get_right(),preord)

def postorder(node_list, start_node, postord):
    if start_node == None:
        return
    postorder(node_list,start_node.get_left(),postord)
    postorder(node_list,start_node.get_right(),postord)
    postord.append(start_node.get_key())


if __name__ == '__main__':
    n = int(input())
    node_list = []
    l = []
    r = []
    for i in range(n):
        k, ll, rr = [int(x) for x in input().split()]
        tempnode = bst_node(i,k)
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
    inorder(node_list,node_list[0],inord)
    preorder(node_list,node_list[0],preord)
    postorder(node_list, node_list[0], postord)
    print(' '.join([str(inord[k]) for k in range(len(inord))]))
    print(' '.join([str(preord[k]) for k in range(len(preord))]))
    print(' '.join([str(postord[k]) for k in range(len(postord))]))


