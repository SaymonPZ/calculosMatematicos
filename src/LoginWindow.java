import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoginWindow extends JFrame {

    private JButton btnEntrar;
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JCheckBox chkLembrar;

    public static void iniciar() {
        new LoginWindow();
    }

    private LoginWindow() {
        setSize(300, 220);
        setTitle("Login do Sistema");
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        criarComponentes();
        carregarUsuario();

        setVisible(true);
    }

    private void criarComponentes() {

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(10, 30, 70, 25);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(80, 30, 180, 25);
        add(txtUsuario);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(10, 60, 70, 25);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(80, 60, 180, 25);
        add(txtSenha);

        chkLembrar = new JCheckBox("Lembrar usuário");
        chkLembrar.setBounds(80, 90, 180, 25);
        add(chkLembrar);

        btnEntrar = new JButton(new AbstractAction("ENTRAR") {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        btnEntrar.setBounds(180, 120, 80, 25);
        add(btnEntrar);
    }

    private void login() {
        String usuario = txtUsuario.getText();
        char[] senhaArray = txtSenha.getPassword();
        String senha = new String(senhaArray);

        if (usuario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Usuário obrigatório");
            txtUsuario.requestFocus();
            return;
        }

        if (senha.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Senha obrigatória");
            txtSenha.requestFocus();
            return;
        }

        if (AuthService.auth(usuario, senha)) {
            JOptionPane.showMessageDialog(null, "Login realizado!");

            if (chkLembrar.isSelected()) {
                FileService.salvarUsuario(usuario);
            }
            else{
                FileService.salvarUsuario("");
            }

            dispose();
            HubWindow.iniciar();
        } else {
            JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!");
        }
        java.util.Arrays.fill(senhaArray, '\0');
    }

    private void carregarUsuario() {
        String usuarioSalvo = FileService.lerUsuario();
        if (usuarioSalvo != null && !usuarioSalvo.isEmpty()) {
            txtUsuario.setText(usuarioSalvo);
            chkLembrar.setSelected(true);
        }
    }
}