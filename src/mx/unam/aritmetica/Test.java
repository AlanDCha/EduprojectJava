package mx.unam.aritmetica;

import java.util.Scanner;

public class Test {
    
    private static int param1;
    private static int param2;

    public static void main(String[] args) {
        int op = 0;
        Scanner cin = new Scanner(System.in);
        System.out.print("\t1 sumar\n\t2 multiplicar" +
            "\n\t3 restar \n\t4 dividir \nSelecciona: ");
        op = cin.nextInt();

        Calculadora calc = new Calculadora();
        switch (op) {
            case 1:
                getTwoParameters();
                System.out.println(calc.sumar(param1, param2));
                break;
        
            case 2:
                getTwoParameters();
                System.out.println(calc.multiplicar(param1, param2));
                break;

            case 3:
                getTwoParameters();
                calc.restar(param1, param2);
                break;

            case 4:
                getTwoParameters();
                try {
                    calc.dividir(param1, param2);
                } catch (ArithmeticException exception) {
                    System.err.println("Excepcion: " + exception);
                    System.err.println("Cero es un denominador invalido");
                }
                break;

            default:
                break;
        }
        cin.close();
    }

    public static void getTwoParameters(){
        Scanner cin = new Scanner(System.in);
        Scanner cin2 = new Scanner(System.in);

        System.out.print("Dame un operador: ");
        param1 = cin.nextInt();
        System.out.print("Dame otro operador: ");
        param2 = cin2.nextInt();
        cin.close();
        cin2.close();
    }
}
