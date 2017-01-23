# python3

def Fib_lastdig(n):
    if n==0:
        print(0)
    else:
        n=n-1
        a=0
        b=1
        F=1
        while n>0:
            F = (a+b) % 10
            a = b
            b = F
            n = n -1
        print( int(str(F)[-1]))
   

import sys

inp= sys.stdin.read()

n = int(inp)
Fib_lastdig(n)

