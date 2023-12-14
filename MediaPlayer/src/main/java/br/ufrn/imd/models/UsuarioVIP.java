package br.ufrn.imd.models;

import br.ufrn.imd.models.Playlist;

import java.util.List;

/**
 * Uma classe que representa um usuário VIP, que estende a classe Usuario.
 * Além das características de um usuário padrão, um usuário VIP possui um código e uma lista de playlists.
 */
public class UsuarioVIP extends Usuario {
    private double codigo;
    private List<Playlist> playlists;

    /**
     * Constrói um novo objeto UsuarioVIP com os parâmetros especificados, estendendo a classe Usuario.
     *
     * @param id O identificador único do usuário.
     * @param username O nome de usuário escolhido pelo usuário.
     * @param email O endereço de e-mail associado ao usuário.
     * @param senha A senha do usuário.
     */
    public UsuarioVIP(int id, String username, String email, String senha) {
        super(id, username, email, senha);
        this.codigo = codigo;
    }

    /**
     * Obtém a lista de playlists do usuário VIP.
     *
     * @return A lista de playlists do usuário VIP.
     */
    public List<Playlist> getPlaylists() {
        return playlists;
    }

    /**
     * Define a lista de playlists para o usuário VIP.
     *
     * @param playlists A nova lista de playlists para o usuário VIP.
     */
    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }
}
    
