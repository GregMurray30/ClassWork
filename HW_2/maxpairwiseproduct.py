#python 3

import sys

input = sys.stdin.read()
in_arr = input.splitlines()
n = int(in_arr[0])
num_arr = in_arr[1].split()
for i in range(len(num_arr)):
    num_arr[i] = int(num_arr[i])


def MaxPairwiseProduct(n, numbers):
    max_index1 = -1
    for i in range(n):
        if max_index1 == -1 or numbers[i] > numbers[max_index1]:
            max_index1 = i

    max_index2 = -1
    for j in range(len(numbers)):
        if (max_index2 == -1  and  j!=max_index1) or (numbers[j] > numbers[max_index2] and  j!=max_index1): 
            max_index2 = j

    #print(max_index1, max_index2)

    print( ((numbers[max_index1])) * numbers[max_index2])




MaxPairwiseProduct(n, num_arr)
