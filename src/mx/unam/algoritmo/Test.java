package mx.unam.algoritmo;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        Scanner cin2 = new Scanner(System.in);
        int entrada;
        String numero;

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
                numero = cin2.next();
                cin2.close();
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
