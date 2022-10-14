package mx.unam.algoritmo;

import java.util.ArrayList;
import java.util.Arrays;

public class Algoritmo {

    // * This is the number generated
    private int[] number = new int[4];
    // * This is the number to compare
    private int[] compar = MixRandNums.mixDiffRandDigits();
    // * These are the counters for each attempt
    private byte count_right = 0;
    private byte count_wrong = 0;
    // * This matrix saves all the attempts and the counters too.
    private int[][] matrix = new int[9][4];
    // * This matrix saves all the wrong and right number of digits
    private int[] counters_right = new int[9];
    private int[] counters_wrong = new int[9];
    // * This is a flag that indicates if the number generated is right
    private boolean band = false;
    // * This array save all number that are considerated as droped
    private ArrayList<Integer> dropped = new ArrayList<>();
    // * This array save all pre-pinned numbers 
    private ArrayList<Integer> prepin_nmbs = new ArrayList<>();
    // * This array save all pinned numbers 
    // private ArrayList<Integer> pinned_nmbs = new ArrayList<>();
    // * This flag save all pinned numbers and won't change it
    private boolean set_pinned_numbers = false;
    // * This counter is for each intent
    private byte counter = 1;
    // * This flag is to detect when it's needed to change strategy 
    private boolean change = false;

    public Algoritmo(int[] number){
        this.number = number;
    }

    public Algoritmo(){}

    public void showAlg(){
        System.out.println("-----------------");
        System.out.println("El digito es el siguiente");
        System.out.println(Arrays.toString(number));
        System.out.println("-----------------");
        while (counter <= 9) {
            System.out.println("||Ciclo: " + counter + " ||");
            if (checkNumEquals(number, compar)) {
                band = true;
                break;
            } else {
                for (int i = 0; i < compar.length; i++) {
                    matrix[counter - 1][i] = compar[i];
                }
                counters_right[counter - 1] = count_right;
                counters_wrong[counter - 1] = count_wrong;
                printOut(compar);
                compar = modifyDigit(compar, counter);
                counter++;
            } 
        }
        if (band){
            System.out.println("I did it :)");
        } else{
            System.out.println("I couldn't solve it. So sorry :(");
        }
    }

    public boolean checkNumEquals(final int[] first, int[] second){
        // * These are the prematch and match
        boolean[] prematch_first  = {false, false, false, false};
        boolean[] prematch_second = {false, false, false, false};
        boolean[] match_first  = {false, false, false, false};
        boolean[] match_second = {false, false, false, false};
        // Initializing counters
        count_right = 0;
        count_wrong = 0;
        ArrayList<Integer> aux_first = new ArrayList<>();
        boolean band_first = false;
        for (int i = 0; i < 4; i++) {
            if (MixRandNums.contains(first, second[i])){
                for (int j = 0; j < 4; j++) {
                    if (second[i] == first[j] && i == j) {
                        if (prematch_first[j] || prematch_second[i]){
                            prematch_first[j] = false;
                            prematch_second[i] = false;
                            if (band_first){
                                prematch_first[aux_first.get(0)] = false;
                            }
                            count_wrong--;
                            count_right++;
                            match_first[j] = true;
                            match_second[i] = true;
                        } else {
                            count_right++;
                            match_first[j] = true;
                            match_second[i] = true;
                        }
                    } else if (second[i] == first[j] && i != j) {
                        if ((!match_first[j] && !match_second[i])
                            && (!prematch_first[j] && !prematch_second[i])
                            && (first[j] != second[j])){

                            count_wrong++;
                            prematch_first[j]  = true;
                            prematch_second[i] = true;
                            aux_first.add(j);
                            band_first = true;
                        }
                    }
                }
            } 
            band_first = false;
            aux_first.clear();
        }
        return count_right == 4;
    }

    public void printOut(int[] two) {
        // Printing numbers
        System.out.println("Number generated");
        System.out.println(Arrays.toString(two));
        
        // Printing counters
        System.out.println("Digitos en posicion correcta: "   + count_right);
        System.out.println("Digitos en posicion incorrecta: " + count_wrong);
    }

    private int[] modifyDigit(int[] compare, int intent) {
        // Creating the modified number
        int[] modified = new int[4];
        // Highlighting the current counters
        int rnod = counters_right[intent - 1];
        int wnod = counters_wrong[intent - 1];

        // ! If W + R == 4. Focus on these digits and dropped the others
        if (wnod + rnod != 4) {
            if (wnod == 0 && rnod == 0) {
            // * If no number doesn't equals to the number to find
                for (int i = 0; i < 4; i++) {
                    dropped.add(compare[i]);
                }
                if (counter == 1){
                    modified = MixRandNums.mixDiffRandDigits(dropped);
                } else {
                    modified = MixRandNums.mixDiffRandDigits(dropped, 
                        matrix, compare, counters_right, counters_wrong, intent);
                }
            } else { // When W, R != 0
                if (counter == 1){
                    modified = MixRandNums.mixDiffRandDigits(compare);
                } else {
                    if (!change && counter == 5){
                        if (!checkSumCounters()) {
                            change = true;
                        }
                    }
                    if (!change){
                        modified = MixRandNums.mixDiffRandDigits(dropped, 
                        matrix, compare, counters_right, counters_wrong, intent);
                    } else {
                        // TODO: Implement the number to repeat
                        // TODO: Complete the repeatDigits function
                    }
                }
            }
            
        } else { // When W + R == 4
            if (!set_pinned_numbers){
                prepin_nmbs.clear();
                for (int iterable : compare) {
                    prepin_nmbs.add(iterable);
                }
                set_pinned_numbers = true;
            }
            modified = MixRandNums.sortDigits(matrix, prepin_nmbs);
        }
        return modified;
    }

    public boolean checkSumCounters(){
        for (int i = 0; i < counters_right.length; i++) {
            if (counters_right[i] + counters_wrong[i] >= 3)
            return true;
        }
        return false;
    }
}