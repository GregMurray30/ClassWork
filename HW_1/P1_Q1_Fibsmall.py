# python3
import sys

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
            

if __name__=='__main__':
    input= sys.stdin.read()
    n = int(input)
    print(Fib(n))

