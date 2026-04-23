package utils;

public final class Utils {

    private Utils() {} // impede instanciação

    public static double aplicarDesconto(double valor, double percentual) {
        return valor - (valor * percentual / 100.0);
    }
}