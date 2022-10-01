package mx.unam.algoritmo;

import java.util.Random;

public class GenerateRand {
    
    private static int number = 10000;

    public static String generateRandom(){
        Random rand = new Random();
        int my_number = rand.nextInt(number);
        return String.valueOf(my_number);
    }
}
