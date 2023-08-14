# python3


def SuffixArray(bwtin):
    sfx = []
    for i in range(len(bwtin)):
        sfx.append((bwtin[i:], i))
    sfx.sort()
    ans = []
    for s in sfx:
        ans.append(s[1])
    return ans


if __name__ == '__main__':
    bwtin = input()
    ans = SuffixArray(bwtin)
    for posn in ans:
        print(posn, end=' ')