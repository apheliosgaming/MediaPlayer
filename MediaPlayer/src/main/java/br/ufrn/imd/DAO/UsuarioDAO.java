package br.ufrn.imd.DAO;

import br.ufrn.imd.models.Usuario;
import br.ufrn.imd.models.UsuarioVIP;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que implementa a interface DAOI para manipulação de objetos Usuario no banco de dados.
 */
public class UsuarioDAO implements DAOI<Usuario>{
    private Connection connection;

    /**
     * Construtor da classe que estabelece a conexão com o banco de dados SQLite.
     */
    public UsuarioDAO(){
        String jdbcUrl = "jdbc:sqlite:mediaplayer.db";
        try {
            this.connection = DriverManager.getConnection(jdbcUrl);
            System.out.println("Conexão com db estabelecida");
        } catch (SQLException e) {
            System.out.println("Não foi possivel conectar com a db");
            e.printStackTrace();
        }
    }

    /**
     * Método para criar um novo usuário no banco de dados.
     *
     * @param usuario O objeto Usuario a ser criado.
     * @return true se a criação for bem-sucedida, false caso contrário.
     */
    @Override
    public boolean create(Usuario usuario){
        String sql = "INSERT INTO usuarios(email, senha, username, vip) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getUsername());
            if (usuario instanceof UsuarioVIP){
                stmt.setInt(4, 1);
            } else {
                stmt.setInt(4, 0);
            }
            stmt.execute();
            return true;

        } catch (SQLException e){
            if (e.getMessage().contains("SQLITE_CONSTRAINT_UNIQUE")) {
                System.err.println("Esse email já está cadastrado.");
            } else{
                e.printStackTrace();
            }
            return false;
        }
    }

    /**
     * Método para obter um usuário pelo ID no banco de dados.
     *
     * @param id O ID do usuário a ser recuperado.
     * @return Um objeto Usuario ou UsuarioVIP se encontrado, ou null se não encontrado.
     */
    @Override
    public Usuario getById(int id){
        String sql = "SELECT * FROM usuarios WHERE usuario_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet usuario = stmt.executeQuery();
            while (usuario.next()){
                String username = usuario.getString("username");
                String email = usuario.getString("email");
                String senha = usuario.getString("senha");
                int isVIP = usuario.getInt("vip");
                // TODO: Ver quais dados precisam ser resgatados
                if (isVIP == 0){
                    return new Usuario(username, email, senha);
                } else {
                    return new UsuarioVIP(username, email, senha, 1);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Método para obter todos os usuários do banco de dados.
     *
     * @return Uma lista de objetos Usuario.
     */
    @Override
    public List<Usuario> getAll(){
        String sql = "SELECT * FROM usuarios";
        List<Usuario> usuarios = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()){
                int id = resultado.getInt("usuario_id");
                String username = resultado.getString("username");
                String email = resultado.getString("email");
                String senha = resultado.getString("senha");
                int isVIP = resultado.getInt("vip");
                if (isVIP == 0){
                    usuarios.add(new Usuario(username, email, senha));
                } else {
                    usuarios.add(new UsuarioVIP(username, email, senha, 1));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return usuarios;
    }

    /**
     * Método para atualizar um usuário no banco de dados.
     *
     * @param usuario O objeto Usuario a ser atualizado.
     * @return true se a atualização for bem-sucedida, false caso contrário.
     */
    @Override
    public boolean update(Usuario usuario){
        String sql = "UPDATE usuarios SET username=?, email=?, senha=?, vip=? WHERE usuario_id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            if (usuario instanceof UsuarioVIP){
                stmt.setInt(4, 1);
            } else {
                stmt.setInt(4, 0);
            }
            stmt.setInt(5, usuario.getId());
            stmt.execute();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método para excluir um usuário do banco de dados pelo ID.
     *
     * @param id O ID do usuário a ser excluído.
     * @return true se a exclusão for bem-sucedida, false caso contrário.
     */
    @Override
    public boolean delete(int id){
        String sql = "DELETE FROM usuarios WHERE usuario_id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método específico de usuário para autenticação no login.
     *
     * @param email O email do usuário.
     * @param senha A senha do usuário.
     * @return true se a autenticação for bem-sucedida, false caso contrário.
     */
    public boolean autenticar(String email, String senha){
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            try(ResultSet resultado = stmt.executeQuery()){
                return resultado.next();
            }
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
