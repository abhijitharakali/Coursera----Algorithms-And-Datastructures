# python3


from collections import deque

class suffixtree(object):

    class Node(object):
        def __init__(self,label):
            self.label = label
            self.out = {}

    def __init__(self,s):
        self.root = self.Node(None)
        self.root.out[s[0]] = self.Node(s)
        for i in range(1,len(s)):
            cur = self.root
            j = i
            while j < len(s):
                if s[j] in cur.out:
                    child = cur.out[s[j]]
                    label = child.label
                    k = j+1
                    while k - j < len(label) and s[k] == label[k - j]:
                        k = k+1
                    if k-j == len(label):
                        cur = child
                        j = k
                    else:
                        old,new = label[k-j],s[k]
                        mid = self.Node(label[:k-j])
                        mid.out[new] = self.Node(s[k:])
                        child.label = label[k-j:]
                        mid.out[old] = child
                        cur.out[s[j]] = mid
                else:
                    cur.out[s[j]] = self.Node(s[j:])
    def Print(self):
        queue = deque()
        queue.append(self.root)
        while queue:
            u = queue.popleft()
            if u != self.root:
                print(u.label)
            for label, node in u.out.items():
                queue.append(node)

if __name__ =='__main__':
    text = input()
    stree = suffixtree(text)
    stree.Print()