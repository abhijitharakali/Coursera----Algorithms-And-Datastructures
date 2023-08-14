#python3
import random

def max_pairwise_product(numbers):
    n = len(numbers)
    max_product = numbers[0]
    for first in range(n):
        for second in range(first + 1, n):
            max_product = max(max_product,numbers[first] * numbers[second])
    return max_product

def max_product(numbers):
    max1=numbers[0]
    n=len(numbers)
    if(n==1):
        return max1
    else:
        if(numbers[0]<numbers[1]):
            max1=numbers[1]
            max2=numbers[0]
        else:
            max2=numbers[1]
        for i in range(2,n):
            if max1 < numbers[i]:
                max2=max1
                max1=numbers[i]
            elif max2 < numbers[i]:
                max2=numbers[i]
        return max1*max2

if __name__ == '__main__':
    input_n = int(input())
    input_numbers = [int(x) for x in input().split()]
    print(max_product(input_numbers))

# out1=0
# out2=0
#
# while out1 == out2:
#     k=10
#     n=random.randint(2,k)
#     numbers = random.sample(range(0, 1000), n)
#     out1=max_product(numbers)
#     out2=max_pairwise_product(numbers)
#     if(out1!=out2):
#         print(numbers)
#         break