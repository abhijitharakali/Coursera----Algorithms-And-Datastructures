# Python3

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

if __name__ == '__main__':
    x = 263
    p = 1000000000 + 9
    strin = 'abhijitharakali'
    low = 3
    high = 4
    tl = 100000
    px = getpx(p,tl,x)
    print(geth(strin[low:high],p,x))
    hashin = geth(strin,p,x)
    print(calc_subhash(hashin,px,p,low,high))