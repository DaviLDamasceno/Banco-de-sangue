package Programa;


import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


public class GestorCentralFrame extends JFrame {

    /**
	 * 
	 */
	private List<String> solicitacoesDoacao;

	private static final long serialVersionUID = 1L;
	
    private JButton btnAdicionarBanco;
    private JButton btnExcluirBanco;
    private JButton btnModificarBanco;
    private JButton btnTransferirDoacao;
    private JButton btnLogout;
    private JButton btnVisualizarSolicitacoes;
    private JTextArea textAreaSolicitacoes;
    

    

    public GestorCentralFrame() {
    	solicitacoesDoacao = new ArrayList<>();
        initialize();
        
    }

    private void initialize() {
        setTitle("Gestor Central");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 3));
        textAreaSolicitacoes = new JTextArea();
        textAreaSolicitacoes.setEditable(false);

        JScrollPane scrollPaneSolicitacoes = new JScrollPane(textAreaSolicitacoes);
        getContentPane().add(scrollPaneSolicitacoes);



        btnAdicionarBanco = new JButton("Adicionar Banco de Sangue");
        btnAdicionarBanco.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String novoNomeBanco = JOptionPane.showInputDialog(GestorCentralFrame.this, "Digite o nome do Banco de Sangue:");
                if (novoNomeBanco != null && !novoNomeBanco.isEmpty()) {
                    BancoDeSangue novoBanco = new BancoDeSangue();
                    novoBanco.setNome(novoNomeBanco);
                    novoBanco.salvarNomeEmArquivoTexto("bancos_de_sangue.txt");

                    JOptionPane.showMessageDialog(GestorCentralFrame.this, "Banco de Sangue adicionado!");
                } else {
                    JOptionPane.showMessageDialog(GestorCentralFrame.this, "Nome do Banco de Sangue inválido!");
                }
            }
        });



        add(btnAdicionarBanco);



       
        btnExcluirBanco = new JButton("Excluir Banco de Sangue");
        btnExcluirBanco.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                List<String> bancosDeSangue = BancoDeSangue.carregarBancosDeSangueDeArquivoTexto("bancos_de_sangue.txt");

                
                String bancoSelecionado = (String) JOptionPane.showInputDialog(
                        GestorCentralFrame.this,
                        "Selecione o Banco de Sangue a ser excluído:",
                        "Excluir Banco de Sangue",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        bancosDeSangue.toArray(),
                        null
                );

                
                if (bancoSelecionado != null && !bancoSelecionado.isEmpty()) {
                    
                    bancosDeSangue.remove(bancoSelecionado);

                   
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("bancos_de_sangue.txt"))) {
                        for (String banco : bancosDeSangue) {
                            writer.write(banco);
                            writer.newLine();
                        }
                    } catch (IOException ex) {
                        System.out.println("Erro ao atualizar o arquivo de bancos de sangue: " + ex.getMessage());
                    }

                    JOptionPane.showMessageDialog(GestorCentralFrame.this, "Banco de Sangue excluído: " + bancoSelecionado);
                } else {
                    JOptionPane.showMessageDialog(GestorCentralFrame.this, "Nenhum banco selecionado.");
                }
            }
        });


        add(btnExcluirBanco);


        
        btnModificarBanco = new JButton("Modificar Banco de Sangue");
        btnModificarBanco.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                List<String> bancosDeSangue = BancoDeSangue.carregarBancosDeSangueDeArquivoTexto("bancos_de_sangue.txt");

               
                String bancoSelecionado = (String) JOptionPane.showInputDialog(
                    GestorCentralFrame.this,
                    "Selecione o Banco de Sangue a ser modificado:",
                    "Modificar Banco de Sangue",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    bancosDeSangue.toArray(),
                    null
                );

                
                if (bancoSelecionado != null && !bancoSelecionado.isEmpty()) {
                  
                    String novoNomeBanco = JOptionPane.showInputDialog(GestorCentralFrame.this, "Digite o novo nome do Banco de Sangue:");
                    if (novoNomeBanco != null && !novoNomeBanco.isEmpty()) {
                     
                        int indice = bancosDeSangue.indexOf(bancoSelecionado);
                        bancosDeSangue.set(indice, novoNomeBanco);

                        
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bancos_de_sangue.txt"))) {
                            for (String banco : bancosDeSangue) {
                                writer.write(banco);
                                writer.newLine();
                            }
                        } catch (IOException ex) {
                            System.out.println("Erro ao atualizar o arquivo de bancos de sangue: " + ex.getMessage());
                        }

                        JOptionPane.showMessageDialog(GestorCentralFrame.this, "Banco de Sangue modificado!");
                    } else {
                        JOptionPane.showMessageDialog(GestorCentralFrame.this, "Nome do Banco de Sangue inválido!");
                    }
                } else {
                    JOptionPane.showMessageDialog(GestorCentralFrame.this, "Nenhum banco selecionado.");
                }
            }
        });

        add(btnModificarBanco);



        btnTransferirDoacao = new JButton("Transferir Doação");
        add(btnTransferirDoacao);
        btnTransferirDoacao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirTelaTransferencia();
            }
        });

        
        btnVisualizarSolicitacoes = new JButton("Visualizar Solicitações");
        add(btnVisualizarSolicitacoes);
        btnVisualizarSolicitacoes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                visualizarSolicitacoes();
            }
        });
        
        JTextArea textAreaSolicitacoes = new JTextArea();
        textAreaSolicitacoes.setEditable(false);
        getContentPane().add(scrollPaneSolicitacoes);
        
        
        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(140, 200, 89, 23);
        getContentPane().add(btnLogout);
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openTelaLogin();
            }
        });

        add(btnLogout);

    }
    
    
    public void receberSolicitacao(String nomeBanco, String tipoSanguineo) {
        
    		String solicitacao = "Banco: " + nomeBanco + " - Tipo Sanguíneo: " + tipoSanguineo;
    		solicitacoesDoacao.add(solicitacao);
    		JTextArea textAreaSolicitacoes = (JTextArea) getContentPane().getComponent(0);
    		textAreaSolicitacoes.append(solicitacao + "\n");
    }

    private void visualizarSolicitacoes() {
        List<String> solicitacoes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("solicitacoes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                solicitacoes.add(line);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(GestorCentralFrame.this, "Erro ao ler as solicitações.");
            return;
        }

        if (!solicitacoes.isEmpty()) {
            JTextArea textAreaSolicitacoes = null;
            Component[] components = getContentPane().getComponents();
            for (Component component : components) {
                if (component instanceof JTextArea) {
                    textAreaSolicitacoes = (JTextArea) component;
                    break;
                }
            }

            if (textAreaSolicitacoes != null) {
                StringBuilder sb = new StringBuilder();
                for (String solicitacao : solicitacoes) {
                    sb.append(solicitacao).append("\n");
                }
                textAreaSolicitacoes.setText(sb.toString()); 
            }

          
            String solicitacaoSelecionada = (String) JOptionPane.showInputDialog(
                    GestorCentralFrame.this,
                    "Selecione uma solicitação:",
                    "Visualizar Solicitações",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    solicitacoes.toArray(),
                    null
            );

            if (solicitacaoSelecionada != null && !solicitacaoSelecionada.isEmpty()) {
                int confirmacao = JOptionPane.showConfirmDialog(
                        GestorCentralFrame.this,
                        "Deseja excluir a solicitação selecionada?",
                        "Excluir Solicitação",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirmacao == JOptionPane.YES_OPTION) {
                    solicitacoes.remove(solicitacaoSelecionada);
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("solicitacoes.txt"))) {
                        for (String solicitacao : solicitacoes) {
                            writer.write(solicitacao);
                            writer.newLine();
                        }
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(GestorCentralFrame.this, "Erro ao excluir a solicitação.");
                    }

                    JOptionPane.showMessageDialog(GestorCentralFrame.this, "Solicitação excluída com sucesso.");
                }
            } else {
                JOptionPane.showMessageDialog(GestorCentralFrame.this, "Nenhuma solicitação selecionada.");
            }
        } else {
            JOptionPane.showMessageDialog(GestorCentralFrame.this, "Não há solicitações de doação.");
        }
        
        
    }
    private void abrirTelaTransferencia() {
        List<String> bancosDeSangue = BancoDeSangue.carregarBancosDeSangueDeArquivoTexto("bancos_de_sangue.txt");
    
        String bancoOrigem = (String) JOptionPane.showInputDialog(
                GestorCentralFrame.this,
                "Selecione o banco de origem:",
                "Transferir Doação",
                JOptionPane.PLAIN_MESSAGE,
                null,
                bancosDeSangue.toArray(),
                null
        );

        if (bancoOrigem != null && !bancoOrigem.isEmpty()) {
        	BancoDeSangue bancoDeSangue = new BancoDeSangue();

        	
        	List<Doacao> doacoesOrigem = bancoDeSangue.carregarDoacoesDeArquivoTexto(bancoOrigem);

            Doacao doacaoSelecionada = (Doacao) JOptionPane.showInputDialog(
                    GestorCentralFrame.this,
                    "Selecione a doação a ser transferida:",
                    "Transferir Doação",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    doacoesOrigem.toArray(),
                    null
            );

            if (doacaoSelecionada != null) {
                
                String bancoDestino = (String) JOptionPane.showInputDialog(
                        GestorCentralFrame.this,
                        "Selecione o banco de destino:",
                        "Transferir Doação",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        bancosDeSangue.toArray(),
                        null
                );

                if (bancoDestino != null && !bancoDestino.isEmpty()) {
                    
                    BancoDeSangue.transferirDoacao(bancoOrigem, bancoDestino, doacaoSelecionada);

                    JOptionPane.showMessageDialog(GestorCentralFrame.this, "Doação transferida com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(GestorCentralFrame.this, "Nenhum banco de destino selecionado.");
                }
            } else {
                JOptionPane.showMessageDialog(GestorCentralFrame.this, "Nenhuma doação selecionada.");
            }
        } else {
            JOptionPane.showMessageDialog(GestorCentralFrame.this, "Nenhum banco de origem selecionado.");
        }
    }
    private void openTelaLogin() {
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.main(null);
        dispose();
    }

    
public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GestorCentralFrame frame = new GestorCentralFrame();
                frame.setVisible(true);
            }
        });
    }
}





