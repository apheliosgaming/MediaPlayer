package br.ufrn.imd.DAO;

import br.ufrn.imd.models.Musica;
import br.ufrn.imd.models.Playlist;
import br.ufrn.imd.models.UsuarioVIP;

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
        String sql = "INSERT INTO musicas(titulo, artista, duracao, caminho) VALUES(?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, musica.getTitulo());
            stmt.setString(2, musica.getArtista());
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
            while (musica.next()){
                int idMusica = musica.getInt("musica_id");
                String titulo = musica.getString("titulo");
                String artista = musica.getString("artista");
                double duracao = musica.getDouble("duracao");
                String caminho = musica.getString("caminho");

                return new Musica(idMusica, titulo, artista, duracao, caminho);
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
            while (resultado.next()){
                int idMusica = resultado.getInt("musica_id");
                String titulo = resultado.getString("titulo");
                String artista = resultado.getString("artista");
                double duracao = resultado.getDouble("duracao");
                String caminho = resultado.getString("caminho");

                musicas.add(new Musica(idMusica, titulo, artista, duracao, caminho));
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
        String sql = "UPDATE musicas SET titulo=?, artista=?, duracao=?, caminho=? WHERE musica_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, musica.getTitulo());
            stmt.setString(2, musica.getArtista());
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
}
