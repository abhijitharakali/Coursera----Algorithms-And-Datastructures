# python3


from collections import deque


def CreateAdj(n, stock):
    adj = [[0] * n for _ in range(n)]
    for i in range(n):
        for j in range(i + 1, n):
            top = all([x < y for x, y in zip(stock[i], stock[j])])
            bot = all([x > y for x, y in zip(stock[i], stock[j])])
            if top:
                adj[i][j] = 1
            elif bot:
                adj[j][i] = 1
    return adj


def CreateNetwork(n, adj):
    graph = [[0] * (2 * n + 2) for _ in range(2 * n + 2)]
    for i in range(1, n + 1):
        graph[0][i] = 1
        for j in range(n):
            graph[i][n + 1 + j] = adj[i - 1][j]
    for k in range(n + 1, 2 * n + 1):
        graph[k][-1] = 1
    return graph


def CheckPath(Gf, path):
    k = len(Gf)  # network中一共k个结点
    visited = [False] * k
    queue = deque([0])
    visited[0] = True
    while queue:
        u = queue.pop()
        if u == k - 1:
            return True
        for v in range(k):
            if not visited[v] and graph[u][v] > 0:
                queue.append(v)
                visited[v] = True
                path[v] = u
    return visited[-1]


def FindFlow(Gf):
    k = len(Gf)
    route = [-1] * k
    ans = 0
    while CheckPath(Gf, route):
        flow = float('inf')
        v = k - 1
        while v > 0:
            u = route[v]
            flow = min(flow, graph[u][v])
            v = u
        v = k - 1
        while v > 0:
            u = route[v]
            graph[u][v] -= flow
            graph[v][u] += flow
            v = u
        ans += flow
    return ans


if __name__ == '__main__':
    n, m = map(int, input().split())
    data = [list(map(int, input().split())) for _ in range(n)]
    adj = CreateAdj(n, data)
    graph = CreateNetwork(n, adj)
    flow = FindFlow(graph)
    ans = n - flow
    print(ans)