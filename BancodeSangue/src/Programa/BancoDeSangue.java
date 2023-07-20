package Programa;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

public class BancoDeSangue {
	
    private Estoque estoque;
    private String nome;
    private List<Doacao> doacoes;
    


    public String getNome() {
        return nome;
    }

    public void setNome(String novoNomeBanco) {
        this.nome = novoNomeBanco;
    }
    public BancoDeSangue() {
        this.estoque = new Estoque();
        this.doacoes = new ArrayList<>();
    }

    
    public void adicionarDoacao(Doacao doacao) {
        estoque.adicionarDoacao(doacao);
    }

    public void excluirDoacao(Doacao doacao) {
        estoque.excluirDoacao(doacao);
    }



    public List<Doacao> visualizarDoacoes() {
        return estoque.getDoacoes();
    }

    public void solicitarDoacao(BancoDeSangue outroBanco, Doacao doacao) {
        outroBanco.excluirDoacao(doacao);
        estoque.adicionarDoacao(doacao);
    }
    

    public void salvarNomeEmArquivoTexto(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
            writer.write(nome);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar o nome do Banco de Sangue no arquivo: " + e.getMessage());
        }
    }
    public static List<String> carregarBancosDeSangueDeArquivoTexto(String nomeArquivo) {
        List<String> bancosDeSangue = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                bancosDeSangue.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar os bancos de sangue do arquivo: " + e.getMessage());
        }

        return bancosDeSangue;
    }
    public static void salvarDoacaoEmArquivoTexto(Doacao doacao, String nomeBanco) {
        String nomeArquivo = nomeBanco + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
            String linha = doacao.getTipoSanguineo() + "," + doacao.getDataColeta() + "," + doacao.getId();
            writer.write(linha);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean excluirDoacaoPorId(String idDoacao, String nomeBanco) {
        Iterator<Doacao> iterator = doacoes.iterator();
        while (iterator.hasNext()) {
            Doacao doacao = iterator.next();
            if (doacao.getId().equals(idDoacao)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }


   

    public static void salvarDoacoesEmArquivoTexto(List<Doacao> doacoes, String nomeBanco) {
        String nomeArquivo = nomeBanco + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Doacao doacao : doacoes) {
                String linha = doacao.getTipoSanguineo() + "," + doacao.getDataColeta() + "," + doacao.getId();
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar as doações no arquivo: " + e.getMessage());
        }
    }
    public List<Doacao> carregarDoacoesDeArquivoTexto(String nomeBanco) {
        List<Doacao> doacoes = new ArrayList<>();
        String nomeArquivo = nomeBanco + ".txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                String tipoSanguineo = dados[0];
                String dataColeta = dados[1];
                String id = dados[2];

                Doacao doacao = new Doacao(tipoSanguineo, dataColeta, id);
                doacoes.add(doacao);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar as doações do arquivo: " + e.getMessage());
        }

        return doacoes;
    }
    public static void transferirDoacao(String bancoOrigem, String bancoDestino, Doacao doacao) {
    	BancoDeSangue bancoDeSangue = new BancoDeSangue();
    	List<Doacao> doacoesOrigem = bancoDeSangue.carregarDoacoesDeArquivoTexto(bancoOrigem);
        doacoesOrigem.remove(doacao);
        salvarDoacoesEmArquivoTexto(doacoesOrigem, bancoOrigem);
        List<Doacao> doacoesDestino = bancoDeSangue.carregarDoacoesDeArquivoTexto(bancoDestino);
        doacoesDestino.add(doacao);
        salvarDoacoesEmArquivoTexto(doacoesDestino, bancoDestino);
    }



}


