import math
import random
import time


cnta = 0
cntb = 0

def argsort(seq):
    return sorted(range(len(seq)), key=seq.__getitem__)


def mergesort(x, y):
    n = len(x)
    if n == 1:
        return x, y
    elif n == 2:
        xr = []
        yr = []
        xr.append(int(x[1]))
        xr.append(int(x[0]))
        yr.append(int(y[1]))
        yr.append(int(y[0]))
        if x[0] > x[1]:
            return xr, yr
        else:
            return x, y
    else:
        n1 = int(n / 2)
        xl = x[0:n1]
        xr = x[n1:n]
        yl = y[0:n1]
        yr = y[n1:n]

    xln, yln = mergesort(xl, yl)
    xrn, yrn = mergesort(xr, yr)

    xf, yf = merge(xln, yln, xrn, yrn)
    return xf, yf


def merge(xl, yl, xr, yr):
    n = len(xl) + len(xr)

    i = 0
    j = 0
    k = 0
    xf = []
    yf = []

    while i < len(xl) and j < len(xr):
        if xl[i] < xr[j]:
            xf.append(int(xl[i]))
            yf.append(int(yl[i]))
            i += 1
        else:
            xf.append(int(xr[j]))
            yf.append(int(yr[j]))
            j += 1
    while i < len(xl):
        xf.append(int(xl[i]))
        yf.append(int(yl[i]))
        i += 1
    while j < len(xr):
        xf.append(int(xr[j]))
        yf.append(int(yr[j]))
        j += 1
    return xf, yf



def to_points(x, y):
    p = []
    p.append(int(x))
    p.append(int(y))
    return p


def min_dist(x, y, xs, ys):
    global cnta
    # print(cnta)
    # ts = time.time()
    if len(x) <= 1:
        return float('inf')
    if len(x) == 2:
        pa = to_points(x[0], y[0])
        pb = to_points(x[1], y[1])
        return dist(pa, pb)
    if len(x) == 3:
        p1 = to_points(x[0], y[0])
        p2 = to_points(x[1], y[1])
        p3 = to_points(x[2], y[2])

        d1 = dist(p1, p2)
        d2 = dist(p2, p3)
        d3 = dist(p3, p1)

        return min(d1, d2, d3)

    n = len(x)
    n1 = int(n / 2)

    xmid = x[n1]

    xl = []
    xsl = []
    xr = []
    xsr = []

    yl = []
    ysl = []
    yr = []
    ysr = []

    yeq = []
    yseq = []

    for i in range(n):
        # print(cnta)
        # print(xs[i])
        if x[i] < xmid:
            xl.append(x[i])
            yl.append(y[i])
        elif x[i] > xmid:
            xr.append(x[i])
            yr.append(y[i])
        else:
            yeq.append(y[i])
        # print(xs[i])
        if xs[i] < xmid:
            xsl.append(xs[i])
            ysl.append(ys[i])
        elif xs[i] > xmid:
            xsr.append(xs[i])
            ysr.append(ys[i])
        else:
            yseq.append(ys[i])

    if len(xl) == 0:
        if len(xr) == 0:
            d = mindy(yseq)
        else:
            d4 = mindy(yseq)
            d5 = min_dist(xr, yr, xsr, ysr)
            d = min(d4, d5)
    else:
        d6 = min_dist(xl, yl, xsl, ysl)
        if len(xr) == 0:
            d4 = mindy(yseq)
            d = min(d6,d4)
        else:
            d4 = mindy(yseq)
            d5 = min_dist(xr, yr, xsr, ysr)
            d = min(d4,d5,d6)


    xsmid = []
    ysmid = []

    for j in range(len(xs)):
        if (xs[j]-xmid) ** 2 < d:
            xsmid.append(xs[j])
            ysmid.append(ys[j])

    # print(str(len(pl))+ ' ' +str(len(pr)))

    dstrip = mind_strip(xsmid, ysmid, d)

    # cnta += 1
    # print('min_dist time: ' + str(time.time()-ts) + ' pindx length: ' + str(len(pindx)) + ' Count: ' + str(cnta))

    return min(d, dstrip)

def mindy(yseq):
    if len(yseq) <= 1:
        return float('inf')
    elif len(yseq) == 2:
        return abs(yseq[1]-yseq[0])
    n = len(yseq)
    d = abs(yseq[1]-yseq[0])
    for i in range(n-1):
        d = min(d,abs(yseq[i+1]-yseq[i]))
    return d

def mind_strip(xs, ys, d):
    # ts = time.time()
    dstrip = d
    # global cntb
    if len(xs) <= 1:
        return dstrip

    for j in range(len(xs)):
        pj = to_points(xs[j], ys[j])
        k = 1
        while j + k < len(xs) and k < 8:
            pjk = to_points(xs[j + k], ys[j + k])
            djjk = dist(pj, pjk)
            dstrip = min(d, djjk, dstrip)
            k += 1
    # cntb += 1
    # print('mind_strip time: ' + str(time.time()-ts) + ' nx length: ' + str(len(nx)) + ' Count: ' + str(cntb))
    return dstrip


def stress_test_min_dist(x, y):
    n = len(x)
    pindx = [0, 1, 2]
    if n == 2:
        pa = to_points(x[pindx[0]], y[pindx[0]])
        pb = to_points(x[pindx[1]], y[pindx[1]])
        return dist(pa, pb)
    if n == 3:
        p1 = to_points(x[pindx[0]], y[pindx[0]])
        p2 = to_points(x[pindx[1]], y[pindx[1]])
        p3 = to_points(x[pindx[2]], y[pindx[2]])

        d1 = dist(p1, p2)
        d2 = dist(p2, p3)
        d3 = dist(p3, p1)

        return min(d1, d2, d3)

    d = 1000000000 ** 2
    for i in range(n - 1):
        k = i + 1
        pi = to_points(x[i], y[i])
        while k < n:
            pk = to_points(x[k], y[k])
            d = min(d, dist(pi, pk))
            k += 1
    return d


def dist(p1, p2):
    return (p1[0] - p2[0]) ** 2 + (p1[1] - p2[1]) ** 2


if __name__ == '__main__':

    # Following is the main code to be uploaded

    n = int(input())
    x = []
    y = []
    for i in range(n):
        a, b = [int(i) for i in input().split()]
        x.append(a)
        y.append(b)

    xs, ys = mergesort(x, y)
    yss, xss = mergesort(y, x)

    print(str.format('{0:.6f}', math.sqrt(min_dist(xs,ys,xss,yss))))

    print(str.format('{0:.6f}', math.sqrt(min_dist(xs, ys, xss, yss))))

    # print(math.sqrt(stress_test_min_dist(xs,ys)))
    # print(min_dist(xs,ys,xss,yss))
    # print(stress_test_min_dist(xs,ys))

    # Following is the stress test for this program

    # k = 32
    # lim = 1000000000
    # # lim = 64
    # nlim = -1 * lim
    # # nlim = lim//2
    # # n = random.randint(2, k)
    # n = 10000
    # d1 = 0
    # d2 = 0
    # while d1 == d2:
    #     x = random.sample(range(nlim, lim), n)
    #     y = random.sample(range(nlim, lim), n)
    #
    #     time_start = time.time()
    #
    #     xs, ys = mergesort(x,y)
    #     yss, xss = mergesort(y, x)
    #     d1 = min_dist(xs, ys, xss, yss)
    #     time_end = time.time()
    #     d2 = stress_test_min_dist(xs, ys)
    #     print(time_end-time_start)
    #     if d1 != d2:
    #         print(xs)
    #         print(ys)
    #         print(d1)
    #         print(d2)
    #         break

    # Following is the code to test argsort(seq)

    # indx = argsort(x)
    # print(indx)
    # print(x)
    # print(y)

    # Following code was written to debug the exceed time limit issue

    # ts = time.time()
    #
    # lim = 1000000000 - 1
    # nlim = -1 * lim
    # n = 64
    #
    # x = random.sample(range(nlim, lim), n)
    # y = random.sample(range(nlim, lim), n)
    #
    # xs, ys = mergesort(x, y)
    # yss, xss = mergesort(y, x)
    #
    # # print(time.time()-ts)
    #
    # d1 = min_dist(xs, ys, xss, yss)
    # print(time.time() - ts)

    # Following code is to test mergesort(x,y)

    # n = int(input())
    # a = [int(x) for x in input().split()]
    # b = [int(y) for y in input().split()]
    # af, bf = mergesort(a,b)
    # print(af)
    # print(bf)