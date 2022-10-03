package mx.unam.algoritmo;

import java.util.Arrays;
import java.util.Random;

public class GenerateRand {
    
    private static int number = 10000;
    private static int number2 = 10;

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
}
