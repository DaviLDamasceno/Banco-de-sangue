package Programa;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;


public class TelaUsuario extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomeBanco;
	private JButton btnExcluirDoacao;
	BancoDeSangue banco = new BancoDeSangue();

	

    public TelaUsuario(String nomeBanco) {
        this.nomeBanco = nomeBanco;
        initialize();
    }

    private void initialize() {
        setTitle("Tela do Usuário - " + nomeBanco);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new GridLayout(2, 1));
        JPanel panelBotoes = new JPanel(new GridLayout(1, 2));

        JButton btnAdicionarDoacao = new JButton("Adicionar Doação");
        panelBotoes.add(btnAdicionarDoacao);
        btnAdicionarDoacao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarDoacao();
            }
        });
        
        JButton btnExcluirDoacao = new JButton("Excluir Doação");
        panelBotoes.add(btnExcluirDoacao);
        btnExcluirDoacao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Doacao> doacoes = banco.carregarDoacoesDeArquivoTexto(nomeBanco);

                Doacao doacaoSelecionada = (Doacao) JOptionPane.showInputDialog(
                        TelaUsuario.this,
                        "Selecione a Doação de Sangue a ser excluída:",
                        "Excluir Doação",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        doacoes.toArray(),
                        null
                );

                if (doacaoSelecionada != null) {
                    
                    banco.excluirDoacao(doacaoSelecionada);
                    doacoes.remove(doacaoSelecionada);
                   
                    BancoDeSangue.salvarDoacoesEmArquivoTexto(doacoes, nomeBanco);
                    JOptionPane.showMessageDialog(TelaUsuario.this, "Doação de Sangue excluída: " + doacaoSelecionada.getId());
                } else {
                    JOptionPane.showMessageDialog(TelaUsuario.this, "Nenhuma doação selecionada.");
                }
            }
            
          
            
        });
    
        JButton btnSolicitarDoacao = new JButton("Solicitar Doação");
        panelBotoes.add(btnSolicitarDoacao);
        btnSolicitarDoacao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                solicitarDoacao();
            }
        });
     
        JButton btnLogout = new JButton("Logout");
        panelBotoes.add(btnSolicitarDoacao);
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openTelaLogin();
            }
        });

        



        getContentPane().add(panelBotoes);
    
    }
    

    

    private void adicionarDoacao() {

        JTextField tipoSanguineoField = new JTextField();
        JTextField dataColetaField = new JTextField();
        JTextField idField = new JTextField();

        Object[] message = {
                "Tipo Sanguíneo:", tipoSanguineoField,
                "Data de validade (xx/xx/xxxx):", dataColetaField,
                "ID:", idField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Adicionar Doação", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String tipoSanguineo = tipoSanguineoField.getText();
            String dataColeta = dataColetaField.getText();
            String id = idField.getText();
            if (!isValidTipoSanguineo(tipoSanguineo)) {
                JOptionPane.showMessageDialog(this, "Tipo sanguíneo inválido. Por favor, insira um tipo sanguíneo válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!isValidData(dataColeta)) {
                JOptionPane.showMessageDialog(this, "Data inválida. Por favor, insira uma data no formato xx/xx/xxxx.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Doacao doacao = new Doacao(tipoSanguineo, dataColeta, id);
            BancoDeSangue.salvarDoacaoEmArquivoTexto(doacao, nomeBanco);
            JOptionPane.showMessageDialog(this, "Doação adicionada!");
            banco.adicionarDoacao(doacao);
        }
    }

    private boolean isValidTipoSanguineo(String tipoSanguineo) {
        String[] tiposValidos = {"A-", "B-", "AB-", "O-", "A+", "B+", "AB+", "O+"};
        for (String tipo : tiposValidos) {
            if (tipo.equals(tipoSanguineo)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidData(String data) {
        String regex = "^\\d{2}/\\d{2}/\\d{4}$";
        return data.matches(regex);
    }

    private void solicitarDoacao() {
        String tipoSanguineo = JOptionPane.showInputDialog(TelaUsuario.this, "Digite o tipo sanguíneo desejado:");

        if (tipoSanguineo != null && !tipoSanguineo.isEmpty()) {
           
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("solicitacoes.txt", true))) {
                writer.write(nomeBanco + "," + tipoSanguineo);
                writer.newLine();
                JOptionPane.showMessageDialog(TelaUsuario.this, "Solicitação de Doação enviada para o Gestor Central.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(TelaUsuario.this, "Erro ao salvar a solicitação.");
            }
        } else {
            JOptionPane.showMessageDialog(TelaUsuario.this, "Tipo sanguíneo inválido.");
        }
    }
    private void openTelaLogin() {
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.main(null);
        dispose();
    }     

}



