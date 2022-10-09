package mx.unam.algoritmo;

import java.util.ArrayList;
// import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MixRandNums {
    
    private static final int number = 10000;
    private static final int number2 = 10;

    public static int[] mixRandDigits(){
        Random rand = new Random();
        int[] numero = new int[4];

        int my_number = rand.nextInt(number);
        numero[0] = my_number / 1000;
        numero[1] = (my_number / 100) % 10;
        numero[2] = (my_number / 10 ) % 10;
        numero[3] = my_number % 10;

        return numero;
    }

    public static int[] mixDiffRandDigits(){
        Random rand = new Random();
        int[] num = new int[4];
        int i = 0;
        boolean flag = false;
        while (i < num.length) {
            flag = false;
            int my_number = rand.nextInt(number2);
            if (i != 0){
                for (int j = i; j >= 0; j++) {
                    if (num[j] == my_number){
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    num[i] = my_number;
                    i++;
                }
            } else {
                num[i] = my_number;
            }
        }
        return num;
    }

    public static int[] mixDiffRandDigits(int[] array){
        int[] modified = new int[4];
        Random rnd = new Random();
        int i = 0;
        boolean flag = false;
        int intent;

        while (i < 4) {
            flag = false;
            intent = rnd.nextInt(number2);
            if (!contains(array, intent)){
                for (int j = i; j >= 0; j++) {
                    if (modified[j] == intent){
                        flag = true;
                        break;
                    }
                }
                if (!flag){
                    modified[i] = intent;
                    i++;
                }
            }
        }
        return modified;
    }

    public static int[] mixDiffRandDigits(ArrayList<Integer> dropped){
        // This is the new modified array 
        int[] modified = new int[4];
        // object of Random class
        Random rnd = new Random();
        // Each loop will compare with dropped array
        int test;
        // Counter
        int i = 0;
        while (i < 4){
            test = rnd.nextInt(number2);
            if (dropped.contains(test)){
                modified[i] = test;
                i++;
            }
        }

        return modified;
    }

    public static int[] mixDiffRandDigits(ArrayList<Integer> dropped, 
        int[][] matrix, int[] right, int[] wrong){
        int[] modified = new int[4];
        // TODO: Complete the function
        // Taking care of array previous  and (R, W)
        return modified;
    }

    public static boolean contains(final int[] arr, final int key){
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }

    public static int[] sortDigits(int[][] matrix, ArrayList<Integer> p_pinned){
        int[] modified = new int[4];
        // TODO: Complete the function
        // sort the digits taking care of matrix
        return modified;
    }

}