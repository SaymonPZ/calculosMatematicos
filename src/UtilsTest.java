import utils.Utils;

public class UtilsTest {

    public static void main(String[] args) {

        System.out.println("===== TESTES DA FORMULAS =====\n");

        // Valores base
        double valor = 100.0;
        double percentual = 20.0;

        // aplicarDesconto
        double desconto = Utils.aplicarDesconto(500, 30);
        System.out.println("Aplicar Desconto (500 - 30%): " + desconto);

        // aplicarIncremento
        double incremento = Utils.aplicarIncremento(55, 5);
        System.out.println("\nAplicar Incremento (55 + 5%): " + incremento);

        // calcularPorcentagem
        double porcentagem = Utils.calcularPorcentagem(699, 88);
        System.out.println("\n88% de 699: " + porcentagem);

        // calcularPercentual
        double percentualCalc = Utils.calcularPercentual(7777, 5555);
        System.out.println("\n7777 é quantos % de 5555: " + percentualCalc + "%");

        // calcularDescontoEntreValores
        double descontoEntre = Utils.calcularDescontoEntreValores(999, 88);
        System.out.println("\nDesconto entre 999 e 88: " + descontoEntre + "%");

        // calcularDeltaPercentual
        double delta = Utils.calcularDeltaPercentual(777, 8888);
        System.out.println("\nDelta percentual de 777 para 8888: " + delta + "%");

        // calcularValorOriginal
        double valorOriginal = Utils.calcularValorOriginal(999, 750);
        System.out.println("\nValor original (999  com 750% desc): " + valorOriginal);

        // regraDeTres
        double regra = Utils.regraDeTres(3, 44, 56);
        System.out.println("\nRegra de 3 (2 -> 10, 4 -> ?): " + regra);

        // gerarSenha (10 numeros com todos os tipos de caracteres)
        String senha = Utils.gerarSenha(10, true, true, true, true);
        System.out.println("\nSenha de 10 digitos com caracteres minusculos, maissculos, números e simbolos : " + senha);

    }
}