# Uses python3
import sys

def binary_search(a, lo, hi, x):
    if x<a[0] or x>a[-1] :
        return -1
    if x==a[hi]:
        return hi
    mid = int((lo+hi)/2)
    #print("\na: ", a, 'lo: ', lo, 'hi: ', hi, 'mid: ', mid)
    if x==a[mid]:
        return mid
    if hi-lo==1:
        return -1
    elif x<a[mid]:
        hi = mid
    elif x>a[mid]:
        lo = mid
    return binary_search(a, lo, hi, x)


def linear_search(a, x):
    for i in range(len(a)):
        if a[i] == x:
            return i
    return -1

if __name__ == '__main__':
    input = sys.stdin.read()
    data = list(map(int, input.split()))
    #print(data)

    n = data[0]
    m = data[n + 1]
    a = data[1 : n + 1]
    lo, hi = 0, len(a)-1
    for x in data[n + 2:]:
        print(binary_search(a, lo, hi, x), end=' ')
