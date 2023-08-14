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
        if a[i] < b[j]:
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

if __name__ == '__main__':
    lst = [int(x) for x in input().split()]
    sorted_lst = mergesort(lst)
    print(sorted_lst)

#     Following function was stolen from https://www.geeksforgeeks.org/merge-two-sorted-arrays/
#
# def mergeArrays(arr1, arr2, n1, n2):
#         arr3 = [None] * (n1 + n2)
#         i = 0
#         j = 0
#         k = 0
#         while i < n1 and j < n2:
#             if arr1[i] < arr2[j]:
#                 arr3[k] = arr1[i]
#                 k = k + 1
#                 i = i + 1
#             else:
#                 arr3[k] = arr2[j]
#                 k = k + 1
#                 j = j + 1
#
#         while i < n1:
#             arr3[k] = arr1[i];
#             k = k + 1
#             i = i + 1
#
#         while j < n2:
#             arr3[k] = arr2[j];
#             k = k + 1
#             j = j + 1