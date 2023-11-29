package br.ufrn.imd.models;

import br.ufrn.imd.models.Musica;

public class Diretorio {
    private int id;
    private Usuario dono;
    private String nome;
    private String caminho;

    public Diretorio(int id, Usuario dono, String nome, String caminho) {
        this.id = id;
        this.dono = dono;
        this.nome = nome;
        this.caminho = caminho;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getDono() {
        return dono;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }
}
