import javax.swing.*;

public class HubWindow extends JFrame {

    public static void iniciar() {
        new HubWindow();
    }

    private HubWindow() {
        setTitle("Hub de Ferramentas");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        criarComponentes();

        setVisible(true);
    }

    private void criarComponentes() {

        JLabel titulo = new JLabel("Menu de Ferramentas");
        titulo.setBounds(120, 20, 200, 30);
        add(titulo);


    }
}