# python3

from collections import deque

def CreateNetwork(n, m, ing):
    graph = [[0] * (n + m + 2) for _ in range(n + m + 2)]
    for i in range(1, n + 1):
        graph[0][i] = 1
        for j in range(m):
            graph[i][n + 1 + j] = ing[i - 1][j]
    for k in range(n + 1, n + m + 1):
        graph[k][-1] = 1
    return graph

def CheckPath(Gf, path):
    V = len(Gf)
    visited = [False] * V
    visited[0] = True
    queue = deque([0])
    while queue:
        u = queue.popleft()
        if u == V - 1:
            return True
        for v in range(V):
            if not visited[v] and Gf[u][v] > 0:
                queue.append(v)
                visited[v] = True
                path[v] = u
    return visited[V - 1]

def FindFlow(Gf, n):
    V = len(Gf)
    route = list(range(V))
    while CheckPath(Gf, route):
        mf = float('inf')
        v = V - 1
        while v != 0:
            u = route[v]
            mf = min(mf, Gf[u][v])
            v = u
        v = V - 1
        while v != 0:
            u = route[v]
            Gf[u][v] -= mf
            Gf[v][u] += mf
            v = u
    ans = [-1] * n
    for i in range(V):
        if Gf[V-1][i] == 1:
            crew = i - n
            flight = Gf[i].index(1)
            ans[flight - 1] = crew
    return ans

if __name__ == '__main__':
    n, c = map(int, input().split())
    ing = [list(map(int, input().split())) for i in range(n)]

    gf = CreateNetwork(n, c, ing)
    ans = FindFlow(gf, n)

    for e in ans:
        print(e, end=' ')