package mx.unam.algoritmo;

import java.util.Arrays;
import java.util.Random;

public class GenerateRandNumbers {
    
    private static final int number = 10000;
    private static final int number2 = 10;

    public static int[] generateRandom(){
        Random rand = new Random();
        int[] numero = new int[4];

        int my_number = rand.nextInt(number);
        numero[0] = my_number / 1000;
        numero[1] = (my_number / 100) % 10;
        numero[2] = (my_number / 10 ) % 10;
        numero[3] = my_number % 10;

        return numero;
    }

    public static int[] generateDiffRandNumbers(){
        Random rand = new Random();
        int[] numero = new int[4];
        for (int i = 0; i < 4; i++) {
            int my_number = rand.nextInt(number2);
            if (i != 0){
                if (numero[i - 1] == my_number)
                i--;
                else {
                    numero[i] = my_number;
                }
            }
        }
        return numero;
    }

    public static int genOneDigitRand() {
        return new Random().nextInt(number2);
    }

    public static boolean contains(final int[] arr, final int key){
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }

    public static byte repeatedElementsArray(final int[] arr, final int key) {
        byte counter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key)
            counter++;
        } 
        return counter;
    }

    public static int getRandom(int[] array){
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    public static int[] pickRandNumExceptArray(int[] array) {
        final int stop = 10;
        int[] number_picked = new int[4];
        Random rnd = new Random();
        byte count = 0;
        while (count < 4) {
            int testing = rnd.nextInt(stop);
            for (int i = 0; i < array.length; i++) {
                if (array[i] != testing) {
                    number_picked[i] = testing;
                    count++;
                }
            }
        }
        return number_picked;
    }

    public static int[] generateThreeDigit(int[] array, int picked, int index){
        // * rnd is for the new index
        Random rnd = new Random();
        // * rnd2 is for each digit generated
        Random rnd2 = new Random();
        // * Save the new index
        int new_index;
        // * The new number
        int[] comparing_modified = new int[4];
        // * counter
        int i = 0;
        // * Flag
        boolean flag;
        // * digit
        int digit;
        // * Creating the new index for the picked number. No index token.
        
        do {
            new_index = rnd.nextInt(array.length);
        } while (new_index != index);
        
        while (i < 4){
            flag = true;
            if (i == new_index) {
                comparing_modified[i] = picked;
                i++;
            } else {
                while (true) {
                    digit = rnd2.nextInt(number2);
                    flag = contains(array, digit);
                    if (flag) {
                        comparing_modified[i] = digit;
                        break;
                    }
                }
                i++;
            }
        }
        return comparing_modified;
    }
}
