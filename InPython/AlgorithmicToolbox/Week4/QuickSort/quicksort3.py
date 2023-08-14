# Uses python3
import random


def partition3(a, l, r):
    # write your code here
    x = a[l]

    m1 = l
    m2 = r
    m = []

    j = l
    while j <= m2:
        if a[j] < x:
            a[j], a[m1] = a[m1], a[j]
            m1 += 1
            j += 1
        elif a[j] == x:
            j += 1
        else:
            a[j], a[m2] = a[m2], a[j]
            m2 -= 1

    m.append(m1)
    m.append(m2)
    return m


def partition2(a, l, r):
    x = a[l]
    j = l
    for i in range(l + 1, r + 1):
        if a[i] <= x:
            j += 1
            a[i], a[j] = a[j], a[i]
    a[l], a[j] = a[j], a[l]
    return j


def randomized_quick_sort(a, l, r):
    if l >= r:
        return
    k = random.randint(l, r)
    a[l], a[k] = a[k], a[l]
    # use partition3
    m = partition3(a, l, r)
    m1 = m[0]
    m2 = m[1]
    randomized_quick_sort(a, l, m1 - 1)
    randomized_quick_sort(a, m2 + 1, r)


def sort2(a, l, r):
    if l >= r:
        return
    k = random.randint(l, r)
    a[l], a[k] = a[k], a[l]
    m = partition2(a, l, r)
    sort2(a, l, m - 1)
    sort2(a, m + 1, r)


if __name__ == '__main__':
    n = int(input())
    a = [int(x) for x in input().split()]
    randomized_quick_sort(a, 0, n - 1)
    for x in a:
        print(x, end=' ')

# if __name__ == '__main__':
#
#         k = 10000
#         n = random.randint(1, k)
#         test = random.sample(range(0, 1000000000), n)
#         # print(test)
#         x = []
#         y = []
#         x.extend(test)
#         y.extend(test)
#         sort2(x, 0, n - 1)
#         randomized_quick_sort(y, 0, n - 1)
#
#         while x == y:
#             n = random.randint(1, k)
#             test = random.sample(range(0, 1000000000), n)
#             x = []
#             y = []
#             x.extend(test)
#             y.extend(test)
#             sort2(x, 0, n - 1)
#             randomized_quick_sort(y, 0, n - 1)
#             if x != y:
#                 for i in test:
#                     print(test, end=' ')
#                 break
