package Programa;


import java.util.ArrayList;
import java.util.List;

public class GestorCentral {
	private List<BancoDeSangue> bancos = new ArrayList<>();

    private List<Doacao> solicitacoes;

    public GestorCentral() {
        this.bancos = new ArrayList<>();
        this.solicitacoes = new ArrayList<>();
    }
    public List<BancoDeSangue> getListaBancos() {
        return bancos;
    }

   
    public void adicionarDoacao(BancoDeSangue banco, Doacao doacao) {
        banco.adicionarDoacao(doacao);
    }

    public void excluirDoacao(BancoDeSangue banco, Doacao doacao) {
        banco.excluirDoacao(doacao);
    }

    
    public void modificarDoacao(BancoDeSangue banco, Doacao doacaoAntiga, Doacao doacaoNova) {
        banco.excluirDoacao(doacaoAntiga);
        banco.adicionarDoacao(doacaoNova);
    }

   
    public void adicionarBanco(BancoDeSangue banco) {
        bancos.add(banco);
    }

    public void excluirBanco(BancoDeSangue banco) {
        bancos.remove(banco);
    }

   
    public void modificarBanco(BancoDeSangue bancoAntigo, BancoDeSangue bancoNovo) {
        int index = bancos.indexOf(bancoAntigo);
        if (index != -1) {
            bancos.set(index, bancoNovo);
        }
    }

    
    public void transferirDoacao(BancoDeSangue origem, BancoDeSangue destino, Doacao doacao) {
        origem.excluirDoacao(doacao);
        destino.adicionarDoacao(doacao);
    }
    public List<BancoDeSangue> visualizarBancos() {
        return bancos;
    }

    public List<Doacao> visualizarSolicitacoes() {
        return solicitacoes;
    }
    public List<Doacao> getListaSolicitacoes() {
        return solicitacoes;
    }

    
}