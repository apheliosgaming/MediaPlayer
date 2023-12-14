package br.ufrn.imd.models;

/**
 * Uma classe que representa uma música.
 * Cada música possui um ID, um dono (usuário), um título, uma duração e um caminho de arquivo.
 */
public class Musica{
    private int id;
    private Usuario dono;
    private String titulo;
    private double duracao;
    private String caminho;

    /**
     * Constrói um novo objeto Musica com os parâmetros especificados.
     *
     * @param id O identificador único da música.
     * @param dono O dono da música (usuário).
     * @param titulo O título da música.
     * @param duracao A duração da música em minutos.
     * @param caminho O caminho do arquivo da música.
     */
    public Musica(int id, Usuario dono, String titulo, double duracao, String caminho) {
        this.id = id;
        this.dono = dono;
        this.titulo = titulo;
        this.duracao = duracao;
        this.caminho = caminho;
    }

    /**
     * Obtém o identificador único da música.
     *
     * @return O ID da música.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador único para a música.
     *
     * @param id O novo ID para a música.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o dono (usuário) da música.
     *
     * @return O dono da música.
     */
    public Usuario getDono() {
        return dono;
    }

    /**
     * Define o dono (usuário) para a música.
     *
     * @param dono O novo dono (usuário) para a música.
     */
    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    /**
     * Obtém o título da música.
     *
     * @return O título da música.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Define o título para a música.
     *
     * @param titulo O novo título para a música.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtém a duração da música em minutos.
     *
     * @return A duração da música.
     */
    public double getDuracao() {
        return duracao;
    }

    /**
     * Define a duração para a música.
     *
     * @param duracao A nova duração para a música em minutos.
     */
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    /**
     * Obtém o caminho do arquivo da música.
     *
     * @return O caminho do arquivo da música.
     */
    public String getCaminho() {
        return caminho;
    }

    /**
     * Define o caminho do arquivo para a música.
     *
     * @param caminho O novo caminho do arquivo para a música.
     */
    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }
}
