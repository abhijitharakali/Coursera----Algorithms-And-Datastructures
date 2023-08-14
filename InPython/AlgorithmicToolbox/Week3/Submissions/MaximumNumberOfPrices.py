def ntn(n):
    if n == 0 or n == 1:
        return n
    else:
        k = 1
        while (k+0.5)**2 <= 2*n+0.25:
            k += 1
        return k - 1

if __name__ == '__main__':
    n = int(input())
    # for k in range(n):
    #     print(k, ntn(k))
    k = ntn(n)

    print(k)
    ans = []

    if n == 1 or n == 2:
        print(n)
    elif n == k*(k+1)/2:
        for i in range(k):
            ans.append(i+1)
        for i in ans:
            print(i, end=" ")
    else:
        for i in range(k-1):
            ans.append(i+1)
        ans.append(int(n-k*(k-1)/2))
        for i in ans:
            print(i, end=" ")