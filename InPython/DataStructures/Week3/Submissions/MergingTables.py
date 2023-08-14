

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
        # print(sroot)
        sroot = t[sroot].get_parent().get_index()

    droot = d
    while t[droot].get_parent() != None:
        # print(droot)
        droot = t[droot].get_parent().get_index()

    srank = t[sroot].get_rank()
    srcnt = t[sroot].get_rowcnt()
    drank = t[droot].get_rank()
    drcnt = t[droot].get_rowcnt()
    sroot_index = t[sroot].get_index()
    droot_index = t[droot].get_index()
    if(sroot_index != droot_index):
        if srank == drank:
            t[sroot].set_rowcnt(0)
            t[droot].set_rank(1+drank)
            # Rank has to be incremented if and only if the root ranks are equal. I had a bug where
            # I incremented the ranks of the child unnecessarily in the case where root ranks were not equal.
            # Without proper rank evaluation, the tree height grows and the result is that the run time increases
            # as the program is no longer log*(n).
            t[sroot].set_parent(t[droot])
            t[s].set_parent(t[droot])
            t[droot].set_rowcnt(srcnt+drcnt)
            # t[d].set_parent(t[droot]) # A bug
        elif srank < drank:
            t[sroot].set_rowcnt(0)
            # I had incremented the rank of sroot here, and that was a bug which resulted in time limit exceed
            t[sroot].set_parent(t[droot])
            t[s].set_parent(t[droot])
            t[droot].set_rowcnt(srcnt+drcnt)
            # t[d].set_parent(t[droot]) # A bug
        else:
            t[droot].set_rowcnt(0)
            # I had incremented the rank of droot here, and that was a bug which resulted in time limit exceed
            t[droot].set_parent(t[sroot])
            t[d].set_parent(t[sroot])
            t[sroot].set_rowcnt(srcnt+drcnt)
            # t[s].set_parent(t[sroot]) # A bug
        return (srcnt+drcnt)
    else:
        return srcnt

if __name__ == '__main__':

    in_nm = input().split()
    n = int(in_nm[0]) # Number of tables
    m = int(in_nm[1]) # Number of merge queries
    r = list(map(int, input().split())) # Number of rows in each of the n tables
    assert len(r) == n # Make sure that the input n (number of tables) matches with the length of rows list
    max_row = max(r) # Initialize the max_row variable to maximum of the rows in the n tables

    tables = [] # Create an empty list of nodes

    for i in range(n):
        new_table = create_table_node(i,r[i],0) # Create an empty node corresponding to the ith table
        tables.append(new_table) # Append it to the list of nodes holding the tables information

    final_ans = []

    for j in range(m):
        in_sd = input().split() # Input the m number of merge queries
        source = int(in_sd[1])-1
        destination = int(in_sd[0])-1
        merged_rowcount = merge_tables(tables,source,destination) # perform merge operation and return the merged table's row count
        max_row = max(max_row,merged_rowcount) # Find the new value of the maximum number of row count
        final_ans.append(max_row) # Print the maximum value of the row count among all the existing tables

    for k in range(len(final_ans)): # Print the maximum value of the row count among all the existing tables
        print(final_ans[k])