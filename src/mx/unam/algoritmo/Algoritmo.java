package mx.unam.algoritmo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Algoritmo {

    // * This is the number generated
    private int[] number = new int[4];
    // * This is the number to compare
    private int[] compar = GenerateRandNumbers.generateDiffRandNumbers();
    // * These are the counters for each attempt
    private byte count_right = 0;
    private byte count_wrong = 0;
    // * These are the prematch and match
    private boolean[] prematch_first  = {false, false, false, false};
    private boolean[] prematch_second = {false, false, false, false};
    private boolean[] match_first  = {false, false, false, false};
    private boolean[] match_second = {false, false, false, false};
    // * This matrix save all the attempts and the counters too.
    private int[][] matrix = new int[9][6];
    // * This is a flag that indicates if the number generated is right
    private boolean band = false;
    // * This array save all number that are considerated as droped
    private ArrayList<Integer> droped_numbers = new ArrayList<>();
    // * This array save all pre-pinned numbers 
    private ArrayList<Integer> prepin_nmbs = new ArrayList<>();
    // * This array save all pinned numbers 
    // private ArrayList<Integer> pinned_nmbs = new ArrayList<>();
    // * This flag save all pinned numbers and won't change it
    private boolean set_pinned_numbers = false;

    public Algoritmo(int[] number){
        this.number = number;
    }

    public Algoritmo(){}

    public void showAlg(){
        byte counter = 1;
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
                    if (i < 4){
                        matrix[counter - 1][i] = compar[i];
                    } else if (i == 4){
                        matrix[counter - 1][i] = count_right;
                    } else {
                        matrix[counter - 1][i] = count_wrong;
                    }
                }
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
        // Initializing counters
        count_right = 0;
        count_wrong = 0;
        // Creating nested cycles for checking both numbers
        for (int i = 0; i < 4; i++) {
            if (GenerateRandNumbers.contains(first, second[i])){
                for (int j = 0; j < 4; j++) {
                    if (second[i] == first[j]) {
                        if (i == j) {
                            if (prematch_first[j] == true || 
                                    prematch_second[i] == true) {
                                // Deleting prematch
                                prematch_first[j] = false;
                                prematch_second[i] = false;
                                // Substracting 1 from count_wrong
                                count_wrong--;
                                // Adding 1 from count_right
                                count_right++;
                                // Creating match
                                match_first[j] = true;
                                match_second[i] = true;
                            } else {
                                // Adding 1 from count_right
                                count_right++;
                                // Creating match
                                match_first[j] = true;
                                match_second[i] = true;
                            }
                        } else {
                            if (match_first[j] == false && 
                                            match_second[i] == false){
                                if (prematch_first[j] == false &&
                                        prematch_second[i] == false){
                                    // Adding 1 from count_wrong
                                    count_wrong++;
                                    // Creating prematch
                                    prematch_first[j]  = true;
                                    prematch_second[i] = true;
                                }
                            }
                        }
                    } 
                }
            } 
        }
        for (int i = 0; i < 4; i++) {
            prematch_first[i] = false;
            prematch_second[i] = false;
            match_first[i] = false;
            match_second[i] = false;
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

    private int[] modifyDigit(int[] comparison, int intent) {
        // Creating the modified number
        int[] modified = new int[4];
        int wnod = matrix[intent][4];
        int rnod = matrix[intent][5];
        int digit;
        int index;

        // ! If W + R == 4. Focus on these digits and droped the others
        if (wnod + rnod != 4) {
            if (wnod == 0 && rnod == 0) {
            // * If no number doesn't equals to the number to find
                for (int i = 0; i < 4; i++) {
                    droped_numbers.add(comparison[i]);
                }
                modified = GenerateRandNumbers.
                                            pickRandNumExceptArray(comparison);
            } else if (wnod == 0 && rnod == 1){
            // * If there's only one digit that matches with any digit of number
            // Pick only one random digit from the array
                index = new Random().nextInt(comparison.length);
                digit = comparison[index];
                prepin_nmbs.add(digit);
                modified = GenerateRandNumbers.generateThreeDigit(comparison, 
                                                                digit, index);
            }
            
        } else {
            if (!set_pinned_numbers){
                prepin_nmbs.clear();
                for (int iterable : comparison) {
                    prepin_nmbs.add(iterable);
                }
                set_pinned_numbers = true;
            }
            // TODO: Send to a function the following parameters: wnod
            /**
             * First searching intents before that contains the same condition
             * If exist intents then analizing it. search for match digit 
             * If wnod == 0 -> re-order 3 numbers and don't move from position 1
             * In fact. Re-order 4 - wnod and pin in position wnod digits
             */
        }
        return modified;
    }
}