

def parent_key_cal(key):
    if key % 2 == 0:
        parent_key = key//2
    else:
        parent_key = (key - 1)//2
    return parent_key

def swap(alist, key1, key2):
    temp = alist[key1]
    alist[key1] = alist[key2]
    alist[key2] = temp

def return_min_key(alist, parent, left, right, criteria):

    min_value = parent
    if alist[parent][criteria] > alist[left][criteria]:
        min_value = left
        if right != -1 and alist[min_value][criteria] > alist[right][criteria]:
            min_value = right
    elif alist[parent][criteria] < alist[left][criteria]:
        if right != -1 and alist[min_value][criteria] > alist[right][criteria]:
            min_value = right

    return min_value

def shift_up(alist, key):

    while key > 1:

        parent = parent_key_cal(key)
        if alist[parent][1] != alist[key][1]:
            if alist[parent][1] > alist[key][1]:
                swap(alist, parent, key)
                key = parent
            else:
                break
        else:
            if alist[parent][0] > alist[key][0]:
                swap(alist, parent, key)
                key = parent
            else:
                break

def shift_down(alist, key):

    if 2*key >= len(alist):
        return

    parent = key
    left = 2*key
    right = 2*key + 1

    if right >= len(alist):

        if (alist[parent] == alist[left]) == True:
            min_value = return_min_key(alist, parent, left, -1, 0)
        else:
            min_value = return_min_key(alist, parent, left, -1, 1)

    else:

        if (alist[parent] == alist[left] == alist[right]) == True:
            min_value = return_min_key(alist, parent, left, right, 0)
        else:
            min_value = return_min_key(alist, parent, left, right, 1)

    if min_value != parent:
        swap(alist, parent, min_value)
        shift_down(alist, min_value)


def min_heap(alist):
    # Index 0 element is dummy. minimum element's index is 1
    min = alist[1]
    alist.pop(1)

    # Maintain heap structure
    parent_last_element = parent_key_cal(len(alist)-1)
    for key in reversed(range(1, parent_last_element + 1)):
        shift_down(alist, key)

    return min

def heap_insert(alist, value):
    alist.append(value)
    shift_up(alist, len(alist)-1)

line1 = input().split()
n = int(line1[0])
m = int(line1[1])
jobs = list(map(int, input().split()))
threads = []
for i in range(n):
    threads.append([i, 0])

# Insert dummy element to make heap calculation easier
threads.insert(0,[-1,-1])

record = []
# O(M)
while len(jobs) > 0:
    # Allocate a job to a thread and record it this moment
    # "threads" is min_heap along with time to finish a allocated job. 0 -> thread order, 1 ->  time to finish the job
    next_thread = min_heap(threads)  # O(lgN)
    record.append([next_thread[0], next_thread[1]])

    # Updated poped thread as much as time to finish the next job
    next_thread[1] += jobs.pop(0)

    # Insert this into min_heap
    heap_insert(threads, next_thread)

for i in range(len(record)):
    print(str(record[i][0]) + ' ' + str(record[i][1]))