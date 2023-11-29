package br.ufrn.imd.DAO;

import br.ufrn.imd.models.Musica;
import br.ufrn.imd.models.Playlist;
import br.ufrn.imd.models.Usuario;
import br.ufrn.imd.models.UsuarioVIP;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que implementa a interface DAOI para manipulação de objetos Playlist no banco de dados.
 */
public class PlaylistDAO implements DAOI<Playlist>{
    private Connection connection;

    /**
     * Construtor da classe que estabelece a conexão com o banco de dados SQLite.
     */
    public PlaylistDAO(){
        String jdbcUrl = "jdbc:sqlite:mediaplayer.db";
        try {
            this.connection = DriverManager.getConnection(jdbcUrl);
            //System.out.println("Conexão com db estabelecida");
        } catch (SQLException e) {
            System.out.println("Não foi possivel conectar com a db");
            e.printStackTrace();
        }
    }

    /**
     * Método para criar uma nova playlist no banco de dados.
     *
     * @param playlist O objeto Playlist a ser criado.
     * @return true se a criação for bem-sucedida, false caso contrário.
     */
    @Override
    public boolean create(Playlist playlist) {
        String sql = "INSERT INTO playlists(usuario_id, nome) VALUES(?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, playlist.getDono().getId());
            stmt.setString(2, playlist.getNome());
            stmt.execute();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método para obter uma playlist pelo ID no banco de dados.
     *
     * @param id O ID da playlist a ser recuperada.
     * @return Um objeto Playlist se encontrado, ou null se não encontrado.
     */
    @Override
    public Playlist getById(int id) {
        String sql = "SELECT * FROM playlists WHERE playlist_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet playlist = stmt.executeQuery();
            while (playlist.next()){
                int idPlaylist = playlist.getInt("playlist_id");
                String nome = playlist.getString("nome");
                int idDono = playlist.getInt("usuario_id");

                UsuarioDAO udao = new UsuarioDAO();
                UsuarioVIP dono = (UsuarioVIP) udao.getById(idDono);
                return new Playlist(id, nome, dono);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Método para obter todos as playlists do banco de dados.
     *
     * @return Uma lista de objetos Playlist.
     */
    @Override
    public List<Playlist> getAll() {
        String sql = "SELECT * FROM playlists";
        List<Playlist> playlists = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()){
                int idPlaylist = resultado.getInt("playlist_id");
                String nome = resultado.getString("nome");
                int idDono = resultado.getInt("usuario_id");

                UsuarioDAO udao = new UsuarioDAO();
                UsuarioVIP dono = (UsuarioVIP) udao.getById(idDono);

                playlists.add(new Playlist(idPlaylist, nome, dono));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return playlists;
    }

    /**
     * Método para atualizar uma playlist no banco de dados.
     *
     * @param playlist O objeto Playlist a ser atualizado.
     * @return true se a atualização for bem-sucedida, false caso contrário.
     */
    @Override
    public boolean update(Playlist playlist) {
        String sql = "UPDATE playlists SET usuario_id=?, nome=? WHERE playlist_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, playlist.getDono().getId());
            stmt.setString(2, playlist.getNome());
            stmt.setInt(3, playlist.getId());
            stmt.execute();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método para excluir uma playlist do banco de dados pelo ID.
     *
     * @param id O ID da playlist a ser excluída.
     * @return true se a exclusão for bem-sucedida, false caso contrário.
     */
    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM playlists WHERE playlist_id=?";
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
     * Método para obter todos as playlists de um usuário.
     *
     * @param usuario O usuário dono da playlist.
     * @return Uma lista de objetos Playlist.
     */
    public List<Playlist> getAllUserPlaylist(UsuarioVIP usuario){
        String sql = "SELECT * FROM playlists WHERE usuario_id=?";
        List<Playlist> playlists = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, usuario.getId());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()){
                int idPlaylist = resultado.getInt("playlist_id");
                String nome = resultado.getString("nome");
                int idDono = resultado.getInt("usuario_id");

                UsuarioDAO udao = new UsuarioDAO();
                UsuarioVIP dono = (UsuarioVIP) udao.getById(idDono);

                playlists.add(new Playlist(idPlaylist, nome, dono));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return playlists;
    }

    /**
     * Método para adicionar musicas a uma playlist.
     *
     * @param musica A musica que será adicionada.
     * @param playlist A playlist que vai receber a musica.
     * @return true se a adição for bem-sucedida, false caso contrário.
     */
    public boolean addMusicToPlaylist(Musica musica, Playlist playlist){
        String sql = "INSERT INTO playlist_musica(playlist_id, musica_id) VALUES(?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, playlist.getId());
            stmt.setInt(2, musica.getId());
            stmt.execute();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método para obter todos as musicas de uma playlist.
     *
     * @param playlist A playlist que será acessada.
     * @return Uma lista de objetos Musica.
     */
    public List<Musica> getPlaylistMusics(Playlist playlist){
        String sql = "SELECT musica_id FROM playlist_musica WHERE playlist_id=?";
        List<Musica> musicas = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, playlist.getId());
            ResultSet resultado = stmt.executeQuery();
            MusicaDAO mdao = new MusicaDAO();
            while (resultado.next()){
                int idMusica = resultado.getInt("musica_id");
                Musica musica = mdao.getById(idMusica);
                musicas.add(musica);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return musicas;
    }
}
