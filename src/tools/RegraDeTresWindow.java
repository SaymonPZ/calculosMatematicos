package tools;

import utils.Utils;

import javax.swing.*;
import javax.swing.text.*;

public class RegraDeTresWindow extends JFrame {

    private JTextField txtA;
    private JTextField txtB;
    private JTextField txtR1;
    private JTextField txtR2;

    public static void iniciar() {
        new RegraDeTresWindow();
    }

    private RegraDeTresWindow() {
        setTitle("Regra de 3");
        setSize(360, 250);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        criarComponentes();

        setVisible(true);
    }

    private void criarComponentes() {

        JLabel lblA = new JLabel("a:");
        lblA.setBounds(20, 20, 20, 25);
        add(lblA);

        txtA = new JTextField();
        txtA.setBounds(50, 20, 100, 25);
        aplicarFiltroNumerico(txtA);
        add(txtA);

        JLabel lblR1 = new JLabel("= r1:");
        lblR1.setBounds(170, 20, 40, 25);
        add(lblR1);

        txtR1 = new JTextField();
        txtR1.setBounds(220, 20, 100, 25);
        aplicarFiltroNumerico(txtR1);
        add(txtR1);

        JLabel lblB = new JLabel("b:");
        lblB.setBounds(20, 60, 20, 25);
        add(lblB);

        txtB = new JTextField();
        txtB.setBounds(50, 60, 100, 25);
        aplicarFiltroNumerico(txtB);
        add(txtB);

        JLabel lblR2 = new JLabel("= r2:");
        lblR2.setBounds(170, 60, 40, 25);
        add(lblR2);

        txtR2 = new JTextField();
        txtR2.setBounds(220, 60, 100, 25);
        txtR2.setEditable(false);
        add(txtR2);

        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(110, 110, 120, 30);
        add(btnCalcular);

        btnCalcular.addActionListener(e -> calcular());
    }

    private void calcular() {

        String aTexto = txtA.getText();
        String bTexto = txtB.getText();
        String r1Texto = txtR1.getText();

        if (aTexto.isEmpty() || bTexto.isEmpty() || r1Texto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos");
            return;
        }

        double a = Double.parseDouble(aTexto);
        double b = Double.parseDouble(bTexto);
        double r1 = Double.parseDouble(r1Texto);

        if (a == 0) {
            JOptionPane.showMessageDialog(null, "Valor 'a' não pode ser zero");
            return;
        }

        double r2 = Utils.regraDeTres(a, b, r1);

        txtR2.setText(String.format("%.2f", r2));
    }

    // 🔒 Filtro numérico padrão
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