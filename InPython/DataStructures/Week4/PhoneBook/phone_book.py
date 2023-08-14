if __name__ == '__main__':
    n = int(input()) # input number of queries
    m = 9999999 # m is the maximum integer value that any phone number can attain
    pb = [] # create an empty phone book array which will eventually hold the phone book names

    """ Next, we create an array whose size is equal to one more than maximum integer value that any 
    phone number can attain assuming 0 is allowed. Each entry is initialized to the string 'emptyy' """

    for j in range(m+1):
        pb.append('emptyy')

    final_ans = [] # this is the array which will hold the final responses to the queries made

    for i in range(n):
        query = input().split()
        if query[0] == 'add':                       # If query is 'add', add the name corresponding to the given
            pb[int(query[1])] = query[2]            # phone number in the array pb
        elif query[0] == 'find':                    # If query is 'find', then output the name corresponding to the
            if pb[int(query[1])] != 'emptyy':       # given phone number if the name entry is not 'emptyy'
                final_ans.append(pb[int(query[1])])
            else:
                final_ans.append('not found')       # Else, respond to the query as 'not found'
        elif query[0] == 'del':                     # Finally, if the query is 'del', then replace the name in the given
            pb[int(query[1])] = 'emptyy'            # phone number entry in array pb with its default value 'emptyy'

    if(len(final_ans)) > 0:                         # Print the list of the responses on a new line for each entry
        for k in range(len(final_ans)):
            print(final_ans[k])
