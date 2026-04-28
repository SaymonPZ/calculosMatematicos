package tools;

import utils.Utils;
import javax.swing.*;
import javax.swing.text.*;

public class IncrementarPorcetagemWindow extends JFrame {
    private JTextField txtValor;
    private JTextField txtPercentual;
    private JTextField txtResultado;

    public static void iniciar() {
        new IncrementarPorcetagemWindow();
    }
    private IncrementarPorcetagemWindow() {
        setTitle("Incrementar Porcentagem");
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

        JLabel lblValor = new JLabel("Valor inicial:");
        lblValor.setBounds(10, 20, 100, 25);
        add(lblValor);

        txtValor = new JTextField();
        txtValor.setBounds(120, 20, 150, 25);
        aplicarFiltroNumerico(txtValor);
        add(txtValor);

        JLabel lblPercentual = new JLabel("% Acréscimo:");
        lblPercentual.setBounds(10, 60, 100, 25);
        add(lblPercentual);

        txtPercentual = new JTextField();
        txtPercentual.setBounds(120, 60, 150, 25);
        aplicarFiltroNumerico(txtPercentual);
        add(txtPercentual);

        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(120, 140, 150, 25);
        add(btnCalcular);

        JLabel lblResultado = new JLabel("Resultado:");
        lblResultado.setBounds(10, 100, 100, 25);
        add(lblResultado);

        txtResultado = new JTextField();
        txtResultado.setBounds(120, 100, 150, 25);
        txtResultado.setEditable(false);
        add(txtResultado);

        btnCalcular.addActionListener(e -> calcular());
    }

    private void calcular() {

        String valorTexto = txtValor.getText();
        String percentualTexto = txtPercentual.getText();

        if (valorTexto.isEmpty() || percentualTexto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos");
            return;
        }

        double valor = Double.parseDouble(valorTexto);
        double percentual = Double.parseDouble(percentualTexto);

        if (percentual < 0) {
            JOptionPane.showMessageDialog(null, "Percentual não pode ser negativo");
            return;
        }

        double resultado = Utils.aplicarIncremento(valor, percentual);

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
