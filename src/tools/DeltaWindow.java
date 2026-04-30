package tools;

import helper.UtilsUI;
import utils.Utils;

import javax.swing.*;
import javax.swing.text.*;

public class DeltaWindow extends JFrame {

    private JTextField txtInicial;
    private JTextField txtFinal;
    private JTextField txtResultado;

    public static void iniciar() {
        new DeltaWindow();
    }

    private DeltaWindow() {
        setTitle("Variação Delta (%)");
        setSize(320, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        ImageIcon icon = new ImageIcon(this.getClass().getResource("/icons/icon.png"));
        setIconImage(icon.getImage());

        criarComponentes();

        setVisible(true);
    }

    private void criarComponentes() {

        JLabel lblInicial = new JLabel("Valor inicial (a):");
        lblInicial.setBounds(10, 20, 110, 25);
        add(lblInicial);

        txtInicial = new JTextField();
        txtInicial.setBounds(130, 20, 140, 25);
        aplicarFiltroNumerico(txtInicial);
        add(txtInicial);

        JLabel lblFinal = new JLabel("Valor final (b):");
        lblFinal.setBounds(10, 60, 110, 25);
        add(lblFinal);

        txtFinal = new JTextField();
        txtFinal.setBounds(130, 60, 140, 25);
        aplicarFiltroNumerico(txtFinal);
        add(txtFinal);

        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(130, 140, 140, 25);
        add(btnCalcular);

        JLabel lblResultado = new JLabel("Diferença %:");
        lblResultado.setBounds(10, 100, 110, 25);
        add(lblResultado);

        txtResultado = new JTextField();
        txtResultado.setBounds(130, 100, 140, 25);
        txtResultado.setEditable(false);
        add(txtResultado);

        btnCalcular.addActionListener(e -> calcular());

        JLabel lblFormula = UtilsUI.criarLabelFormula(
                " v = ((a - b) / a) * 100.0"
        );

        lblFormula.setBounds(10, 200, 280, 40);

        add(lblFormula);
    }

    private void calcular() {

        String inicialTexto = txtInicial.getText();
        String finalTexto = txtFinal.getText();

        if (inicialTexto.isEmpty() || finalTexto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos");
            return;
        }

        double inicial = Double.parseDouble(inicialTexto);
        double finall = Double.parseDouble(finalTexto);

        if (inicial == 0) {
            JOptionPane.showMessageDialog(null, "Valor inicial não pode ser zero");
            return;
        }

        double resultado = Utils.calcularDeltaPercentual(inicial, finall);

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