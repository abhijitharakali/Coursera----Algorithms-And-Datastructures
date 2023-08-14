# python3

from collections import deque

def CheckPath(Gf, route):
    n = len(Gf)
    visited = [False] * n
    visited[0] = True
    queue = deque([0])
    while queue:
        temp = queue.popleft()
        if temp == n - 1:
            return True
        for i in range(n):
            if not visited[i] and Gf[temp][i] > 0:
                queue.append(i)
                visited[i] = True
                route[i] = temp
    return visited[n-1]


def Evac_Max(Gf):
    ans1 = 0
    n = len(Gf)
    route = list(range(n))
    while CheckPath(Gf, route):
        minf = float('inf')
        v = n - 1
        while v != 0:
            u = route[v]
            minf = min(minf, Gf[u][v])
            v = u
        v = n - 1
        while v != 0:
            u = route[v]
            Gf[u][v] -= minf
            Gf[v][u] += minf
            v = u
        ans1 += minf
    return ans1


if __name__ == '__main__':
    n, m = map(int, input().split())
    gf = [[0] * n for i in range(n)]
    for _ in range(m):
        u, v, c = map(int, input().split())
        gf[u - 1][v - 1] += c
    ans = Evac_Max(gf)
    print(ans)