package br.ufrn.imd.DAO;

import br.ufrn.imd.models.Usuario;

import java.sql.Connection;
import java.util.List;

public class PlaylistDAO implements DAOI{
    private Connection connection; // Guarda a conexão com o banco

    public playlistDAO(){
        //fazer conexão com o banco
    }

    public Usuario getById(int id){
        // query
    }

    public Playlist getById(int id){
        //query
    }

    public List<Playlist> listar(Playlist obj){
        // query
    }

    public void deletar(Playlist obj){
        // query
    }

    void inserir(Playlist obj){
        //query
    }

}
