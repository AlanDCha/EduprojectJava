package mx.unam.diasmes;

import java.util.Scanner;

public class Prueba {
    public static void main(String[] args) {

        String mes = null;
        int num_mes = 0;
        Scanner sc = new Scanner(System.in);
        String[] meses = {"enero", "febrero", "marzo", "abril", "mayo", "junio",
            "julio", "agosto", "septiembre", "octubre", "noviembre",
            "diciembre"};
        byte[] n_meses = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

        System.out.print("Dame un mes: ");
        mes = sc.next(); // El usuario escribe marzo
        mes = mes.toLowerCase();
        
        for (byte n_mes : n_meses) {
            if (mes.equals(meses[n_mes])) num_mes = n_mes + 1;
        }
        ProbarMes m = new ProbarMes();
        m.calc_mes(num_mes);
        sc.close();
    }
}
