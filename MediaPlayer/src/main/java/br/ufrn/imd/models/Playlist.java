package br.ufrn.imd.models;

import java.util.ArrayList;
import br.ufrn.imd.models.Musica;

public class Playlist {
    private int id;
    private String nome;
    private UsuarioVIP dono;
    private ArrayList<Musica> musicas;

    public Playlist(int id, String nome, UsuarioVIP dono) {
        this.id = id;
        this.nome = nome;
        this.dono = dono;
        this.musicas = new ArrayList<Musica>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UsuarioVIP getDono() {
        return dono;
    }

    public void setDono(UsuarioVIP dono) {
        this.dono = dono;
    }

    public ArrayList<Musica> getMusicas() {
        return this.musicas;
    }

    public void addMusica(Musica musica) {
        this.musicas.add(musica);
    }
    
    public void removeMusica(Musica musica){
    }
}
