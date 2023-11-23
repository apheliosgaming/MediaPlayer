package br.ufrn.imd.DAO;

import br.ufrn.imd.models.Musica;

import java.sql.Connection;
import java.util.List;

public class MusicaDAO implements DAOI {
    private Connection connection; // Guarda a conexão com o banco

    public MusicaDAO(){
        //fazer conexão com o banco
    }

    public Musica getById(int id){
        // query
    }

    public List<Musica> listar(Musica musica){
        // query
    }

    public void deletar(Musica musica){
        // query
    }

    void inserir(Musica musica){
        //query
    }
}
