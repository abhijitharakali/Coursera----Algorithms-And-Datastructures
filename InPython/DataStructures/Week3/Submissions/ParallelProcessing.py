

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

def swap_threads(thread_list,i1,i2):
    temp = thread_list[i1]
    thread_list[i1] = thread_list[i2]
    thread_list[i2] = temp

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

        threads[0].set_free_time(cur_time+times.pop(0))
        sift_down(threads,0)

        # op = []
        # for k in range(n):
        #     op.append(str(threads[k].get_index())+' '+str(threads[k].get_free_time()))
        # print(op)
