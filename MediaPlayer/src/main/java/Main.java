import br.ufrn.imd.DAO.MusicaDAO;
import br.ufrn.imd.DAO.PlaylistDAO;
import br.ufrn.imd.DAO.UsuarioDAO;
import br.ufrn.imd.models.Musica;
import br.ufrn.imd.models.Playlist;
import br.ufrn.imd.models.Usuario;
import br.ufrn.imd.models.UsuarioVIP;

public class Main {
    public static void main(String[] args){
        UsuarioDAO udao = new UsuarioDAO();
        PlaylistDAO pdao = new PlaylistDAO();
        MusicaDAO mdao = new MusicaDAO();
        MusicaDAO mdao2 = new MusicaDAO();

        //UsuarioVIP user = new UsuarioVIP("joao", "joao@gmail.com", "password", 0);

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
        Musica m2 = mdao.getById(4);
        m1.setTitulo("titulo3");
        m2.setArtista("artista_novo");
        mdao.update(m1);
        mdao2.update(m2);

        mdao.delete(4);

    }
}
