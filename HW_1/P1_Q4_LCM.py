#python 3
import sys
from decimal import *


def EuclidGCD(a, b):
    if b == 0:
        return a
    else:
        ap = a % b
        #print(ap, b)
        return ( EuclidGCD(b, ap) )
        
def LCM(a, b):
    gcd = EuclidGCD(a, b)
    #print("gcd: ", gcd)
    #print(a*b)
    prod = int(a*b)
    #print(prod)
    lcm = Decimal(prod)/gcd
    #print("lcm: ", lcm)
    return lcm


if __name__=='__main__':
    input = sys.stdin.read()
    a, b = map(int, input.split())
    print(LCM(a, b))
