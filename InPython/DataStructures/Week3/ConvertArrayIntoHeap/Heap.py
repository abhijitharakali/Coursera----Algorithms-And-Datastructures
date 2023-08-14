
n = int(input())
harr = [int(x) for x in input().split()]
assert len(harr) == n
left = []
right = []

cnt  = 0

def build_heap():
    global cnt
    global n
    global harr
    global left
    global right

    if n > 1:
        for i in range((n//2)-1,-1,-1):
            sift(i)
    print(cnt)
    assert len(left) == len(right)
    if(len(left)>0):
        for j in range(len(left)):
            print(str(left[j])+" "+str(right[j]))

def sift(i):

    global cnt
    global harr
    global n
    global left
    global right

    maxindex = i

    l = 2 * i + 1
    if l <= n-1:
        if harr[l] < harr[maxindex]:
            maxindex = l

    r = 2 * i + 2
    if r <= n-1:
        if harr[r] < harr[maxindex]:
            maxindex = r

    if i != maxindex:
        # print(str(i)+" "+str(maxindex))
        left.append(i)
        right.append(maxindex)
        cnt += 1
        temp = harr[i]
        harr[i] = harr[maxindex]
        harr[maxindex] = temp
        sift(maxindex)


if __name__ == '__main__':

    build_heap()