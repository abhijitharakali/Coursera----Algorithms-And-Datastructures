# python3


def ComputePrefixFunction(p):
    s = [0] * len(p)
    border = 0
    for i in range(1, len(p)):
        while border > 0 and p[i] != p[border]:
            border = s[border - 1]
        if p[i] == p[border]:
            border += 1
        else:
            border = 0
        s[i] = border
    return s


def find_occurances(p, t):
    s = p + '$' + t
    prefix_func = ComputePrefixFunction(s)
    result = []
    for i in range(len(p) + 1, len(s)):
        if prefix_func[i] == len(p):
            result.append(i - 2 * len(p))
    return result


if __name__ == '__main__':
    ptrn = input()
    text = input()
    prefix = find_occurances(ptrn, text)
    for pos in prefix:
        print(pos, end=' ')