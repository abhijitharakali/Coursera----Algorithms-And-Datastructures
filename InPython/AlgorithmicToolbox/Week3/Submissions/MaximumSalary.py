def largestnumber(nums):
    return int(''.join(str(i) for i in nums))


def argsort(seq):
    return sorted(range(len(seq)), key=seq.__getitem__)


def greater(a, b):
    x = int(str(a) + str(b))
    y = int(str(b) + str(a))
    return x < y


def mergesort(lst):
    n = len(lst)
    if n == 1:
        return lst
    elif n == 2:
        a = lst[0]
        b = lst[1]
        if greater(a, b):
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
        if greater(b[j], a[i]):
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
    n = int(input())
    nums = [int(x) for x in input().split()]
    k = len(nums)
    # print(mergesort(nums))
    sorted_nums=mergesort(nums)
    # print(sorted_nums)
    print(largestnumber(sorted_nums))

    # Code below is for single digit integers

    # indx = argsort(nums)
    # sorted_nums = []
    # for i in range(len(indx)):
    #     sorted_nums.append(nums[indx[k - i - 1]])
    # # print(indx)
    # m = largestnumber(sorted_nums)
    # print(m)
