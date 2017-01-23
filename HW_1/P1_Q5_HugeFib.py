#python 3
import sys
import math
from decimal import *

LIMIT = 3000
def Fib(n):
    if n==0:
        return 0
    else:
        n=n-1
        a=0
        b=1
        F=1
        while n>0:
            F = a+b
            a = b
            b = F
            n = n -1
        return F

def GEN_FibSeq():
    a, b = 0, 1
    yield 0
    while True:
        a, b = b, a+b
        yield a


def modFib(lim, m):
    fib_mod_arr = []
    fib_seq = GEN_FibSeq()

    for i in range(lim):
        fib_mod_arr.append(next(fib_seq)%m)

    return fib_mod_arr


def perLen(arr):
    per = 1
    #print(len(arr))
    for i in range(1, len(arr)):
        if arr[i] == arr[0]:
            if arr[:i] == arr[i:i+i]:
                return per
        per +=1
    return None
    

def hugeFib(n, m):
    fib_mods=modFib(LIMIT, m)
    per = perLen(fib_mods)
    if per:
        k=n%per
        F = Fib(k)
        FnModm= F%m
    if not per:
        F = Fib(n)
        FnModm=F%m
    #print("k: ", k)
    
    #print("Fib(k): ", F)
    
    return FnModm
    
if __name__=='__main__':
    input= sys.stdin.read()
    n, m = map(int, input.split()) 
    print(hugeFib(n, m))

