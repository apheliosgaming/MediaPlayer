package br.ufrn.imd.models;

import java.util.ArrayList;
import br.ufrn.imd.models.Musica;

/**
 * Uma classe que representa uma Playlist .
 * Cada playlist possui um ID, um nome, um dono (usuário VIP) e uma lista de músicas.
 */
public class Playlist {
    private int id;
    private String nome;
    private UsuarioVIP dono;
    private ArrayList<Musica> musicas;

    /**
     * Constrói um novo objeto Playlist com os parâmetros especificados.
     *
     * @param id O identificador único da playlist.
     * @param nome O nome da playlist.
     * @param dono O dono da playlist (usuário VIP).
     */
    public Playlist(int id, String nome, UsuarioVIP dono) {
        this.id = id;
        this.nome = nome;
        this.dono = dono;
        this.musicas = new ArrayList<Musica>();
    }

    /**
     * Obtém o identificador único da playlist.
     *
     * @return O ID da playlist.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador único para a playlist.
     *
     * @param id O novo ID para a playlist.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o nome da playlist.
     *
     * @return O nome da playlist.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome para a playlist.
     *
     * @param nome O novo nome para a playlist.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o dono (usuário VIP) da playlist.
     *
     * @return O dono da playlist.
     */
    public UsuarioVIP getDono() {
        return dono;
    }

    /**
     * Define o dono (usuário VIP) para a playlist.
     *
     * @param dono O novo dono (usuário VIP) para a playlist.
     */
    public void setDono(UsuarioVIP dono) {
        this.dono = dono;
    }

    /**
     * Obtém a lista de músicas na playlist.
     *
     * @return A lista de músicas na playlist.
     */
    public ArrayList<Musica> getMusicas() {
        return this.musicas;
    }

    /**
     * Adiciona uma música à playlist.
     *
     * @param musica A música a ser adicionada à playlist.
     */
    public void addMusica(Musica musica) {
        this.musicas.add(musica);
    }

    /**
     * Remove uma música da playlist.
     *
     * @param musica A música a ser removida da playlist.
     */
    public void removeMusica(Musica musica){
    }
}
