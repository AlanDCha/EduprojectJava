package mx.unam.algoritmo;

import java.util.Arrays;
import java.util.Scanner;

public class NoRestriction {
    public static void menu() {
        Scanner cin = new Scanner(System.in);
        // Scanner cin2 = new Scanner(System.in);
        // Scanner choice = new Scanner(System.in);
        int input;
        int[] number = new int[4];
        int num;

        System.out.println("---------------------");
        System.out.println("    No restriction   ");
        System.out.println("---------------------");
        System.out.println("1. I guess the number");
        System.out.println("2. The system guess the number");
        System.out.print("\tChoose your option: ");

        input = cin.nextInt();

        switch (input) {
            case 1:
                int[] to_find = new int[4];
                to_find = MixRandNums.mixRandDigits();
                boolean flag = false;
                byte counter = 1;
                // System.out.println(Arrays.toString(to_find));

                while (counter < 10) {
                    System.out.println("Intent " + counter);
                    System.out.print("Enter the number: ");
                    // Catch the number introduced by user
                    num = cin.nextInt();
                    // Convert to array
                    number = guessNumber(num);
                    flag = Algoritmo.checkNumEquals(to_find, number);
                    Algoritmo.printOut(number);
                    counter++;
                    if (flag) {
                        System.out.println("You did it!!");
                        System.out.println(Arrays.toString(to_find));
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("You couldn't");
                    System.out.println(Arrays.toString(to_find));
                }
                break;
            
            case 2:
                Scanner choice2 = new Scanner(System.in);
                int my_choice;
                System.out.println("1. I decide the number to find");
                System.out.println("2. The system decides the number to find");
                System.out.print("\tChoose your option: ");
                my_choice = choice2.nextInt();
                switch (my_choice) {
                    case 1:
                        int numb;
                        System.out.print("Enter the number: ");
                        numb = cin.nextInt();
                        number = guessNumber(numb);
                        Algoritmo alg = new Algoritmo(number);
                        alg.showAlg();
                        break;
                    
                    case 2:
                        Algoritmo alg2 = new Algoritmo(MixRandNums.
                                                            mixRandDigits());
                        alg2.showAlg();
                        break;
                
                    default:
                        break;
                }
                choice2.close();
                break;
        
            default:
                break;
        }
        cin.close();

    }

    public static int[] guessNumber(int num){
        int[] array = new int[4];
        array[0] =  num / 1000;
        array[1] = (num / 100) % 10;
        array[2] = (num / 10 ) % 10;
        array[3] =  num % 10;
        return array;
    }
}
