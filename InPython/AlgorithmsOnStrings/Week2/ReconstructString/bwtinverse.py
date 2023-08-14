# python3

def SMAP(s):
    cnts = {'$': 0, "A": 0, 'C': 0, 'G': 0, 'T': 0}
    for char in s:
        cnts[char] += 1
    pntr = -1
    posn = {}
    for c in ['$', 'A', 'C', 'G', 'T']:
        pntr += cnts[c]
        posn[c] = pntr
    ans = [0] * len(s)
    for i in range(len(s)-1, -1, -1):
        ans[i] = posn[s[i]]
        posn[s[i]] -= 1
    return ans

def InverseBWT(s):
    smap = SMAP(s)
    ans = '$'
    posn = 0
    n = len(s)
    for i in range(n-1):
        ans += s[posn]
        posn = smap[posn]
    ans = ans[::-1]
    return ans

if __name__ == '__main__':
    bwt = input()
    print(InverseBWT(bwt))