package Tasks;
import java.util.Random;
import java.util.Scanner;
public class Task7 {
    public static void ShootTarget (int spots, int target) {
        Random random = new Random();
        int step;
        int trial=0;
        for (int i = 1; i <= spots; i++)
        {
            ++trial;
            step = random.nextInt(2) * 2 - 1;
            if(i==target || (i+1 == target))
            {
                if(i==target){
                    System.out.println("Trial "+trial +" | Hits :" + i + " | shoot succeeded at -> " + i);
                    System.out.println("you hit the target in spot "+(i) +" in "+trial+" trials");
                }
                else{
                    System.out.println("Trial "+trial + " | Hits :" + i + " | "+ "target still to hide in :"+target);
                    trial++;
                    System.out.println("Trial "+trial +" | Hits :" + (i+1) +" | shoot succeeded at -> " + (i+1));
                    System.out.println("you hit the target in spot "+(i+1) +" in "+trial+" trials");
                }
                return;
            }
            System.out.println("Trial "+trial +" | Hits :" + i + " | "+ "target still to hide in :"+target);
            if(target == 1)
            {
                target++;
            }
            else if(target == spots)
            {
                target--;
            }
            else
            {
                target+=step;
            }
            trial++;
            System.out.println("Trial "+trial +" | Hits :" + (i+1) +" | "+ "target just moved to hide in :"+target);
        }
        System.out.println("Failed to shoot the target");;
    }
    public static void main(String[] args) {
        System.out.print("Enter number of spots:");
        Scanner input = new Scanner(System.in);
        int spots = input.nextInt();
        Random random = new Random();
        int target= random.nextInt(spots)+1 ;
        ShootTarget(spots,target);
    }
}
