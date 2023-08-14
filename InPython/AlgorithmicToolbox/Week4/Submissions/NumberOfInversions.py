cnt = 0


def mergesort(lst):
    n = len(lst)
    global cnt
    if n == 1:
        return lst
    elif n == 2:
        a = lst[0]
        b = lst[1]
        if a > b:
            cnt += 1
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
    global cnt

    i = 0
    j = 0
    k = 0

    na = len(a)

    out = []

    while i < len(a) and j < len(b):
        if a[i] <= b[j]:
            out.append(a[i])
            i += 1
            na -= 1
        else:
            out.append(b[j])
            j += 1
            cnt = cnt + na
    while i < len(a):
        out.append(a[i])
        i += 1
    while j < len(b):
        out.append(b[j])
        j += 1
    return out

if __name__ == '__main__':
    n = int(input())
    lst = [int(x) for x in input().split()]
    sorted_lst = mergesort(lst)
    print(cnt)