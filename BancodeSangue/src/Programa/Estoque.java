package Programa;

import java.util.ArrayList;
import java.util.List;



public class Estoque {
    private List<Doacao> doacoes;

    public Estoque() {
        this.doacoes = new ArrayList<>();
    }

    // Método para adicionar uma doação ao estoque
    public void adicionarDoacao(Doacao doacao) {
        doacoes.add(doacao);
    }

    // Método para excluir uma doação do estoque
    public void excluirDoacao(Doacao doacao) {
        doacoes.remove(doacao);
    }

    // Método para obter a lista de doações no estoque
    public List<Doacao> getDoacoes() {
        return doacoes;
    }
}

