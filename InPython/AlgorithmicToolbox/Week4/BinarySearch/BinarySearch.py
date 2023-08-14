def binary_search(dict,n,keys,k):
    ans = []
    low = 0
    high = n-1
    for j in range(k):
        ans.append(bs(dict,low,high,keys[j]))
    return ans

def bs(dict,low,high,key):
    if high >= low:
        mid = int((high+low)/2)
        if dict[mid] == key:
            return mid
        elif dict[mid] > key:
            return bs(dict,low,mid-1,key)
        else:
            return bs(dict,mid+1,high,key)
    else:
        return -1



if __name__ == '__main__':
    l1 = [int(x) for x in input().split()]
    n = l1[0]
    dict = l1[1:len(l1)]
    l2 = [int(y) for y in input().split()]
    k = l2[0]
    keys = l2[1:len(l2)]

    # print(n)
    # print(dict)
    # print(k)
    # print(keys)

    ans = binary_search(dict,n,keys,k)
    for i in ans:
        print(i, end=" ")