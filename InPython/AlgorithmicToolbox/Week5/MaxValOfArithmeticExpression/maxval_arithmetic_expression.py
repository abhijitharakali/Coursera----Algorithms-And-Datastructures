import math


def evalexp(a, b, sym):
    if sym == '+':
        return a + b
    elif sym == '-':
        return a - b
    else:
        return a * b


def MinMax(M, m, i, j, expression):
    n = len(expression)

    most = -math.inf
    least = math.inf

    for k in range(i, j):
        sym = expression[2 * k + 1]
        # print(i)
        # print(k)
        # print(j)
        # print(sym)
        # print(M[k+1][j])
        a = evalexp(M[i][k], M[k + 1][j], sym)
        b = evalexp(M[i][k], m[k + 1][j], sym)
        c = evalexp(m[i][k], M[k + 1][j], sym)
        d = evalexp(m[i][k], m[k + 1][j], sym)
        least = min(least, a, b, c, d)
        most = max(most, a, b, c, d)
    return most, least


def GetMinMaxValues(expression):
    n = (len(expression) + 1) // 2

    if n == 2:
        a = int(expression[0])
        b = int(expression[2])
        sym = expression[1]
        mm = evalexp(a, b, sym)
        MM = mm
        return MM, mm

    else:

        m = [[None for x in range(n)] for x in range(n)]
        M = [[None for x in range(n)] for x in range(n)]

        for i in range(n):
            j = 2 * i
            # print(expression[j])
            M[i][i] = int(expression[j])
            m[i][i] = int(expression[j])

        for s in range(1, n):
            for i in range(0, n - s):
                j = i + s
                # print(i,j)
                M[i][j], m[i][j] = MinMax(M, m, i, j, expression)

        return M[0][n - 1], m[0][n - 1]


if __name__ == "__main__":
    expression = input()
    M, m = GetMinMaxValues(expression)
    # print(m)
    print(M)
