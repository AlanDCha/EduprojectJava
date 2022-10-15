package mx.unam.algoritmo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MixRandNums {
    
    private static final int TEN_THOUSAND = 10000;
    private static final int TEN = 10;

    public static int[] mixRandDigits(){
        Random rand = new Random();
        int[] numero = new int[4];
        int my_number = rand.nextInt(TEN_THOUSAND);
        numero[0] = my_number / 1000;
        numero[1] = (my_number / 100) % 10;
        numero[2] = (my_number / 10 ) % 10;
        numero[3] = my_number % 10;
        return numero;
    }

    public static int[] mixDiffRandDigits(){
        int[] num = new int[4];
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < TEN; i++) list.add(i);
        Collections.shuffle(list);
        for (int i = 0; i < num.length; i++) num[i] = list.get(i);
        return num;
    }

    public static int[] mixDiffRandDigits(int[] array){
        int[] num = new int[4];
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < TEN; i++) {
            if (!contains(array, i)) list.add(i);
        }
        Collections.shuffle(list);
        for (int i = 0; i < num.length; i++) num[i] = list.get(i);
        return num;
    }

    public static boolean contains(final int[] arr, final int key){
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }
}