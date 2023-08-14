# This code passed all the test cases.

class book:
    def __init__(self,m):
        self.m = m
        intm = self.m
        self.page = []*m
        # print(self.page)
        for i in range(intm):
            self.page.append([])
    def myadd(self,instring,hash_key):
        if instring in self.page[hash_key][:]:
            return
        else:
            self.page[hash_key].insert(0,instring)
    def mydel(self,instring,hash_key):
        while instring in self.page[hash_key]:
            self.page[hash_key].remove(instring)
    def myfind(self,instring,hash_key):
        if instring in self.page[hash_key]:
            return 'yes'
        else:
            return 'no'
    def mycheck(self,hash_key):
        return ' '.join(self.page[hash_key])


def find_hash(in_string,m):
    p = 1000000007
    x = 263
    ret_ans = 0
    char_lst = list(in_string)
    for c in reversed(char_lst):
        sk = ord(c)
        ret_ans = ((ret_ans*x + sk) % p)
    return (ret_ans % m)

if __name__ == '__main__':

    # test_string = 'aaaaaaaaaaaaaaaa'
    # print(find_hash(test_string,100))

    m = int(input())
    mybook = book(m)

    n = int(input())
    final_ans = []
    for i in range(n):
        query = input().split()
        if query[0] == 'check':
            key = int(query[1])
            final_ans.append(mybook.mycheck(key))
        elif query[0] == 'add':
            new_string = query[1]
            key = find_hash(new_string,m)
            mybook.myadd(new_string,key)
        elif query[0] == 'del':
            new_string = query[1]
            key = find_hash(new_string,m)
            mybook.mydel(new_string,key)
        elif query[0] == 'find':
            new_string = query[1]
            key = find_hash(new_string,m)
            final_ans.append(mybook.myfind(new_string,key))
    # print('start')
    if len(final_ans) > 0:
        for j in range(len(final_ans)):
            print(final_ans[j])