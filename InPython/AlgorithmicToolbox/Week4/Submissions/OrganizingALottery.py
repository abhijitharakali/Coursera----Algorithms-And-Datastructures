def mergesort(lst):
    n = len(lst)
    if n == 1:
        return lst
    elif n == 2:
        a = lst[0]
        b = lst[1]
        if a > b:
            return [b, a]
        else:
            return lst
    else:
        n1 = int(n / 2)
        a = lst[0:n1]
        b = lst[n1:n]
        sorted_lst = merge(mergesort(a), mergesort(b))
        return sorted_lst


def merge(a, b):
    n = len(a) + len(b)
    i = 0
    j = 0
    k = 0
    out = []
    while i < len(a) and j < len(b):
        if a[i] <= b[j]:
            out.append(a[i])
            i += 1
        else:
            out.append(b[j])
            j += 1
    while i < len(a):
        out.append(a[i])
        i += 1
    while j < len(b):
        out.append(b[j])
        j += 1
    return out

def bsearch_a(arr,key):
    n = len(arr)
    right = n-1
    left = 0
    cnt = 0

    while(left<=right):
        mid=int((left+right)/2)
        if arr[mid] <= key:
            cnt = mid + 1
            left = mid + 1
        else:
            right = mid - 1
    return cnt

def bsearch_b(arr,key):
    n = len(arr)
    right = n-1
    left = 0
    cnt = 0

    while(left<=right):
        mid=int((left+right)/2)
        if arr[mid] < key:
            cnt = mid + 1
            left = mid + 1
        else:
            right = mid - 1
    return cnt

if __name__ == '__main__':

    s, p = [int(i) for i in input().split()]
    a = []
    b = []

    for i in range(s):
        x, y = [int(i) for i in input().split()]
        a.append(x)
        b.append(y)

    pts = input().split()

    a_sorted = mergesort(a)
    b_sorted = mergesort(b)

    ans = []

    for i in pts:
        j = int(i)
        na = bsearch_a(a_sorted,j)
        nb = bsearch_b(b_sorted,j)
        ans.append(na-nb)

    for j in ans:
        print(j, end=" ")

    # # Code below is to test the bsearch(arr,key) function
    #
    # test = [int(i) for i in input().split()]
    # lst = mergesort(test)
    # key = int(input())
    # print(lst)
    # print(bsearch_b(lst,key))