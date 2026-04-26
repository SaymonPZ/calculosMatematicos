package tools;

import utils.Utils;

import javax.swing.*;

public class GeradorSenhaWindow extends JFrame {

    private JCheckBox chkMaiusculas;
    private JCheckBox chkMinusculas;
    private JCheckBox chkNumeros;
    private JCheckBox chkSimbolos;

    private JComboBox<Integer> cmbTamanho;
    private JTextField txtResultado;

    public static void iniciar() {
        new GeradorSenhaWindow();
    }

    private GeradorSenhaWindow() {
        setTitle("Gerador de Senha");
        setSize(350, 280);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        criarComponentes();

        setVisible(true);
    }

    private void criarComponentes() {

        chkMaiusculas = new JCheckBox("Maiúsculas");
        chkMaiusculas.setBounds(20, 20, 150, 25);
        add(chkMaiusculas);

        chkMinusculas = new JCheckBox("Minúsculas");
        chkMinusculas.setBounds(20, 50, 150, 25);
        add(chkMinusculas);

        chkNumeros = new JCheckBox("Números");
        chkNumeros.setBounds(20, 80, 150, 25);
        add(chkNumeros);

        chkSimbolos = new JCheckBox("Símbolos");
        chkSimbolos.setBounds(20, 110, 150, 25);
        add(chkSimbolos);

        JLabel lblTamanho = new JLabel("Tamanho:");
        lblTamanho.setBounds(180, 20, 80, 25);
        add(lblTamanho);

        cmbTamanho = new JComboBox<>();
        for (int i = 1; i <= 20; i++) {
            cmbTamanho.addItem(i);
        }
        cmbTamanho.setBounds(180, 50, 100, 25);
        add(cmbTamanho);

        JButton btnGerar = new JButton("Gerar");
        btnGerar.setBounds(180, 90, 100, 25);
        add(btnGerar);

        txtResultado = new JTextField();
        txtResultado.setBounds(20, 160, 260, 30);
        txtResultado.setEditable(false);
        add(txtResultado);

        btnGerar.addActionListener(e -> gerarSenha());
    }

    private void gerarSenha() {

        boolean mai = chkMaiusculas.isSelected();
        boolean min = chkMinusculas.isSelected();
        boolean num = chkNumeros.isSelected();
        boolean sim = chkSimbolos.isSelected();

        int selecionados = (mai ? 1 : 0) + (min ? 1 : 0) + (num ? 1 : 0) + (sim ? 1 : 0);

        if (selecionados == 0) {
            JOptionPane.showMessageDialog(null, "Selecione pelo menos uma opção");
            return;
        }

        int tamanho = (int) cmbTamanho.getSelectedItem();

        if (tamanho < selecionados) {
            JOptionPane.showMessageDialog(null,
                    "Tamanho mínimo deve ser " + selecionados);
            return;
        }

        String senha = Utils.gerarSenha(tamanho, mai, min, num, sim);

        txtResultado.setText(senha);
    }
}