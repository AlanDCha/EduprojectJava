package mx.unam.algoritmo;

import java.util.Arrays;

public class Algoritmo {

    private int[] number = new int[4];
    private int[] compar = new int[4];
    private byte count_right = 0;
    private byte count_wrong = 0;
    private boolean[] prematch_first = {false, false, false, false};
    private boolean[] prematch_second = {false, false, false, false};
    private boolean[] match_first = {false, false, false, false};
    private boolean[] match_second = {false, false, false, false};

    public Algoritmo(int[] number){
        this.number = number;
        compar = GenerateRand.generateRandom();
    }

    public void showAlg(){
        byte counter = 1;
        System.out.println("-----------------");
        System.out.println("El digito es el siguiente");
        System.out.println(Arrays.toString(number));
        System.out.println("-----------------");
        while (counter <= 9) {
            System.out.println("||Ciclo: " + counter + " ||");
            if (checkNumEquals(number, compar)) {
                break;
            } else {
                counter++;
                printOut(number, compar);
                compar = modifyDigit(compar);
            } 
        }
    }

    public boolean checkNumEquals(int[] first, int[] second){
        // Initializing counters
        count_right = 0;
        count_wrong = 0;
        // Creating nested cycles for checking both numbers
        for (int i = 0; i < 4; i++) {
            if (GenerateRand.contains(first, second[i])){
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

        return count_right == 4;
    }

    private void printOut(int[] one, int[] two) {
        // Printing numbers
        System.out.println("Number to find");
        System.out.println(Arrays.toString(one));
        System.out.println("Number generated");
        System.out.println(Arrays.toString(two));
        
        // Printing counters
        System.out.println("Digitos en posicion correcta: "   + count_right);
        System.out.println("Digitos en posicion incorrecta: " + count_wrong);
    }

    private int[] modifyDigit(int [] comparison) {
        // TODO: MODIFY DIGIT ON POSITION
        int[] example = new int[4];
        return example;
    }
}