from random import choice
from string import ascii_lowercase

def max_mismatches_ref(str1,str2):
    return sum(c1!=c2 for c1,c2 in zip(str1,str2))

def geth(text,m, x):
    h = [0]
    for k in range(1,len(text)+1):
        h.append((h[k-1]*x+ord(text[k-1])) % m)
    return h

def getpx(m,tl,x):
    px = [int(1)]
    for j in range(1,tl+1):
        # temp = pow(x,j,m)
        # px.append(temp)
        px.append(((px[j-1]*x) % m))
    return px

def calc_subhash(h,px,p,low,high):
    i = low
    l = high - low
    return (h[i + l] - px[l] * h[i]) % p

def geth(text,m, x):
    h = [0]
    for k in range(1,len(text)+1):
        h.append((h[k-1]*x+ord(text[k-1])) % m)
    return h

def max_mismatches(h1,h2,px,p,i,pl):
    if pl == 1:
        a1 = calc_subhash(h1, px, p, i, i+pl)
        a2 = h2[1]
        if a1 == a2:
            return 0
        else:
            return 1
    cntk = 0
    low = 0
    ptrh = pl//2
    while(ptrh < pl+1 and low < pl):
        while low != ptrh:
            b1 = calc_subhash(h1, px, p, i+low, i+ptrh)
            b2 = calc_subhash(h2,px,p,low,ptrh)
            if b1 == b2:
                low = ptrh
                ptrh = (pl+ptrh+1)//2
            else:
                ptrh = low + (ptrh-low)//2
        if low == ptrh and ptrh < pl:
            cntk = cntk + 1
            low = low+1
            ptrh = (pl+low+1)//2

    return cntk

if __name__ == '__main__':

    # str1 = input()
    # str2 = input()
    # print(max_mismatches_ref(str1,str2))
    # print(max_mismatches(str1,str2))

    x = 263
    p = 1000000009
    n = 7
    i = n+1
    pl = n
    high = i + pl
    notfound = True
    while notfound:
        str1 = ''.join(choice(ascii_lowercase) for i in range(3*n))
        str2 = ''.join(choice(ascii_lowercase) for i in range(pl))

        h1 = geth(str1,p,x)
        h2 = geth(str2,p,x)
        px = getpx(p,100000,x)
        if max_mismatches(h1,h2,px,p,i,pl) != max_mismatches_ref(str1[i:high],str2):
            print(str1)
            print(str2)
            notfound = False
            break