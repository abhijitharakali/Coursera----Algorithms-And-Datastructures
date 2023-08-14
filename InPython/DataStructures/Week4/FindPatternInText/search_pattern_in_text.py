def calc_phash(pattern):
    p = int(1000000007)
    x = int(263)
    ret_ans = int(0)
    for j in reversed(range(len(pattern))):
        ret_ans = (ret_ans*x + ord(pattern[j])) % p
    return ret_ans

def calc_thash(text,pl):
    p = int(1000000007)
    x = int(263)
    thash = []
    thash.append(calc_phash(text[len(text)-pl:len(text)]))
    xp = int(1)
    for k in range(pl):
        xp = (xp * x) % p
    cnt = 0
    for l in reversed(range(len(text)-pl)):
        temp = (x*thash[cnt]+ord(text[l])-xp*ord(text[l+pl])) % p
        thash.append(temp)
        cnt = cnt + 1
    # print(thash.reverse())
    thash.reverse()
    return thash

if __name__ == '__main__':
    pattern = input().rstrip()
    text = input().rstrip()
    final_ans =[]
    pl = len(pattern)
    thash = calc_thash(text,pl)
    phash = calc_phash(pattern)
    for i in range(len(text)-len(pattern)+1):
        # print(i)
        if thash[i] != phash:
            continue
        elif text[i:i+len(pattern)] == pattern:
                final_ans.append(i)
    # print(phash)
    # print(thash)
    if len(final_ans) > 0:
        print(' '.join(str(h) for h in final_ans))


