package br.ufrn.imd.DAO;

import br.ufrn.imd.models.Diretorio;

import java.sql.Connection;
import java.util.List;

public class DiretorioDAO implements DAOI{
    private Connection connection; // Guarda a conexão com o banco

    public DiretorioDAO(){
        //fazer conexão com o banco
    }

    public Diretorio getById(int id){
        // query
    }

    public List<Diretorio> listar(Diretorio diretorio){
        // query
    }

    void deletar(Diretorio diretorio){
        // query
    }

    void inserir(Diretorio diretorio){
        // query
    }
}
