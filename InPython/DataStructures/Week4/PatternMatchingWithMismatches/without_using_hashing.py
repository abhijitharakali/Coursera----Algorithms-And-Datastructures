# Python3
# This code fails test #6 out of 7, due to time limit exceed. I suppose using hashing is mandatory.
# This code does not use hashing

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
    return (((h[i + l] - px[l] * h[i]) % p))

def max_matches(text,pattern,k,x,p,px):
    #1. Find the hash values of text and pattern
    #2. Fix position i in text. Set length to range (low, mid, high) to 0,len(pattern)//2,len(pattern) and then
    # binary search for first mismatch. Find the rest of the mismatches and stop if it exceeds k.
    #3. Append nothing, if number of mismatches exceeds k, and append i if number of mismatches is less than k.
    # hasht = geth(text,p,x)
    # hashp = geth(pattern,p,x)
    ret_ans = []
    for i in range(len(text)-len(pattern)+1):
        s1 = text[i:i+len(pattern)]
        s2 = pattern
        if sum(c1!=c2 for c1,c2 in zip(s1,s2)) <= k:
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