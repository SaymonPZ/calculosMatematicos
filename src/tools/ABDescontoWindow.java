package tools;

import utils.Utils;

import javax.swing.*;
import javax.swing.text.*;

public class ABDescontoWindow extends JFrame {

    private JTextField txtOriginal;
    private JTextField txtPago;
    private JTextField txtResultado;

    public static void iniciar() {
        new ABDescontoWindow();
    }

    private ABDescontoWindow() {
        setTitle("Desconto entre valores (A -> B)");
        setSize(320, 230);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        criarComponentes();

        setVisible(true);
    }

    private void criarComponentes() {

        JLabel lblOriginal = new JLabel("Valor original:");
        lblOriginal.setBounds(10, 20, 110, 25);
        add(lblOriginal);

        txtOriginal = new JTextField();
        txtOriginal.setBounds(130, 20, 140, 25);
        aplicarFiltroNumerico(txtOriginal);
        add(txtOriginal);

        JLabel lblPago = new JLabel("Valor pago:");
        lblPago.setBounds(10, 60, 110, 25);
        add(lblPago);

        txtPago = new JTextField();
        txtPago.setBounds(130, 60, 140, 25);
        aplicarFiltroNumerico(txtPago);
        add(txtPago);

        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(130, 100, 140, 25);
        add(btnCalcular);

        JLabel lblResultado = new JLabel("Desconto %:");
        lblResultado.setBounds(10, 140, 110, 25);
        add(lblResultado);

        txtResultado = new JTextField();
        txtResultado.setBounds(130, 140, 140, 25);
        txtResultado.setEditable(false);
        add(txtResultado);

        btnCalcular.addActionListener(e -> calcular());
    }

    private void calcular() {

        String originalTexto = txtOriginal.getText();
        String pagoTexto = txtPago.getText();

        if (originalTexto.isEmpty() || pagoTexto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos");
            return;
        }

        double original = Double.parseDouble(originalTexto);
        double pago = Double.parseDouble(pagoTexto);

        if (original == 0) {
            JOptionPane.showMessageDialog(null, "Valor original não pode ser zero");
            return;
        }

        if (pago > original) {
            JOptionPane.showMessageDialog(null, "Valor pago não pode ser maior que o original");
            return;
        }

        double resultado = Utils.calcularDescontoEntreValores(original, pago);

        txtResultado.setText(String.format("%.2f %%", resultado));
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