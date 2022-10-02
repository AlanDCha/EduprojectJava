package mx.unam.algoritmo;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        Scanner cin2 = new Scanner(System.in);
        int entrada;
        int number;

        System.out.println("---------------------");
        System.out.println("Juego de adivinar un numero");
        System.out.println("---------------------");
        System.out.println("1. Introduzca un numero aleatorio (0-9999)");
        System.out.println("2. Que el sistema genere un numero");
        System.out.print("\tSeleccione la opcion adecuada: ");

        entrada = cin.nextInt();
        cin.close();

        switch (entrada){
            case 1:
                System.out.print("Ingrese el numero: ");
                number = cin2.nextInt();
                cin2.close();
                int[] numero = new int[4];
                numero[0] = number / 1000;
                numero[1] = (number / 100) % 10;
                numero[2] = (number / 10 ) % 10;
                numero[3] = number % 10;

                Algoritmo alg = new Algoritmo(numero);
                alg.showAlg();
                break;

            case 2:
                Algoritmo alg2 = new Algoritmo(GenerateRand.generateRandom());
                alg2.showAlg();
                break;

            default:
                break;
        }
    }
}
