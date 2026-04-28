package tools;

import utils.Utils;

import javax.swing.*;
import javax.swing.text.*;

public class AmostragemPorcentagemWindow extends JFrame {

    private JTextField txtTotal;
    private JTextField txtPercentual;
    private JTextField txtResultado;

    public static void iniciar() {
        new AmostragemPorcentagemWindow();
    }

    private AmostragemPorcentagemWindow() {
        setTitle("Amostragem de Porcentagem");
        setSize(320, 230);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        ImageIcon icon = new ImageIcon(this.getClass().getResource("/icons/icon.png"));
        setIconImage(icon.getImage());

        criarComponentes();

        setVisible(true);
    }

    private void criarComponentes() {

        JLabel lblTotal = new JLabel("Total:");
        lblTotal.setBounds(10, 20, 100, 25);
        add(lblTotal);

        txtTotal = new JTextField();
        txtTotal.setBounds(120, 20, 150, 25);
        aplicarFiltroNumerico(txtTotal);
        add(txtTotal);

        JLabel lblPercentual = new JLabel("%:");
        lblPercentual.setBounds(10, 60, 100, 25);
        add(lblPercentual);

        txtPercentual = new JTextField();
        txtPercentual.setBounds(120, 60, 150, 25);
        aplicarFiltroNumerico(txtPercentual);
        add(txtPercentual);

        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(120, 140, 150, 25);
        add(btnCalcular);

        JLabel lblResultado = new JLabel("Corresponde a:");
        lblResultado.setBounds(10, 100, 110, 25);
        add(lblResultado);

        txtResultado = new JTextField();
        txtResultado.setBounds(120, 100, 150, 25);
        txtResultado.setEditable(false);
        add(txtResultado);

        btnCalcular.addActionListener(e -> calcular());
    }

    private void calcular() {

        String totalTexto = txtTotal.getText();
        String percentualTexto = txtPercentual.getText();

        if (totalTexto.isEmpty() || percentualTexto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos");
            return;
        }

        double total = Double.parseDouble(totalTexto);
        double percentual = Double.parseDouble(percentualTexto);

        double resultado = Utils.calcularPorcentagem(total, percentual);

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