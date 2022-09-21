package mx.unam.interfaz;

import javax.swing.JOptionPane;

public class Prueba {
    public static void main(String[] args) {
        String n = null;
        n = JOptionPane.showInputDialog("Dame un numero: ");
        int valorEntero = Integer.parseInt(n);

        String txt = valorEntero % 2 == 0 ? "Es par" : "Es impar";
        System.out.println(txt);
    }
}
