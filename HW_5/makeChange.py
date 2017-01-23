

def dpMakeChange(coinValueList, change, minCoins, coinUsed):
    for cents in range(change+1):
        coinCount = cents
        print("\n\nINIT", "\ncoinValueList:", coinValueList, "\ncoinCount (cents):", coinCount, "\nminCoins:", minCoins, "\ncoinUsed:", coinUsed)
        newCoin = 1
        for j in [c for c in coinValueList if c <= cents]:
            print("cents:", cents, ", j: ", j)
            print("minCoins[cents- j]+1 < coinCount:")
            print("    ", minCoins[cents-j]+1, "< ", coinCount)
            if minCoins[cents-j] + 1 < coinCount:
                coinCount = minCoins[cents-j]+1
                newCoin = j
                print("in if minCoins, coinCount:", coinCount, "newCoin:", newCoin)
        minCoins[cents] = coinCount
        coinUsed[cents] = newCoin
        print("END ITER: cents:", cents, ", minCoins:", minCoins, "coinCount:", coinCount, ", coinUsed:", coinUsed, "newCoin", newCoin)
    return minCoins[change]

def printCoins(coinUsed,change):
   coin = change
   while coin > 0:
      thisCoin = coinUsed[coin]
      print(thisCoin)
      coin = coin - thisCoin

def main():
    change = 24
    coinValueList = [1, 20, 8]
    coinUsed = [0]*(change+1)
    minCoins = [0]*(change+1)

    #print("Making change for",amnt,"requires")
    print(dpMakeChange(coinValueList, change, minCoins, coinUsed),"coins")
    #print("They are:")
    printCoins(coinUsed, change)
    #print("The used list is as follows:")
    #print(coinsUsed)

main()
