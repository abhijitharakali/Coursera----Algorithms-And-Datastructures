from random import choice
from string import ascii_lowercase

def max_mismatches_ref(str1,str2):
    return sum(c1!=c2 for c1,c2 in zip(str1,str2))

def geth(text,m, x):
    h = [0]
    for k in range(1,len(text)+1):
        h.append((h[k-1]*x+ord(text[k-1])) % m)
    return h

def max_mismatches(str1,str2):
    if len(str1) == 1:
        if str1 == str2:
            return 0
        else:
            return 1
    cntk = 0
    low = 0
    pl = len(str2)
    ptrh = pl//2
    x = 263
    p = 1000000000 + 9
    while(ptrh < pl+1 and low < pl):
        while low != ptrh:
            if geth(str1[low:ptrh],p,x) == geth(str2[low:ptrh],p,x):
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

    n = 2
    notfound = True
    while notfound:
        str1 = ''.join(choice(ascii_lowercase) for i in range(n))
        str2 = ''.join(choice(ascii_lowercase) for i in range(n))
        if max_mismatches(str1,str2) != max_mismatches_ref(str1,str2):
            print(str1)
            print(str2)
            notfound = False
            break