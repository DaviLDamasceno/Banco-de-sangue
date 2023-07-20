package Programa;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaLogin {

    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaLogin window = new TelaLogin();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TelaLogin() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 50, 80, 14);
        frame.getContentPane().add(lblUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 100, 80, 14);
        frame.getContentPane().add(lblPassword);

        usernameField = new JTextField();
        usernameField.setBounds(140, 50, 200, 20);
        frame.getContentPane().add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(140, 100, 200, 20);
        frame.getContentPane().add(passwordField);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(140, 150, 89, 23);
        frame.getContentPane().add(btnLogin);
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

             
                List<String> bancosDeSangue = BancoDeSangue.carregarBancosDeSangueDeArquivoTexto("bancos_de_sangue.txt");

            
                if (bancosDeSangue.contains(username) && password.equals(username)) {
                  
                    openTelaUsuario(username);
                } else if (username.equals("admin") && password.equals("admin")) {
                
                    openGestorCentral();
                } else {
                    
                    JOptionPane.showMessageDialog(frame, "Credenciais inválidas!", "Erro de Login", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void openGestorCentral() {
       
        GestorCentralFrame gestorCentralFrame = new GestorCentralFrame();
     
        gestorCentralFrame.setVisible(true);

      
        frame.dispose();
    }

    private void openTelaUsuario(String nomeBanco) {
       
        TelaUsuario telaUsuario = new TelaUsuario(nomeBanco);
        
        telaUsuario.setVisible(true);

        
        frame.dispose();
    }
}


