# python3


def PreprocessBWT(bwtin):
    counts = {'$': 0, "A": 0, 'C': 0, 'G': 0, 'T': 0}
    for char in bwtin:
        counts[char] += 1
    symb = ['$', 'A', 'C', 'G', 'T']
    starts = {'$': 0}
    for i in range(1, 5):
        starts[symb[i]] = starts[symb[i-1]] + counts[symb[i-1]]
    occ_counts_before = {}
    for s in symb:
        occ_counts_before[s] = [0] * (len(bwtin) + 1)
    for i in range(len(bwtin)):
        temp = {bwtin[i]: 1}
        for s in symb:
            occ_counts_before[s][i+1] = occ_counts_before[s][i] + temp.get(s, 0)
    return starts, occ_counts_before


def CountOccurrences(str, ptrn, starts, occ_counts_before):
    top = 0
    bottom = len(str) - 1
    while top <= bottom:
        if ptrn:
            symbol = ptrn[-1]
            ptrn = ptrn[:-1]
            top = starts[symbol] + occ_counts_before[symbol][top]
            bottom = starts[symbol] + occ_counts_before[symbol][bottom + 1] - 1
        else:
            return bottom - top + 1
    return 0


if __name__ == '__main__':
    bwtin = input()
    n = int(input())
    patterns = list(input().split())
    starts, occ_counts_before = PreprocessBWT(bwtin)
    for pattern in patterns:
        result = CountOccurrences(bwtin, pattern, starts, occ_counts_before)
        print(result, end=' ')