package mx.unam.aritmetica;

public class Calculadora {

    public int sumar(int param1, int param2){
        return param1 + param2;
    }

    public int multiplicar(int param1, int param2){
        return param1 * param2;
    }

    public void restar(int param1, int param2){
        System.out.println(param1 - param2); ;
    }

    public void dividir(int param1, int param2) throws ArithmeticException{
        System.out.println(param1 / param2); 
    }

    public Calculadora() {
    }
}
