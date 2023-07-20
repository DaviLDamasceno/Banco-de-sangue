package Programa;

import java.io.Serializable;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Doacao implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tipoSanguineo;
    private String dataColeta;
    private String id;

    public Doacao(String tipoSanguineo, String dataColeta, String id) {
        this.tipoSanguineo = tipoSanguineo;
        this.dataColeta = new String(dataColeta);
        this.id = id;
    }

    public Doacao(String tipoSanguineo, String dataColeta) {
        this.tipoSanguineo = tipoSanguineo;
        this.dataColeta = dataColeta;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public String getDataColeta() {
        return dataColeta;
    }

    public String getId() {
        return id;
    }

    public void setTipoSanguineo(String novoTipoSanguineo) {
        this.tipoSanguineo = novoTipoSanguineo;
    }

    public void setDataColeta(String novaDataColeta) {
        this.dataColeta = novaDataColeta;
    }

    public void setId(String novoId) {
        this.id = novoId;
    }
    public void salvarEmArquivoTexto(String nomeArquivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo, true))) {
            writer.println(tipoSanguineo + "," + dataColeta);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Doacao other = (Doacao) obj;
        return id.equals(other.id);
    }
    @Override
    public String toString() {
        return "Tipo Sanguíneo: " + tipoSanguineo + ", Data da validade: " + dataColeta + ", ID: " + id;
    }


}
