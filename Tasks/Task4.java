package Tasks;
import java.util.*;
public class Task4 {
    static StringBuilder sb = new StringBuilder();

    public static void DrinkFromBarrels(List<Integer> slaves, int[] arr, int first, int last, List<Integer> barrels) {
        if (first < last ) {
            int step = (last - first + 1) / slaves.size();
            for (int i = 0; i < slaves.size(); i++) {
                int begin = first + step * i;
                int end = begin + step - 1;
                if (i == slaves.size() - 1) {
                    end = last;
                }
                // make each slave drink the given mix from begin to end
                DrinkProcess(slaves.get(i), arr, begin, end, barrels);
                // recursively make the slaves drink from the remaining untested barrels
                DrinkFromBarrels(slaves, arr, begin, end, barrels);
            }
        }
    }

    public static int findPoisonedBarrel(List<Integer> slaves, int[] arr) {
        List<Integer> barrels = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            barrels.add(i);
        }
        DrinkFromBarrels(slaves, arr, 0, arr.length - 1, barrels);
        return Integer.parseInt(sb.toString());
    }

    /* function to show who drinks from what and know who dies from the slaves and who survives */
    public static void DrinkProcess(int slaveNo, int[] arr, int start, int end, List<Integer> barrels) {
        for (int i = start; i <= end; i++) {
            if (arr[i] == 1) {
                //System.out.println("Slave " + slaveIndex + " drinks from barrel " + i + " and dies");
                sb.append(slaveNo-1);
                return;
            }
        }
        //System.out.println("Slave " + slaveIndex + " drinks from barrels " + start + "-" + end + " and survives");
    }

    public static void main(String[] args) {
        // create a list of slaves ( 10 slaves )
        List<Integer> slaves = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            slaves.add(i);
        }
        // create an array of wine barrels (all of them are 0's except the poisoned one will be marked as 1)
        int[] arr = new int[1000];
        int poisonedIndex = (int) (Math.random() * 1000); //random generation of the poisoned barrel
        arr[poisonedIndex] = 1;
        System.out.println("The poisoned barrel is at index ( random generator ) is " + poisonedIndex);

        int result = findPoisonedBarrel(slaves, arr);
        System.out.println("The poisoned barrel is at index ( from the find poisoned barrel function ) is " + result);
    }

}
