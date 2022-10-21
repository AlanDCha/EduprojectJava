package mx.unam.algoritmo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MixRandNums {
    
    private static final int TEN_THOUSAND = 10000;
    private static final int TEN = 10;
    private static int[] num = new int[4];
    private static ArrayList<Integer> restricted = new ArrayList<>();
    private static ArrayList<Integer> indices = new ArrayList<>();

    public static int[] mixRandDigits(){
        Random rand = new Random();
        int my_number = rand.nextInt(TEN_THOUSAND);
        num[0] = my_number / 1000;
        num[1] = (my_number / 100) % 10;
        num[2] = (my_number / 10 ) % 10;
        num[3] = my_number % 10;
        return num;
    }

    public static int[] mixDiffRandDigits(){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < TEN; i++) list.add(i);
        Collections.shuffle(list);
        for (int i = 0; i < num.length; i++) num[i] = list.get(i);
        return num;
    }

    public static int[] mixDiffRandDigits(int[] array){
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

    public static int[] mixTwoRepeatedDigit(){
        for (int i = 1; i < 9; i++) restricted.add(i);
        for (int i = 0; i < num.length; i++) indices.add(i);
        ArrayList<Integer> repeated = new ArrayList<>();
        Collections.shuffle(restricted);
        for (int i = 0; i < 2; i++) {
            repeated.add(restricted.get(i));
            restricted.remove(i);
            Collections.shuffle(restricted);
        }
        Collections.shuffle(repeated);
        Collections.shuffle(indices);
        while (!indices.isEmpty()) {
            num[indices.get(0)] = repeated.get(0);
            indices.remove(0);
            num[indices.get(0)] = repeated.get(1);
            indices.remove(0);
        }
        return num;
    }
}