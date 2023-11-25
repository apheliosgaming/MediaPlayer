package br.ufrn.imd.models;

import br.ufrn.imd.models.Playlist;

import java.util.List;

public class UsuarioVIP extends Usuario {
    private double codigo;
    private List<Playlist> playlists;

    public UsuarioVIP(String username, String email, String senha, double codigo) {
        super(username, email, senha);
        this.codigo = codigo;
    }

    public double getCodigo() {
        return codigo;
    }

    public void setCodigo(double codigo) {
        this.codigo = codigo;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }
}
    
