# This code has a bug and not sure where. I did not bother to investigate further
# The code in processing_strings_using_lists avoids the use of nodes
# Avoiding nodes removed the bug and passes all the test cases

class Node:

    def __init__(self, data):
        self.data = data
        self.next = None
        self.prev = None

class dll:
    def __init__(self):
        self.head = None
    def push(self, val):
        tempnode = Node(val)
        tempnode.next = self.head
        if self.head is not None:
            self.head.prev = tempnode
        self.head = tempnode
    def del_string(self,new_string):
        if self.head is None:
            return
        tempnode = self.head
        while tempnode is not None:
            if tempnode.data == new_string:
                if tempnode.prev is None:
                    self.head = tempnode.next
                    tempnode = self.head
                else:
                    last = tempnode.prev
                    last.next = tempnode.next
                    tempnode = tempnode.next
            else:
                tempnode = tempnode.next

            # if tempnode.data == new_string:
            #     if tempnode == self.head:
            #         self.head = tempnode.next
            #     else:
            #         last = tempnode.prev
            #         if last is not None:
            #             last.next = tempnode.next
            #             tempnode = tempnode.next
            #         else:
            #
            # else:
            #     tempnode = tempnode.next
    def get_entries(self):
        if self.head is None:
            return ''
        ret_lst = []
        cur_node = self.head
        while cur_node is not None:
            ret_lst.append(cur_node.data)
            cur_node = cur_node.next
        return ' '.join(ret_lst)
    def find_string(self,mystring):
        if self.head is None:
            return 'no'
        cur_node = self.head
        while cur_node is not None:
            if cur_node.data == mystring:
                return 'yes'
            cur_node = cur_node.next
        return 'no'

# def write_dll(mydll):
#     if mydll.head is None:
#         return ''
#     ret_lst = []
#     cur_node = mydll.head
#     while cur_node is not None:
#         ret_lst.append(cur_node.data)
#         last = cur_node
#         cur_node = cur_node.next
#     return ' '.join(ret_lst)

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

    test_string = 'aaaaaaaaaaaaaaaa'
    print(find_hash(test_string,100))

    # m = int(input())
    # book = []
    # for j in range(m):
    #     tempdll = dll()
    #     book.append(tempdll)
    #
    # n = int(input())
    # final_ans = []
    # for i in range(n):
    #     query = input().split()
    #     if query[0] == 'check':
    #         key = int(query[1])
    #         final_ans.append(book[key].get_entries())
    #     elif query[0] == 'add':
    #         new_string = query[1]
    #         key = find_hash(new_string,m)
    #         if book[key].find_string(new_string) == 'no':
    #             book[key].push(new_string)
    #     elif query[0] == 'del':
    #         new_string = query[1]
    #         key = find_hash(new_string,m)
    #         book[key].del_string(new_string)
    #     elif query[0] == 'find':
    #         new_string = query[1]
    #         key = find_hash(new_string,m)
    #         final_ans.append(book[key].find_string(new_string))
    # # print('start')
    # if len(final_ans) > 0:
    #     for j in range(len(final_ans)):
    #         print(final_ans[j])