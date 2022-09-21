package mx.unam.diasmes;

public class ProbarMes {
    public void calc_mes(int x){
        switch (x){
            case 2:
                System.out.println("El mes tiene 28 dias");
                break;
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                System.out.println("El mes tiene 31 dias");
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                System.out.println("El mes tiene 30 dias");
                break;
        }
    }
}
