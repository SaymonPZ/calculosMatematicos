package tools;

import utils.Utils;

import javax.swing.*;
import javax.swing.text.*;

public class ValorOriginalWindow extends JFrame {

    private JTextField txtValorComDesconto;
    private JTextField txtPercentual;
    private JTextField txtResultado;

    public static void iniciar() {
        new ValorOriginalWindow();
    }

    private ValorOriginalWindow() {
        setTitle("Calcular Valor Original");
        setSize(340, 230);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        ImageIcon icon = new ImageIcon(this.getClass().getResource("/icons/icon.png"));
        setIconImage(icon.getImage());

        criarComponentes();

        setVisible(true);
    }

    private void criarComponentes() {

        JLabel lblValor = new JLabel("Valor c/ desconto:");
        lblValor.setBounds(10, 20, 130, 25);
        add(lblValor);

        txtValorComDesconto = new JTextField();
        txtValorComDesconto.setBounds(150, 20, 150, 25);
        aplicarFiltroNumerico(txtValorComDesconto);
        add(txtValorComDesconto);

        JLabel lblPercentual = new JLabel("Desconto %:");
        lblPercentual.setBounds(10, 60, 130, 25);
        add(lblPercentual);

        txtPercentual = new JTextField();
        txtPercentual.setBounds(150, 60, 150, 25);
        aplicarFiltroNumerico(txtPercentual);
        add(txtPercentual);

        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(150, 140, 150, 25);
        add(btnCalcular);

        JLabel lblResultado = new JLabel("Valor original:");
        lblResultado.setBounds(10, 100, 130, 25);
        add(lblResultado);

        txtResultado = new JTextField();
        txtResultado.setBounds(150, 100, 150, 25);
        txtResultado.setEditable(false);
        add(txtResultado);

        btnCalcular.addActionListener(e -> calcular());
    }

    private void calcular() {

        String valorTexto = txtValorComDesconto.getText();
        String percentualTexto = txtPercentual.getText();

        if (valorTexto.isEmpty() || percentualTexto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos");
            return;
        }

        double valor = Double.parseDouble(valorTexto);
        double percentual = Double.parseDouble(percentualTexto);

        if (percentual >= 100) {
            JOptionPane.showMessageDialog(null, "Desconto deve ser menor que 100%");
            return;
        }

        double resultado = Utils.calcularValorOriginal(valor, percentual);

        txtResultado.setText(String.format("%.2f", resultado));
    }

    private void aplicarFiltroNumerico(JTextField campo) {
        ((AbstractDocument) campo.getDocument()).setDocumentFilter(new DocumentFilter() {

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {

                String atual = fb.getDocument().getText(0, fb.getDocument().getLength());

                String novoTexto = new StringBuilder(atual)
                        .replace(offset, offset + length, text)
                        .toString();

                if (novoTexto.matches("\\d*(\\.\\d*)?")) {

                    if (novoTexto.length() > 10) return;

                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }
}