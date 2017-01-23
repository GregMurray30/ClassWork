#python 3

import sys
import math

    

def fibSumLastDig(n):
    sqf = math.sqrt(5)
    S = ((math.pow((1+sqf),n))-(math.pow((1-sqf),n)))/((math.pow(2,n))*sqf)
    return int(S+1)
    

if __name__=='__main__':
    input= int(sys.stdin.read())
    print(fibSumLastDig(input+2))

