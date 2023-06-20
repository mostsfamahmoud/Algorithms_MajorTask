package Tasks;
import java.util.Arrays;
public class Task6 {
    public static void main(String[] args) {
        int[] coins = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
        String result = findFakeCoin(coins);
        System.out.println(result);
    }

    public static String findFakeCoin(int[] coinWeights) {
        int n = coinWeights.length;

        // Initialize the dynamic programming table
        // dp[i][j] represents the result of weighing coins i and j
        char[][] dp = new char[n][n];

        // Iterate through all possible combinations of coins
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Case 1: Fake coin is lighter
                if (i > 0 && coinWeights[i] < coinWeights[j]) {
                    dp[i][j] = '<';
                }
                // Case 2: Fake coin is heavier
                else if (j > 0 && coinWeights[i] > coinWeights[j]) {
                    dp[i][j] = '>';
                }
                // Case 3: Fake coin is not in the current weighing
                else if (i > 0 && j > 0) {
                    dp[i][j] = '=';
                }
            }
        }

        char[] sign = dp[1];
        String result = checkSign(sign);
        int index = checkIndex(sign);
        if (result != null) {
            if (result.equals(">")) {
                return String.format("(%d, lighter)", index);
            } else if (result.equals("<")) {
                return String.format("(%d, heavier)", index);
            }
        } else {
            return "there is no fake coin";
        }
        return null;
    }

    public static String checkSign(char[] array) {
        char previousSign = '\0';

        for (char sign : array) {
            if (previousSign != '\0' && sign != previousSign) {
                return Character.toString(sign);
            }
            previousSign = sign;
        }
        return null;
    }

    public static int checkIndex(char[] array) {
        char previousSign = '\0';

        for (int i = 0; i < array.length; i++) {
            char sign = array[i];
            if (previousSign != '\0' && sign != previousSign) {
                return i;
            }
            previousSign = sign;
        }
        return -1;
    }


}
