# Uses python3
import sys
from operator import itemgetter

def get_optimal_value(capacity, weights, values):
    value = 0.
    #print(capacity, values, weights)
    val_per_wts = []
    for i in range(len(values)):
        vpw = values[i]/weights[i]
        val_per_wts.append(vpw)

    vw_vpw = zip(values, weights, val_per_wts)
    #for a, b, c in v_p_vpw:
        #print(a, b, c)
    #print(v_p_vpw)
    vw_vpw = sorted(vw_vpw, key=itemgetter(2), reverse=True)
    #print(vw_vpw)

    ctr = 0
    while capacity>0:
        #print("ctr: ", ctr)
        for i in range(len(vw_vpw)):
            #print("i: ", i)
            if capacity<vw_vpw[i][1]:
                value +=(capacity/vw_vpw[i][1])*vw_vpw[i][0]
                capacity -=vw_vpw[i][1]
                #print("capacity<weight; ", "cap: ", capacity, "val: ", value)
                break
            else:
                value +=(vw_vpw[i][1])*(vw_vpw[i][2])
                capacity -=vw_vpw[i][1]
            ctr +=1
            if  ctr>=len(vw_vpw):
                return value
                
    return value


if __name__ == "__main__":
    data = list(map(int, sys.stdin.read().split()))
    n, capacity = data[0:2]
    values = data[2:(2 * n + 2):2]
    weights = data[3:(2 * n + 2):2]
    opt_value = get_optimal_value(capacity, weights, values)
    print("{:.10f}".format(opt_value))
