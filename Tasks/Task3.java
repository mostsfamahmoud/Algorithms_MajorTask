package Tasks;
import java.util.Scanner;
public class Task3 {
    public static int securitySwitches_minimumMoves(int switches) {
        int result[] = new int[switches];
        result[0] = 1; result[1] = 2;
        for (int i = 2;i < switches;i++)
            result[i] = result[i-2] + 1 + result[i-2] + result[i-1];
        return result[switches-1];
    }
    public static void main(String[] args) {
        int switches = 1;
        while (true) {
            System.out.print("Enter number of switches:");
            Scanner input = new Scanner(System.in);
            switches = input.nextInt();
            if(switches == 0) break;
            System.out.println(
                    "Minimum number of moves for"
                            + switches +
                            "switches = "
                            + securitySwitches_minimumMoves(switches));
        }
    }

}
