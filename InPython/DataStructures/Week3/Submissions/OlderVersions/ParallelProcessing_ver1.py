

class create_thread:

    def __init__(self,index,free_time,job_length):
        self.index = index
        self.free_time = free_time
        self.job_length = job_length

    def set_free_time(self,next_free_time):
        self.free_time = next_free_time

    def set_job_length(self,next_job_length):
        self.job_length = next_job_length

    def get_free_time(self):
        return self.free_time

    def get_index(self):
        return self.index

def get_parent(index):
    if index%2 == 0:
        return index//2
    else:
        return (index-1)//2

def swap_threads(thread_list,i1,i2):
    temp = thread_list[i1]
    thread_list[i1] = thread_list[i2]
    thread_list[i2] = temp

def sift_up(thread_list,key):

    key_int = key

    while key_int > 0:
        pindex = get_parent(key_int)
        parent = thread_list[pindex]
        child = thread_list[key_int]
        if parent.get_free_time() != child.get_free_time():
            if parent.get_free_time() > child.get_free_time():
                swap_threads(thread_list,pindex,key_int)
                key_int = pindex
            else:
                break
        else:
            if parent.get_index() > child.get_index():
                swap_threads(thread_list,pindex,key_int)
                key_int = pindex
            else:
                break

def sift_down(thread_list,key):

    if 2*key+1 >= len(thread_list):
        return

    parent = key
    left = 2*key+1
    right = 2*key+2
    priority_index = parent

    tp = thread_list[parent].get_free_time()
    tl = thread_list[left].get_free_time()
    ip = thread_list[parent].get_index()
    il = thread_list[left].get_index()

    if right >= len(thread_list):
        if tp > tl:
            priority_index = left

        elif tp == tl:
            if ip > il:
                priority_index = left

    else:
        tr = thread_list[right].get_free_time()
        ir = thread_list[right].get_index()

        if tp > tl:
            if tl > tr:
                priority_index = right
            elif tl == tr:
                if il > ir:
                    priority_index = right
                else:
                    priority_index = left
            else: # tp > tl, tr > tl
                priority_index = left
                # if tp > tr:
                #     swap_threads(thread_list,left,right)
                #     sift_down(thread_list,left)
                #     priority_index = right
                # elif tp == tr: # tp > tl, tp = tr
                #     if ip > ir:
                #         swap_threads(thread_list,parent,right)
                #         sift_down(thread_list,right)
                #     priority_index = left
                # else: # tp > tl, tr > tl, tr > tp
                #     priority_index = left

        elif tp == tl:
            if tl > tr:
                priority_index = right
            elif tl == tr:
                if ip > il:
                    if il > ir:
                        priority_index = right
                    elif ip > ir:
                        priority_index = left
                    else:
                        priority_index = left

        else:
            if tl > tr:
                if tp > tr: # tl > tp, tl > tr
                    priority_index = right
                elif tp == tr: # tl > tp, tl > tr
                    if ip > ir:
                        swap_threads(thread_list,parent,right)
                        sift_down(thread_list,right)

                else: # tl > tp, tl > tr, tp < tr
                    priority_index = parent
            elif tl == tr: # tp < tl, tl = tr
                priority_index = parent
            else: # tp < tl, tl < tr
                priority_index = parent

    if parent != priority_index:
        swap_threads(thread_list,parent,priority_index)
        sift_down(thread_list,priority_index)

def heapify_threads(thread_list):

    root_thread = thread_list[0]

    n = len(thread_list)
    swap_threads(thread_list,0,n-1)
    thread_list.pop(n-1)
    sift_down(thread_list,0)

    # thread_list.pop(0)
    # tlen = len(thread_list)
    # parent = get_parent(tlen-1)
    #
    # for i in range(parent,-1,-1):
    #     sift_down(thread_list,i)

    return root_thread


if __name__ == '__main__':
    in_nm = input().split()
    n = int(in_nm[0])
    m = int(in_nm[1])
    times = list(map(int, input().split()))

    assert m == len(times)

    threads = []

    for i in range(n):
        threads.append(create_thread(i,0,0))


    while len(times) > 0:
        free_thread = threads[0]

        cur_index = free_thread.get_index()
        cur_time = free_thread.get_free_time()
        print(str(cur_index)+' '+str(cur_time))

        # free_thread.set_free_time(cur_time+times.pop(0))
        threads[0].set_free_time(cur_time+times.pop(0))
        # threads.append(free_thread)
        sift_down(threads,0)

        # op = []
        # for k in range(n):
        #     op.append(str(threads[k].get_index())+' '+str(threads[k].get_free_time()))
        # print(op)
