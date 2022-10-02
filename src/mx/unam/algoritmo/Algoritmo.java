package mx.unam.algoritmo;

import java.util.Arrays;

public class Algoritmo {

    private int[] number = new int[4];
    private int[] compar = new int[4];
    private byte count_right = 0;
    private byte count_wrong = 0;

    public Algoritmo(int[] number){
        this.number = number;
        compar = GenerateRand.generateRandom();
    }

    public void showAlg(){
        byte counter = 1;
        System.out.println("-----------------");
        System.out.println("El digito es el siguiente");
        System.out.println(Arrays.toString(number));
        while (counter <= 9) {
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
        count_right = 0;
        count_wrong = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == j){
                    if (first[i] == second[j]){
                        count_right++;
                    }
                }
            }
        }
        return count_right == 4;
    }

    public void printOut(int[] one, int[] two) {
        // Printing numbers
        System.out.println("Number to find");
        System.out.println(Arrays.toString(one));
        System.out.println("Number generated");
        System.out.println(Arrays.toString(two));
        
        // Printing counters
        System.out.println("Digitos en posicion correcta: "   + count_right);
        System.out.println("Digitos en posicion incorrecta: " + count_wrong);
    }

    public int[] modifyDigit(int [] comparison) {
        // TODO: MODIFY DIGIT ON POSITION
        int[] example = new int[4];
        return example;
    }
}
