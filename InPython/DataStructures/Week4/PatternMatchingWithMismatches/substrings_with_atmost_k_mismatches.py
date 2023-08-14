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

def calc_subhash(h,px,p,i,l):
    return (h[i + l] - px[l] * h[i]) % p

def calc_subhash_list(h,px,m,l):
    ret_ans = []
    for i in range(len(h)-l):
        ret_ans.append(((h[i + l] - px[l] * h[i]) % m))
    return ret_ans

def num_mismatches_lessthank(ht,hp,k,x,p,px):
    cntk = 0
    low = 0
    pl = len(hp)
    ptrl = 0
    ptrh = pl//2
    while(ptrh < len(hp)):
        while ptrl != ptrh:
            l = len(hp[low:ptrh])
            if calc_subhash(ht,px,p,low,l) == calc_subhash(hp,px,p,low,l):
                ptrl = ptrh
                ptrh = (pl+ptrh)//2
            else:
                ptrh = ptrl + (ptrh-ptrl)//2
        cntk = cntk+1
        if cntk > k:
            return False
        low = ptrl+1
        if low >= pl:
            return True
        ptrl = ptrl+1
        ptrh = ptrl +(pl-ptrl)//2
    return True


def max_matches(text,pattern,k,x,p,px):
    #1. Find the hash values of text and pattern
    #2. Fix position i in text. Set length to range (low, mid, high) to 0,len(pattern)//2,len(pattern) and then
    # binary search for first mismatch. Find the rest of the mismatches and stop if it exceeds k.
    #3. Append nothing, if number of mismatches exceeds k, and append i if number of mismatches is less than k.
    hashp = geth(pattern,p,x)
    ret_ans = []
    for i in range(len(text)-len(pattern)+1):
        hashti = geth(text[i:i+len(pattern)],p,x)
        if num_mismatches_lessthank(hashti,hashp,k,x,p,px):
            ret_ans.append(i)
    return ret_ans

if __name__ == '__main__':
    final_ans = []
    x = 263
    p = 1000000000 + 9
    px = getpx(p, 100000, x)
    while True:
        try:
            line = input()
        except EOFError:
            break
        if line:
            temp, text, pattern = line.split()
            k = int(temp)
            max_mm = max_matches(text,pattern,k,x,p,px)
            if len(max_mm) > 0:
                final_ans.append(str(len(max_mm))+' '+' '.join(str(x) for x in max_mm))
            else:
                final_ans.append(0)
        else:
            break

    for s in final_ans:
        print(s)