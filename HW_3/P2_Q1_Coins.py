# Uses python3
import sys

def get_change(m):
    d, n, p = 0, 0, 0
    d, m = int(m/10), m%10
    n, m = int(m/5), m%5
    p, m = int(m), int(m)
    coins = d + n + p
    return coins

if __name__ == '__main__':
    m = int(sys.stdin.read())
    print(get_change(m))
    
    
