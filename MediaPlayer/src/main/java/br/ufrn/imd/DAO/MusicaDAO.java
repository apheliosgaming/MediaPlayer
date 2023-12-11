package br.ufrn.imd.DAO;

import br.ufrn.imd.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que implementa a interface DAOI para manipulação de objetos Musica no banco de dados.
 */
public class MusicaDAO implements DAOI<Musica> {

    private Connection connection; // Guarda a conexão com o banco

    /**
     * Construtor da classe que estabelece a conexão com o banco de dados SQLite.
     */
    public MusicaDAO() {
        String jdbcUrl = "jdbc:sqlite:mediaplayer.db";
        try {
            this.connection = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            System.out.println("Não foi possivel conectar com a db");
            e.printStackTrace();
        }
    }

    /**
     * Método para criar uma nova musica no banco de dados.
     *
     * @param musica O objeto Musica a ser criado.
     * @return true se a criação for bem-sucedida, false caso contrário.
     */
    @Override
    public boolean create(Musica musica) {
        String sql = "INSERT INTO musicas(titulo, dono, duracao, caminho) VALUES(?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, musica.getTitulo());
            stmt.setInt(2, musica.getDono().getId());
            stmt.setDouble(3, musica.getDuracao());
            stmt.setString(4, musica.getCaminho());

            stmt.execute();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método para obter uma musica pelo ID no banco de dados.
     *
     * @param id O ID da musica a ser recuperada.
     * @return Um objeto Musica se encontrado, ou null se não encontrado.
     */
    @Override
    public Musica getById(int id) {
        String sql = "SELECT * FROM musicas WHERE musica_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet musica = stmt.executeQuery();
            UsuarioDAO udao = new UsuarioDAO();
            while (musica.next()){
                int idMusica = musica.getInt("musica_id");
                String titulo = musica.getString("titulo");
                int idDono = musica.getInt("dono");
                double duracao = musica.getDouble("duracao");
                String caminho = musica.getString("caminho");

                Usuario dono = udao.getById(idDono);

                return new Musica(idMusica, dono, titulo, duracao, caminho);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Método para obter todos as musicas do banco de dados.
     *
     * @return Uma lista de objetos Musica.
     */
    @Override
    public List<Musica> getAll() {
        String sql = "SELECT * FROM musicas";
        List<Musica> musicas = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet resultado = stmt.executeQuery();
            UsuarioDAO udao = new UsuarioDAO();
            while (resultado.next()){
                int idMusica = resultado.getInt("musica_id");
                String titulo = resultado.getString("titulo");
                int idDono = resultado.getInt("dono");
                double duracao = resultado.getDouble("duracao");
                String caminho = resultado.getString("caminho");

                Usuario dono = udao.getById(idDono);

                musicas.add(new Musica(idMusica, dono, titulo, duracao, caminho));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return musicas;
    }

    /**
     * Método para atualizar uma música no banco de dados.
     *
     * @param musica O objeto Musica a ser atualizado.
     * @return true se a atualização for bem-sucedida, false caso contrário.
     */
    @Override
    public boolean update(Musica musica) {
        String sql = "UPDATE musicas SET titulo=?, dono=?, duracao=?, caminho=? WHERE musica_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, musica.getTitulo());
            stmt.setInt(2, musica.getDono().getId());
            stmt.setDouble(3, musica.getDuracao());
            stmt.setString(4, musica.getCaminho());
            stmt.setInt(5, musica.getId());
            stmt.execute();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método para excluir uma musica do banco de dados pelo ID.
     *
     * @param id O ID da musica a ser excluída.
     * @return true se a exclusão for bem-sucedida, false caso contrário.
     */
    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM musicas WHERE musica_id=?";
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
     * Método para obter todos as musicas de um usuário.
     *
     * @param usuario O usuário dono da musica.
     * @return Uma lista de objetos Musica.
     */
    public List<Musica> getAllUserMusics(Usuario usuario){
        String sql = "SELECT * FROM musicas WHERE dono=?";
        List<Musica> musicas = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, usuario.getId());
            ResultSet resultado = stmt.executeQuery();
            UsuarioDAO udao = new UsuarioDAO();
            while (resultado.next()){
                int idMusica = resultado.getInt("musica_id");
                String titulo = resultado.getString("titulo");
                int idDono = resultado.getInt("dono");
                double duracao = resultado.getDouble("duracao");
                String caminho = resultado.getString("caminho");

                Usuario dono = udao.getById(idDono);

                musicas.add(new Musica(idMusica, dono, titulo, duracao, caminho));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return musicas;
    }

    /**
     * Método para obter uma musica pelo titulo no banco de dados.
     *
     * @param tituloBusca O titulo da musica a ser recuperado.
     * @return Um objeto Usuario ou UsuarioVIP se encontrado, ou null se não encontrado.
     */
    public Musica getByTitulo(String tituloBusca){
        String sql = "SELECT * FROM musicas WHERE titulo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, tituloBusca);
            ResultSet resultado = stmt.executeQuery();
            UsuarioDAO udao = new UsuarioDAO();
            while (resultado.next()){
                int idMusica = resultado.getInt("musica_id");
                String titulo = resultado.getString("titulo");
                int idDono = resultado.getInt("dono");
                double duracao = resultado.getDouble("duracao");
                String caminho = resultado.getString("caminho");

                Usuario dono = udao.getById(idDono);

                return new Musica(idMusica, dono, titulo, duracao, caminho);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
