package utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Utils {

    private Utils() {} // impede instanciação

    public static double aplicarDesconto(double valor, double percentual) {
        return valor - (valor * (percentual / 100.0));
    }

    public static double aplicarIncremento(double valor, double percentual) {
        return valor + (valor * (percentual / 100.0));
    }

    public static double calcularPorcentagem(double total, double percentual) {
        return (total * percentual) / 100.0;
    }

    public static double calcularPercentual(double total, double parte) {
        if (total == 0) return 0;
        return (parte / total) * 100.0;
    }

    public static double calcularDescontoEntreValores(double valorOriginal, double valorPago) {
        if (valorOriginal == 0) return 0;
        return ((valorOriginal - valorPago) / valorOriginal) * 100.0;
    }

    public static double calcularDeltaPercentual(double valorInicial, double valorFinal) {
        if (valorInicial == 0) return 0;
        return ((valorFinal - valorInicial) / valorInicial) * 100.0;
    }

    public static double calcularValorOriginal(double valorFinal, double percentual) {
        return (valorFinal * 100.0 / (100.0 - percentual));
    }
    public static double regraDeTres(double a, double b, double r1) {
        if (a == 0) return 0;
        return (r1 * b) / a;
    }

    private static final String MAIUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMEROS = "0123456789";
    private static final String SIMBOLOS = "!@#$%&*()-_=+";

    public static String gerarSenha(int tamanho, boolean mai, boolean min, boolean num, boolean sim) {

        List<String> gruposSelecionados = new ArrayList<>();
        StringBuilder pool = new StringBuilder();
        Random random = new Random();

        if (mai) {
            gruposSelecionados.add(MAIUSCULAS);
            pool.append(MAIUSCULAS);
        }

        if (min) {
            gruposSelecionados.add(MINUSCULAS);
            pool.append(MINUSCULAS);
        }

        if (num) {
            gruposSelecionados.add(NUMEROS);
            pool.append(NUMEROS);
        }

        if (sim) {
            gruposSelecionados.add(SIMBOLOS);
            pool.append(SIMBOLOS);
        }

        StringBuilder senha = new StringBuilder();

        for (String grupo : gruposSelecionados) {
            senha.append(grupo.charAt(random.nextInt(grupo.length())));
        }

        while (senha.length() < tamanho) {
            senha.append(pool.charAt(random.nextInt(pool.length())));
        }

        return embaralhar(senha.toString());
    }

    private static String embaralhar(String texto) {
        char[] array = texto.toCharArray();
        Random random = new Random();

        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        return new String(array);
    }
}