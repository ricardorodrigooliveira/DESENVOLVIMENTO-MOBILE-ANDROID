package br.com.ricardooliveira.exemplorecycleview;

public class Contato {
    public String nome;
    public String telefone;
    public String status;
    public int imagem;


    public Contato(String nome) {
        this.nome = nome;
    }

    public Contato(String nome, String telefone, String status, int imagem) {
        this.nome = nome;
        this.telefone = telefone;
        this.status = status;
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
