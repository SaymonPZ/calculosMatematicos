import javax.swing.*;
import java.awt.*;
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

        ImageIcon icon = new ImageIcon(this.getClass().getResource("icons/icon.png"));
        setIconImage(icon.getImage());

        getContentPane().setBackground(new Color(30, 30, 30));

        criarComponentes();
        carregarUsuario();

        setVisible(true);

    }

    private void criarComponentes() {

        JLabel lblUsuario = new JLabel("Usuário:");

        lblUsuario.setBounds(10, 30, 70, 25);
        lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblUsuario.setForeground(Color.WHITE);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(80, 30, 180, 25);
        txtUsuario.setBackground(new Color(30, 30, 30));
        txtUsuario.setForeground(Color.WHITE);
        add(txtUsuario);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(10, 60, 70, 25);
        lblSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblSenha.setForeground(Color.WHITE);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(80, 60, 180, 25);
        txtSenha.setBackground(new Color(30, 30, 30));
        txtSenha.setForeground(Color.WHITE);
        add(txtSenha);

        chkLembrar = new JCheckBox("Lembrar usuário");
        chkLembrar.setBounds(80, 90, 180, 25);
        chkLembrar.setFont(new Font("Tahoma", Font.BOLD, 12));
        chkLembrar.setForeground(Color.WHITE);
        chkLembrar.setBackground(new Color(30, 30, 30));
        add(chkLembrar);

        btnEntrar = new JButton(new AbstractAction("ENTRAR") {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        btnEntrar.setBounds(160, 120, 100, 25);
        btnEntrar.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnEntrar.setBackground(new Color(30, 30, 30));
        btnEntrar.setForeground(Color.WHITE);
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