package br.ufrn.imd.DAO;

import br.ufrn.imd.models.Usuario;

import java.sql.Connection;
import java.util.List;

public class UsuarioDAO implements DAOI{
    private Connection connection; // Guarda a conexão com o banco

    public UsuarioDAO(){
        //fazer conexão com o banco
    }

    public Usuario getById(int id){
        // query
    }

    public List<Usuario> listar(Usuario usuario){
        // query
        return usuario;
    }

    void deletar(Usuario usuario){
        // query
    }

    void inserir(Usuario usuario){
        // query
    }

    // Método próprio de usuário, serve para a autenticação no login
    void autenticar(String email, String senha){
        // autenticar
    }
}
