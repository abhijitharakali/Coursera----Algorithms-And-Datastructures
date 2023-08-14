

def substring_equal(h1,h2,px1,px2,m1,m2,a,b,l):
    sa1 = ((h1[a + l] - px1[l] * h1[a]) % m1)
    sb1 = ((h1[b + l] - px1[l] * h1[b]) % m1)
    sa2 = ((h2[a + l] - px2[l] * h2[a]) % m2)
    sb2 = ((h2[b + l] - px2[l] * h2[b]) % m2)

    if ((sa1 == sb1) and (sa2 == sb2)):
        return True
    else:
        return False


def getpx(m,tl):
    x = 263
    px = [int(1)]
    for j in range(1,tl+1):
        px.append(((px[j-1]*x) % m))
    return px

def geth(text,m,px):
    x = 263
    h = [0]
    for k in range(1,len(text)+1):
        h.append((h[k-1]*x+ord(text[k-1])) % m)
    return h

if __name__ == '__main__':
    text = input().rstrip()
    tl = len(text)
    m1 = int(10**9+7)
    m2 = int(10**9+9)
    px1 = getpx(m1,tl)
    px2 = getpx(m2,tl)

    h1 = geth(text,m1,px1)
    h2 = geth(text,m2,px2)

    q = int(input())
    final_ans = []

    for i in range(q):
        inp = [int(x) for x in input().split()]
        if substring_equal(h1,h2,px1,px2,m1,m2,inp[0],inp[1],inp[2]):
            final_ans.append('Yes')
        else:
            final_ans.append('No')

    for s in final_ans:
        print(s)