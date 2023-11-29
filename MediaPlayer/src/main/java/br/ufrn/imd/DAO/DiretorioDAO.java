package br.ufrn.imd.DAO;

import br.ufrn.imd.models.Diretorio;
import br.ufrn.imd.models.Playlist;
import br.ufrn.imd.models.Usuario;
import br.ufrn.imd.models.UsuarioVIP;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que implementa a interface DAOI para manipulação de objetos Diretorio no banco de dados.
 */
public class DiretorioDAO implements DAOI<Diretorio> {

    private Connection connection; // Guarda a conexão com o banco

    /**
     * Construtor da classe que estabelece a conexão com o banco de dados SQLite.
     */
    public DiretorioDAO() {
        String jdbcUrl = "jdbc:sqlite:mediaplayer.db";
        try {
            this.connection = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            System.out.println("Não foi possivel conectar com a db");
            e.printStackTrace();
        }
    }

    /**
     * Método para criar um novo diretorio no banco de dados.
     *
     * @param diretorio O objeto Diretorio a ser criado.
     * @return true se a criação for bem-sucedida, false caso contrário.
     */
    @Override
    public boolean create(Diretorio diretorio) {
        String sql = "INSERT INTO diretorios(usuario_id, nome, caminho) VALUES(?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, diretorio.getDono().getId());
            stmt.setString(2, diretorio.getNome());
            stmt.setString(3, diretorio.getCaminho());
            stmt.execute();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método para obter um diretorio pelo ID no banco de dados.
     *
     * @param id O ID do diretorio a ser recuperado.
     * @return Um objeto Diretorio se encontrado, ou null se não encontrado.
     */
    @Override
    public Diretorio getById(int id) {
        String sql = "SELECT * FROM diretorios WHERE diretorio_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet diretorio = stmt.executeQuery();
            while (diretorio.next()){
                String nome = diretorio.getString("nome");
                int idDono = diretorio.getInt("usuario_id");
                String caminho = diretorio.getString("caminho");

                UsuarioDAO udao = new UsuarioDAO();
                Usuario dono = udao.getById(idDono);
                return new Diretorio(id, dono, nome, caminho);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Método para obter todos as diretorios do banco de dados.
     *
     * @return Uma lista de objetos Diretorio.
     */
    @Override
    public List<Diretorio> getAll() {
        String sql = "SELECT * FROM diretorios";
        List<Diretorio> diretorios = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()){
                int idDiretorio = resultado.getInt("diretorio_id");
                String nome = resultado.getString("nome");
                int idDono = resultado.getInt("usuario_id");
                String caminho = resultado.getString("caminho");

                UsuarioDAO udao = new UsuarioDAO();
                Usuario dono = udao.getById(idDono);

                diretorios.add(new Diretorio(idDiretorio, dono, nome, caminho));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return diretorios;
    }

    /**
     * Método para atualizar um diretorio no banco de dados.
     *
     * @param diretorio O objeto Diretorio a ser atualizado.
     * @return true se a atualização for bem-sucedida, false caso contrário.
     */
    @Override
    public boolean update(Diretorio diretorio) {
        String sql = "UPDATE diretorios SET usuario_id=?, nome=?, caminho=? WHERE diretorio_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, diretorio.getDono().getId());
            stmt.setString(2, diretorio.getNome());
            stmt.setString(3, diretorio.getCaminho());
            stmt.setInt(4, diretorio.getId());

            stmt.execute();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método para excluir uma diretorio do banco de dados pelo ID.
     *
     * @param id O ID da diretorio a ser excluída.
     * @return true se a exclusão for bem-sucedida, false caso contrário.
     */
    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM diretorios WHERE diretorio_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método para obter todos os diretorios de um usuário.
     *
     * @param usuario O usuário dono da playlist.
     * @return Uma lista de objetos Diretorio.
     */
    public List<Diretorio> getAllUserDirectories(Usuario usuario){
        String sql = "SELECT * FROM diretorios WHERE usuario_id=?";
        List<Diretorio> diretorios = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, usuario.getId());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()){
                int idDiretorio = resultado.getInt("diretorio_id");
                String nome = resultado.getString("nome");
                int idDono = resultado.getInt("usuario_id");
                String caminho = resultado.getString("caminho");

                UsuarioDAO udao = new UsuarioDAO();
                Usuario dono = udao.getById(idDono);

                diretorios.add(new Diretorio(idDiretorio, dono, nome, caminho));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return diretorios;
    }
}
