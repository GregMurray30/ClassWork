#python 3

def EuclidGCD(a, b):
    if b == 0:
        return a
    else:
        ap = a % b
        #print(ap, b)
        return ( EuclidGCD(b, ap) )
        

import sys

inp = (sys.stdin.read()).split()
print(EuclidGCD(int(inp[0]), int(inp[1])))
