import javax.swing.*;
import java.awt.*;
import java.net.URL;
import tools.*;

public class HubWindow extends JFrame {

    public static void iniciar() {
        new HubWindow();
    }

    private HubWindow() {
        setTitle("Hub de Ferramentas");
        getContentPane().setBackground(new Color(30, 30, 30));
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        ImageIcon icon = new ImageIcon(this.getClass().getResource("icons/icon.png"));
        setIconImage(icon.getImage());

        criarComponentes();

        setVisible(true);

    }

    private void criarComponentes() {

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(3, 3, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painel.setBackground(new Color(30, 30, 30));

        // Botões com caminho correto (COMEÇA COM /)
        painel.add(criarBotao("Calculadora de Desconto", "/icons/desconto.png", 60, () -> AplicarDescontoWindow.iniciar()));
        painel.add(criarBotao("Incrementar %", "/icons/incremento.png", 70, () -> IncrementarPorcetagemWindow.iniciar()));
        painel.add(criarBotao("Amostragem - X% de Y", "/icons/percentual.png", 90, () -> AmostragemPorcentagemWindow.iniciar()));
        painel.add(criarBotao("Amostragem 2 - X representa Y", "/icons/relacao.png", 100, () -> Amostragem2Window.iniciar()));
        painel.add(criarBotao("Qual foi o desconto", "/icons/diferenca.png", 110, () -> ABDescontoWindow.iniciar()));
        painel.add(criarBotao("Variação Delta", "/icons/delta.png", 120 , () -> DeltaWindow.iniciar()));
        painel.add(criarBotao("Valor Original", "/icons/original.png", 70,() -> ValorOriginalWindow.iniciar()));
        painel.add(criarBotao("Regra de 3", "/icons/regra3.png", 90, () -> RegraDeTresWindow.iniciar()));
        painel.add(criarBotao("Gerador de Senha", "/icons/senha.png", 70, () -> GeradorSenhaWindow.iniciar()));

        add(painel);
    }

    private JButton criarBotao(String texto, String caminhoIcone, Integer tamanho, Runnable acao) {

        java.net.URL url = getClass().getResource(caminhoIcone);

        ImageIcon icon = null;

        if (url != null) {
            Image img = new ImageIcon(url).getImage()
                    .getScaledInstance(tamanho, tamanho, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);
        }

        JButton botao = new JButton(texto, icon);

        //estilo
        botao.setBackground(new Color(60, 64, 65));
        botao.setForeground(Color.WHITE);
        botao.setOpaque(true);
        botao.setBorderPainted(false);
        botao.setFocusPainted(false);

        botao.setHorizontalTextPosition(SwingConstants.CENTER);
        botao.setVerticalTextPosition(SwingConstants.BOTTOM);

        //função hover
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(new Color(60, 64, 100));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(new Color(60, 64, 65));
            }

        });

        botao.addActionListener(e -> acao.run());

        return botao;
    }

}