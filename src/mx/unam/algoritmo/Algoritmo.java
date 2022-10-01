package mx.unam.algoritmo;


public class Algoritmo {
    private char[] number = new char[4];
    private int[] digito;
    private char[] number2;
    private int[] digito2;
    private String another;

    public Algoritmo(String number){
        // * Generate the first number
        for (int i = 0; i < number.length(); i++){
            this.number[i] = number.charAt(i);
        }
        // * Generate the second number
        another = GenerateRand.generateRandom();
        for (int i = 0; i < another.length(); i++) {
            number2[i] = another.charAt(i);
        }
        // Adding to array int[]
        
    }

    public void showAlg(){
        System.out.println("-----------------");
        System.out.println("El digito es el siguiente");
        System.out.println(new String(number));

    }

}
