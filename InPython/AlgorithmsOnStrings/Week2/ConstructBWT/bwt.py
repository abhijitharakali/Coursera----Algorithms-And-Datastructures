# python3

def BWT(s):
    m = [s]
    t = s
    n = len(s)
    for i in range(1,n):
        t = t[n-1] + t[:n-1]
        m.append(t)
    m.sort()
    ans = ''
    for r in m:
        ans += r[n-1]
    return ans

if __name__ == '__main__':
    bwtin = input()
    print(BWT(bwtin))