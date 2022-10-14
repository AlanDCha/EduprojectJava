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

    public static int[] mixDiffRandDigits(ArrayList<Integer> dropped){
        int[] num = new int[4];
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < TEN; i++) list.add(i);
        list.removeIf(x -> dropped.contains(x));
        Collections.shuffle(list);
        for (int i = 0; i < num.length; i++) num[i] = list.get(i);
        return num;
    }

    public static int[] mixDiffRandDigits(ArrayList<Integer> dropped, 
        int[][] matrix, int[] array, int[] right, int[] wrong, int intent){
            // TODO: Complete the function
            // Taking care of array previous  and (R, W)
        int[] modified = new int[4];
        boolean empty_dropped = true;
        int count_Ri;
        int count_W;
        int count_Ra = 0;
        // * Index to remove from matrix[2][]
        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i < modified.length; i++) index.add(i);

        if (!dropped.isEmpty()){
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < TEN; i++) list.add(i);
            list.removeIf(x -> dropped.contains(x));
            empty_dropped = false;
        }
        if (intent == 2) {
            ArrayList<Integer> numbers = new ArrayList<>();
            ArrayList<Integer> removed = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < modified.length; j++) numbers.add(j);
                count_Ri = right[i];
                count_W = wrong[i];
                count_Ra = count_Ra + (count_Ri + count_W);
                if (count_Ri + count_W == 0){
                    if (i == 0) continue;
                    else if (i == 1){
                    // Repeat digits (special case: 8 dropped in 2 intent)
                        for (int j = 0; j < TEN; j++) removed.add(j);
                        dropped.forEach(x -> removed.removeIf(n -> n == x));
                        for (int j = 0; j < modified.length; j++) {
                            Collections.shuffle(removed);
                            modified[j] = removed.get(0);
                        }
                    }
                } 
                while (count_Ri > 0 || count_W > 0){
                    if (count_Ri != 0){
                        Collections.shuffle(index);
                        modified[index.get(0)] = matrix[i][index.get(0)];
                        removed.add(matrix[i][numbers.get(0)]); // 7
                        index.remove(numbers.get(0));//rm index 2 obj from array
                        numbers.remove(0); // del 2 index
                        count_Ri--;
                    } else if (count_W != 0){
                        Collections.shuffle(numbers);
                        Collections.shuffle(index);
                        if (index.get(0) != numbers.get(0)){
                            modified[index.get(0)] = matrix[i][numbers.get(0)];
                            removed.add(matrix[i][numbers.get(0)]);
                            numbers.remove(0);
                        } else {
                            modified[index.get(0)] = matrix[i][numbers.get(1)];
                            removed.add(matrix[i][numbers.get(1)]);
                            numbers.remove(1);
                        }
                        index.remove(0);
                        count_W--;
                    }
                }
                numbers.clear();
            }
            boolean my_band = false;
            while (count_Ra < 4) {
                if (!my_band){
                    ArrayList<Integer> aux = new ArrayList<>();
                    for (int i = 0; i < TEN; i++) numbers.add(i);
                    for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < modified.length; j++) {
                            aux.add(matrix[i][j]);
                        }
                    }
                    aux.forEach(x -> numbers.removeIf(n -> x == n));
                }
                Collections.shuffle(numbers);
                Collections.shuffle(index);
                modified[index.get(0)] = numbers.get(0);
                numbers.remove(0);
                index.remove(0);
                count_Ra++;
                my_band = true;
            }
        } else {
            // TODO: Implement the gross function
        }
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

    public static boolean containsItemOnMatrix(int[][] matrix, int[] array,
            int intent){
        for (int j = intent - 1; j >= 0; j--) {
            for (int j2 = 3; j2 >= 0; j2--) {
                if (contains(array, matrix[j][j2])){
                    return true;
                }
            }
        }
        return false;
    }

}