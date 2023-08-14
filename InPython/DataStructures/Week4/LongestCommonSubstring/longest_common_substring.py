def calc_fullhash(text,m):
    x = int(263)
    ret_ans = int(0)
    for j in reversed(range(len(text))):
        ret_ans = (ret_ans*x + ord(text[j])) % m
    return ret_ans

def calc_hash(text,pl,m):
    x = 263
    h = []
    h.append(calc_fullhash(text[len(text)-pl:len(text)],m))
    xm = int(1)
    for k in range(pl):
        xm = (xm * x) % m
    cnt = 0
    for l in reversed(range(len(text) - pl)):
        temp = (x * h[cnt] + ord(text[l]) - xm * ord(text[l + pl])) % m
        h.append(temp)
        cnt = cnt + 1
    h.reverse()
    return h

def calc_posn(h1_m1,h2_m1,h1_m2,h2_m2):
    p1 = -1
    p2 = -1
    found = False
    for i in range(len(h1_m1)):
        for j in range(len(h2_m2)):
            if((h1_m1[i] == h2_m1[j]) and (h1_m2[i] == h2_m2[j])):
                p1 = i
                p2 = j
                found = True
                break
            else:
                continue
        if found:
            break
    return [p1 , p2]

if __name__ == '__main__':
    final_ans = []

    while True:
        try:
            line = input()
        except EOFError:
            break
        if line:
            str1, str2 = line.split()

            high = min(len(str1),len(str2))
            low = 0
            m1 = 1000000000+7
            m2 = 1000000000+9
            p1 = -1
            p2 = -1
            p1_last = p1
            p2_last = p2
            length = 0

            while low <= high:
                mid = (low + high) // 2

                h1_m1 = calc_hash(str1,mid,m1)
                h1_m2 = calc_hash(str1,mid,m2)
                h2_m1 = calc_hash(str2,mid,m1)
                h2_m2 = calc_hash(str2,mid,m2)

                posns = calc_posn(h1_m1,h2_m1,h1_m2,h2_m2)


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