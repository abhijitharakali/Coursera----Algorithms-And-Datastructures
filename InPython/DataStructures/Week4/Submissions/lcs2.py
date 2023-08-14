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

def calc_subhash(h,px,m,l):
    ret_ans = []
    for i in range(len(h)-l):
        ret_ans.append(((h[i + l] - px[l] * h[i]) % m))
    return ret_ans

# def calc_posn(h1_m1,h2_m1,h1_m2,h2_m2,mid):
def calc_posn(h1_m1,h2_m1,h1_m2,h2_m2,px1,px2,mid,x):
    m1 = 1000000000 + 7
    m2 = 1000000000 + 9
    p1 = -1
    p2 = -1
    found = False
    hash1_m1 = calc_subhash(h1_m1,px1,m1,mid)
    hash1_m2 = calc_subhash(h1_m2,px2,m2,mid)
    hash2_m1 = calc_subhash(h2_m1,px1,m1,mid)
    hash2_m2 = calc_subhash(h2_m2,px2,m2,mid)
    both_m1 = set(hash1_m1).intersection(hash2_m1)
    both_m2 = set(hash1_m2).intersection(hash2_m2)
    if len(both_m1) == 0 or len(both_m2) == 0:
        return [p1, p2]
    else:
        ind1_m1 = [hash1_m1.index(x) for x in both_m1]
        # ind2_m1 = [hash2_m1.index(x) for x in both_m1]
        ind1_m2 = [hash1_m2.index(x) for x in both_m2]
        # ind2_m2 = [hash2_m2.index(x) for x in both_m2]
        ind1_both = set(ind1_m1).intersection(ind1_m2)
        # ind2_both = set(ind2_m1).intersection(ind2_m2)
        # if len(ind1_both) == 0 or len(ind2_both) == 0:
        if len(ind1_both) == 0:
            return [p1, p2]
        else:
            p1 = ind1_both.pop()
            temph = hash1_m1[p1]
            for l in range(len(hash2_m1)):
                if temph == hash2_m1[l]:
                    p2 = l
                    break
            # p2 = ind2_both.pop()
            return [p1, p2]
    # for i in range(len(h1_m1)-mid):
    #     for j in range(len(h2_m2)-mid):
    #         s1_m1 = ((h1_m1[i + mid] - px1[mid] * h1_m1[i]) % m1)
    #         s1_m2 = ((h1_m2[i + mid] - px2[mid] * h1_m2[i]) % m2)
    #         s2_m1 = ((h2_m1[j + mid] - px1[mid] * h2_m1[j]) % m1)
    #         s2_m2 = ((h2_m2[j + mid] - px2[mid] * h2_m2[j]) % m2)
    #         # s1_m1 = ((h1_m1[i + mid] - pow(x,mid,m1) * h1_m1[i]) % m1)
    #         # s1_m2 = ((h1_m2[i + mid] - pow(x,mid,m2) * h1_m2[i]) % m2)
    #         # s2_m1 = ((h2_m1[j + mid] - pow(x,mid,m1) * h2_m1[j]) % m1)
    #         # s2_m2 = ((h2_m2[j + mid] - pow(x,mid,m2) * h2_m2[j]) % m2)
    #         if((s1_m1 == s2_m1) and (s1_m2 == s2_m2)):
    #             p1 = i
    #             p2 = j
    #             found = True
    #             break
    #         else:
    #             continue
    #     if found:
    #         break
    # return [p1 , p2]

if __name__ == '__main__':
    final_ans = []
    x = 263
    m1 = 1000000000 + 7
    m2 = 1000000000 + 9
    px1 = getpx(m1, 100000, x)
    px2 = getpx(m2, 100000, x)

    while True:
        try:
            line = input()
        except EOFError:
            break
        if line:

            str1, str2 = line.split()
            high = min(len(str1),len(str2))
            low = 0
            tl = max(len(str1),len(str2))


            p1 = -1
            p2 = -1
            p1_last = p1
            p2_last = p2
            length = 0
            h1_m1 = geth(str1, m1, x)
            h1_m2 = geth(str1, m2, x)
            h2_m1 = geth(str2, m1, x)
            h2_m2 = geth(str2, m2, x)

            while low <= high:
                mid = (low + high) // 2

                posns = calc_posn(h1_m1,h2_m1,h1_m2,h2_m2,px1,px2,mid,x)
                # posns = calc_posn(h1_m1, h2_m1, h1_m2, h2_m2, mid)

                p1 = posns[0]
                p2 = posns[1]

                if p1 == -1:
                    high = mid - 1
                else:
                    p1_last = p1
                    p2_last = p2
                    length = mid
                    low = mid + 1
            if p1_last == -1:
                final_ans.append((str(0)+' '+str(0)+' '+str(0)))
            else:
                final_ans.append((str(p1_last)+' '+str(p2_last)+' '+str(length)))

        else:
            break


    for s in final_ans:
        print(s)