import javax.swing.*;
import java.awt.*;
import tools.*;

public class HubWindow extends JFrame {

    public static void iniciar() {
        new HubWindow();
    }

    private HubWindow() {
        setTitle("Hub de Ferramentas");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        criarComponentes();

        setVisible(true);
    }

    private void criarComponentes() {

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 2, 10, 10)); // 5 linhas, 2 colunas
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Botões
        painel.add(criarBotao("Calculadora de Desconto", () -> AplicarDescontoWindow.iniciar()));
        painel.add(criarBotao("Incrementar %", () -> Amostragem2Windows.iniciar()));
        painel.add(criarBotao("X% de Y", () -> ABDescontoWindows.iniciar()));
        painel.add(criarBotao("X representa Y", () -> DeltaWindows.iniciar()));
        painel.add(criarBotao("Desconto por diferença", () -> AmostragemPorcetagem.iniciar()));
        painel.add(criarBotao("Variação Delta", () -> IncrementarPorcetagem.iniciar()));
        painel.add(criarBotao("Valor Original", () -> ValorOriginalWindow.iniciar()));
        painel.add(criarBotao("Regra de 3", () -> RegraDeTresWindow.iniciar()));
        painel.add(criarBotao("Gerador de Senha", () -> GeradorSenhaWindow.iniciar()));

        add(painel);
    }

    private JButton criarBotao(String texto, Runnable acao) {
        JButton botao = new JButton(texto);
        botao.addActionListener(e -> acao.run());
        return botao;
    }
}