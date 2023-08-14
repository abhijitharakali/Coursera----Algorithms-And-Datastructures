

class create_table_node:
    def __init__(self, index, row_count, rank):
        self.index = index
        self.row_count = row_count
        self.rank = rank
        self.next = None
    def get_index(self):
        return self.index
    def set_parent(self, nextNode):
        self.next = nextNode
    def get_parent(self):
        return self.next
    def set_rowcnt(self,rowcnt):
        self.row_count = rowcnt
    def get_rowcnt(self):
        return self.row_count
    def set_rank(self,new_rank):
        self.rank = new_rank
    def get_rank(self):
        return self.rank


def merge_tables(t,s,d):

    sroot = s
    while t[sroot].get_parent() != None:
        print(sroot)
        sroot = t[sroot].get_parent().get_index()

    droot = d
    while t[droot].get_parent() != None:
        # print(droot)
        droot = t[droot].get_parent().get_index()

    srank = t[sroot].get_rank()
    srcnt = t[sroot].get_rowcnt()
    drank = t[droot].get_rank()
    drcnt = t[droot].get_rowcnt()

    if srank <= drank:
        t[sroot].set_rowcnt(0)
        t[sroot].set_rank(1+drank)
        t[sroot].set_parent(t[droot])
        t[s].set_parent(t[droot])
        t[droot].set_rowcnt(srcnt+drcnt)
        # t[d].set_parent(t[droot])
    else:
        t[droot].set_rowcnt(0)
        t[droot].set_rank(1+srank)
        t[droot].set_parent(t[sroot])
        t[d].set_parent(t[sroot])
        t[sroot].set_rowcnt(srcnt+drcnt)
        # t[s].set_parent(t[sroot])
    return (srcnt+drcnt)

if __name__ == '__main__':
    a = create_table_node(0,2,0)
    b = create_table_node(1,3,0)
    c = create_table_node(2,2,0)
    d = create_table_node(3,1,0)
    t = []
    t.append(a)
    t.append(b)
    t.append(c)
    t.append(d)
    x = merge_tables(t,0,1)
    y = merge_tables(t,2,3)
    z = merge_tables(t,0,2)