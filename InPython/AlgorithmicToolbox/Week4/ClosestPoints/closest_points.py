import math
import random
import time

cnta = 0
cntb = 0

def argsort(seq):
    return sorted(range(len(seq)), key=seq.__getitem__)


def to_points(x, y):
    p = []
    p.append(int(x))
    p.append(int(y))
    return p


def min_dist(x, y, xs, ys, pindx):
    global cnta

    ts = time.time()
    if len(pindx) == 2:
        pa = to_points(x[pindx[0]], y[pindx[0]])
        pb = to_points(x[pindx[1]], y[pindx[1]])
        return dist(pa, pb)
    if len(pindx) == 3:
        p1 = to_points(x[pindx[0]], y[pindx[0]])
        p2 = to_points(x[pindx[1]], y[pindx[1]])
        p3 = to_points(x[pindx[2]], y[pindx[2]])

        d1 = dist(p1, p2)
        d2 = dist(p2, p3)
        d3 = dist(p3, p1)

        return min(d1, d2, d3)

    n = len(pindx)
    n1 = int(n / 2)

    pl = []
    pr = []

    for i in range(n1):
        pl.append(pindx[i])
    for j in range(n1, n):
        pr.append(pindx[j])

    d4 = min_dist(x, y, xs, ys, pl)
    d5 = min_dist(x, y, xs, ys, pr)
    d = min(d4, d5)

    # print(str(len(pl))+ ' ' +str(len(pr)))

    xmid = x[pindx[n1]]

    dstrip = mind_strip(xs, ys, xmid, d)

    # for e in range(len(pmid)):
    #     pe = to_points(x[pmid[e]],y[pmid[e]])
    #     f = e+1
    #     while f < len(pmid) and (f-e) < 17:
    #         pf = to_points(x[pmid[f]],y[pmid[f]])
    #         d = min(dist(pe,pf),d)
    #         f += 1

    cnta += 1
    # print('min_dist time: ' + str(time.time()-ts) + ' pindx length: ' + str(len(pindx)) + ' Count: ' + str(cnta))

    return min(d, dstrip)


def mind_strip(xs, ys, xmid, d):
    ts = time.time()
    nx = []
    ny = []
    dstrip = d
    global cntb

    for i in range(len(xs)):
        if (xs[i] - xmid) ** 2 < d:
            nx.append(xs[i])
            ny.append(ys[i])
    if len(nx) <= 1:
        return dstrip

    for j in range(len(nx)):
        pj = to_points(nx[j], ny[j])
        k = 1
        while j + k < len(nx) and k < 8:
            pjk = to_points(nx[j + k], ny[j + k])
            djjk = dist(pj, pjk)
            dstrip = min(d, djjk, dstrip)
            k += 1
    cntb += 1
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

    # n = int(input())
    # x = []
    # y = []
    # for i in range(n):
    #     a, b = [int(i) for i in input().split()]
    #     x.append(a)
    #     y.append(b)
    # sx = argsort(x)
    #
    # xs = []
    # ys = []
    # for i in range(n):
    #     xs.append(x[sx[i]])
    #     ys.append(y[sx[i]])
    #
    # sy = argsort(ys)
    # pindx = list(range(0, len(xs)))
    #
    # xss = []
    # yss = []
    #
    # for i in range(n):
    #     xss.append(xs[sy[i]])
    #     yss.append(ys[sy[i]])
    #
    # print(math.sqrt(min_dist(xs, ys, xss, yss, pindx)))


    # print(min_dist(xs,ys,xss,yss,pindx))
    # print(stress_test_min_dist(xs,ys))

    # Following is the stress test for this program

    k = 10
    lim = 1000000000-1
    nlim = -1*lim
    n = random.randint(2,k)
    n = 64
    d1 = 0
    d2 = 0
    cnt = 0
    while d1 == d2:
        x = random.sample(range(nlim,lim),n)
        y = random.sample(range(nlim,lim),n)

        time_start = time.time()

        sx = argsort(x)
        xs = []
        ys = []
        for i in range(n):
            xs.append(int(x[sx[i]]))
            ys.append(int(y[sx[i]]))
        sy = argsort(ys)
        pindx = list(range(0,n))
        xss = []
        yss = []
        for i in range(n):
            xss.append(xs[sy[i]])
            yss.append(ys[sy[i]])
        d1 = min_dist(xs,ys,xss,yss,pindx)
        time_end = time.time()
        d2 = stress_test_min_dist(xs,ys)
        cnt += 1
        # print(time_end-time_start)
        if(d1 != d2):
            print(xs)
            print(ys)
            print(d1)
            print(d2)
            # print(cnt)
            break

    # indx = argsort(x)
    # print(indx)
    # print(x)
    # print(y)


    # ts = time.time()
    #
    # lim = 1000000000-1
    # nlim = -1*lim
    # n = 64
    #
    # x = random.sample(range(nlim,lim),n)
    # y = random.sample(range(nlim,lim),n)
    #
    # sx = argsort(x)
    # xs = []
    # ys = []
    # for i in range(n):
    #     xs.append(int(x[sx[i]]))
    #     ys.append(int(y[sx[i]]))
    # sy = argsort(ys)
    # pindx = list(range(0,n))
    # xss = []
    # yss = []
    #
    # for i in range(n):
    #     xss.append(xs[sy[i]])
    #     yss.append(ys[sy[i]])
    #
    # # print(time.time()-ts)
    #
    # d1 = min_dist(xs,ys,xss,yss,pindx)
    # print(time.time()-ts)
