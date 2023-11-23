package br.ufrn.imd.models;

import java.util.ArrayList;

public class Playlist {
    private String nome;
    private ArrayList<Musica> musicas;
    
    public Playlist(String nome) {
        this.nome = nome;
        this.musicas = new ArrayList<Musica>();
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
     public ArrayList<Musica> getMusicas() {
        return this.musicas;
    }

    public void addMusica(Musica musica) {
        this.musicas.add(musica);
    }
    
    public void addMusica(Musica musica) {
        this.musicas.add(musica);
    }
    public void removeMusica(Musica musica){
    }
}
