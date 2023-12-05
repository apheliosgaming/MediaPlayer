import br.ufrn.imd.DAO.DiretorioDAO;
import br.ufrn.imd.DAO.MusicaDAO;
import br.ufrn.imd.DAO.PlaylistDAO;
import br.ufrn.imd.DAO.UsuarioDAO;
import br.ufrn.imd.models.*;
//
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
//

public class Main extends Application{
    @Override
    public void start(Stage stage)  throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        stage.setTitle("Tela de Login");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
        }
    public static void main(String[] args){
        //nao sei se o launch da problema aqui (no meu codigo nao tem nada no main)//
        launch(args);
        UsuarioDAO udao = new UsuarioDAO();
        PlaylistDAO pdao = new PlaylistDAO();
        MusicaDAO mdao = new MusicaDAO();
        MusicaDAO mdao2 = new MusicaDAO();

        UsuarioVIP user = new UsuarioVIP(0,"joao", "joao@gmail.com", "password", 0);

//        Usuario u1= udao.getById(5);
//        System.out.println(u1.getId());
        Playlist pc = new Playlist(0,"Natal", (UsuarioVIP) udao.getById(4));

        Playlist p1 = pdao.getById(3);
        System.out.println("Nome: " + p1.getNome());



        p1.setDono((UsuarioVIP) udao.getById(4));

        pdao.update(p1);

        for (Playlist p : pdao.getAll()){
            System.out.println(p.getNome());
            if (p.getDono() != null){
                System.out.println(p.getDono().getUsername());
            }
            System.out.println("-----------");
        }

        pdao.delete(1);
        pdao.delete(2);

        System.out.println("Playlists de " + udao.getById(4).getUsername());
        for (Playlist pu : pdao.getAllUserPlaylist((UsuarioVIP) udao.getById(4))){
            System.out.println(pu.getNome());
        }


//        if(udao.autenticar("test1@mail.com","1234")){
//            System.out.println("Logado");
//        } else {
//            System.out.println("Senha ou email incorreto");
//        }

        // MUSICA DAO
        //Musica m1 = new Musica(0, "titulo3", "artista3", 1.9, "musicas/m3");
        //Musica m2 = new Musica(0, "titulo4", "artista4", 3.1, "musicas/m4");

        //mdao.create(m1);
        //mdao2.create(m2);

        System.out.println(mdao.getById(1).getTitulo());
        System.out.println(mdao2.getById(2).getTitulo());

        for (Musica m : mdao.getAll()){
            System.out.println(m.getArtista());
        }

        Musica m1 = mdao.getById(3);
        //Musica m2 = mdao.getById(4);
        m1.setTitulo("titulo3");
        //m2.setArtista("artista_novo");
        mdao.update(m1);
        //mdao2.update(m2);

        mdao.delete(4);

        // DIRETORIO DAO
        DiretorioDAO ddao = new DiretorioDAO();
        Usuario dono = udao.getById(3);
        Diretorio d = new Diretorio(0, user, "Dir1", "caminho/dir");
//        if(ddao.create(d)){
//            System.out.println("Diretorio adicionado");
//        } else {
//            System.out.println("Erro ao adicionar diretorio");
//        }

        Diretorio dir1 = ddao.getById(1);

        for (Diretorio dir : ddao.getAll()){
            System.out.println(dir.getCaminho());
        }

        dir1.setNome("Dir2");
        dir1.setDono(dono);
        dir1.setCaminho("caminho/dir2");

        //ddao.update(dir1);

        ddao.delete(3);

        for (Diretorio dir2 : ddao.getAllUserDirectories(dono)){
            System.out.println(dir2.getNome());
        }

        // Adicionar musica a playlist
        Playlist playlist = pdao.getById(3);
        Musica musica = mdao.getById(2);
        //pdao.addMusicToPlaylist(musica, playlist);

        System.out.println("Musicas da playlist:");
        for (Musica mus : pdao.getPlaylistMusics(playlist)){
            System.out.println(mus.getTitulo());
        }
    }
}
