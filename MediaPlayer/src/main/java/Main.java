import br.ufrn.imd.DAO.PlaylistDAO;
import br.ufrn.imd.DAO.UsuarioDAO;
import br.ufrn.imd.models.Playlist;
import br.ufrn.imd.models.Usuario;
import br.ufrn.imd.models.UsuarioVIP;

public class Main {
    public static void main(String[] args){
        UsuarioDAO udao = new UsuarioDAO();
        PlaylistDAO pdao = new PlaylistDAO();

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

    }
}
