package mx.unam.algoritmo;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String input;
        int choice;
        boolean error = false;
        System.out.println("---------------------");
        System.out.println("Guess the number game");
        System.out.println("---------------------");
        System.out.println("1. 4 digit with no restrictions");
        System.out.println("2. 4 digit with repetition (1-8)");
        System.out.print("\tChoose your option: ");

        input = cin.next();

        do {
            try {
                choice = checkNumberFromUser(input);
                // choice = Integer.parseInt(input);
                error = false;
            } catch (NumberFormatException e) {
                System.out.println("Error entering a number. Try again");
                
                System.out.println("1. 4 digit with no restrictions");
                System.out.println("2. 4 digit with repetition (1-8)");
                System.out.print("\tChoose your option: ");

                input = cin.next();
                choice = checkNumberFromUser(input);
                // choice = Integer.parseInt(input);
                error = true;
            }
        } while (error);
        

        switch (choice) {
            case 1:
                NoRestriction.menu();
                break;
            case 2:
                Restriction.menu();
                break;
            default:
                System.out.println("Choose an option please: \n");
                main(args);
                break;
        }
        cin.close();
    }

    public static int checkNumberFromUser(String str) 
        throws NumberFormatException {
        return Integer.parseInt(str);
    }
}
