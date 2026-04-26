package tools;

import utils.Utils;

import javax.swing.*;
import javax.swing.text.*;

public class Amostragem2Window extends JFrame {

    private JTextField txtTotal;
    private JTextField txtParte;
    private JTextField txtResultado;

    public static void iniciar() {
        new Amostragem2Window();
    }

    private Amostragem2Window() {
        setTitle("Amostragem (Parte de um Total)");
        setSize(320, 230);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

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

        JLabel lblParte = new JLabel("Parte:");
        lblParte.setBounds(10, 60, 100, 25);
        add(lblParte);

        txtParte = new JTextField();
        txtParte.setBounds(120, 60, 150, 25);
        aplicarFiltroNumerico(txtParte);
        add(txtParte);

        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(120, 100, 150, 25);
        add(btnCalcular);

        JLabel lblResultado = new JLabel("Corresponde a:");
        lblResultado.setBounds(10, 140, 110, 25);
        add(lblResultado);

        txtResultado = new JTextField();
        txtResultado.setBounds(120, 140, 150, 25);
        txtResultado.setEditable(false);
        add(txtResultado);

        btnCalcular.addActionListener(e -> calcular());
    }

    private void calcular() {

        String totalTexto = txtTotal.getText();
        String parteTexto = txtParte.getText();

        if (totalTexto.isEmpty() || parteTexto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos");
            return;
        }

        double total = Double.parseDouble(totalTexto);
        double parte = Double.parseDouble(parteTexto);

        if (total == 0) {
            JOptionPane.showMessageDialog(null, "Total não pode ser zero");
            return;
        }

        double resultado = Utils.calcularPercentual(total, parte);

        txtResultado.setText(String.format("%.2f %%", resultado));
    }

    // 🔒 Filtro numérico (mesmo padrão)
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