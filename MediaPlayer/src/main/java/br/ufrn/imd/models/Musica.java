package br.ufrn.imd.models;

public class Musica{
    private int id;
    private Usuario dono;
    private String titulo;
    private double duracao;
    private String caminho;

    public Musica(int id, Usuario dono, String titulo, double duracao, String caminho) {
        this.id = id;
        this.dono = dono;
        this.titulo = titulo;
        this.duracao = duracao;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }
}
