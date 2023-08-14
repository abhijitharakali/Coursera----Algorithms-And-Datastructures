# Uses python3
# import numpy as np

def argsort(seq):
    return sorted(range(len(seq)), key=seq.__getitem__)

def min_points(x, y):
    if len(y) == 1:
        print(len(y))
        print(y[0])
    else:
        n = len(y)
        cnt = 0
        indx = []
        xindx = []
        prev=-1

        while cnt < n:
            pos=cnt
            pntr = int(y[cnt])
            if cnt > n - 2:
                if int(x[cnt]) > prev:
                    indx.append(pos)
                    xindx.append(y[pos])
                break
            # print(pntr,x[cnt+1],cnt)
            while pntr > int(x[cnt + 1]) and xindx.count(int(x[cnt+1])) == 0:
                # print(pntr)
                cnt += 1
                if cnt >=n-1:
                    break
            if pntr > prev and xindx.count(int(x[cnt])) == 0:
                indx.append(pos)
                xindx.append(y[pos])
            cnt += 1
            prev = pntr

        print(len(indx))
        for i in range(len(indx)):
            print(y[indx[i]])

def find_covering_points(x,y):

    if len(y) == 1:
        print(len(y))
        print(y[0])
        return

    pos=0
    cnt=0
    indx = []

    while pos < n:
        while int(x[cnt]) <= y[pos] and cnt < n:
            cnt += 1
            if cnt > n-1:
                break
        indx.append(pos)
        pos = cnt
    print(len(indx))
    for i in range(len(indx)):
        print(y[indx[i]])
    return
if __name__ == '__main__':
    n = int(input())
    a = []
    b = []
    for i in range(n):
        x, y = input().split()
        a.append(int(x))
        b.append(int(y))
    # print(a, b)
    # print(argsort(b))
    indx = argsort(b)
    # print(indx)
    x = []
    y = []
    for i in range(n):
        x.append(a[int(indx[i])])
        y.append(b[int(indx[i])])
    # print(x)
    # print(y)

    # for j in range(n):
    #     print(x[j],y[j])

    # min_points(x, y)
    find_covering_points(x,y)