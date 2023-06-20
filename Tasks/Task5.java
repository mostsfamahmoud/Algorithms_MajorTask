package Tasks;
import java.util.Arrays;
public class Task5 {
    public static void main(String[] args) {
        int n = 4;
        // Check if n is divisible by 4
        if (n % 4 != 0) {
            System.out.println("Cannot solve the puzzle for n = " + n + ". n must be divisible by 4.");
            return;
        }
        initializeCoins(n);

        for (int i = 1; i <= n/4 - 1; i++) {
            int rightmostSingleCoin = findRightmostSingleCoinWithIcoinsToItsLeft(n-i);
            if (rightmostSingleCoin!= -1){
                jumpCoin(rightmostSingleCoin- i -1 - countEmptySpacesToTheLeft(rightmostSingleCoin),rightmostSingleCoin);
            }
        }

        for (int i = n/4; i <= n/2; i++) {

            int leftmostSingleCoin = i - n/4;
            if (leftmostSingleCoin != -1){
                int coinscount =0;
                int extraindex = 0;
                for (int v =leftmostSingleCoin+1; v<n;v++){
                    if (coinscount + coins[v] <= i){
                        coinscount = coinscount + coins[v];
                        extraindex ++;
                    }
                    else{break;}
                }
                jumpCoin(leftmostSingleCoin, leftmostSingleCoin+extraindex+1);

            }
        }

        System.out.println("Final state of the puzzle with " + n + " coins paired: " + Arrays.toString(coins));
        System.out.println("With min number of moves equals: " + nocount);
    }



    private static int[] coins;
    private static int nocount =0;

    private static void initializeCoins(int n) {
        coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = 1;
        }
    }

    private static int findRightmostSingleCoinWithIcoinsToItsLeft(int i) {
        for (int j = i; j >= 0; j--) {
            if (coins[j]==1 ) {
                return j;
            }
        }
        return -1; // Error: cannot find a rightmost single coin with I coins to its left
    }

    private static int countEmptySpacesToTheLeft(int index) {
        int count = 0;
        for (int i = index-1; i >= 0; i--) {
            if (coins[i] == 0) {
                count++;
            }
        }
        return count;
    }

    private static void jumpCoin(int fromIndex, int toIndex) {

        coins[toIndex] = 2;
        coins[fromIndex] = 0;
        nocount ++;
    }

}
