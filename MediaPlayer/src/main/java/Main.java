import br.ufrn.imd.DAO.UsuarioDAO;
import br.ufrn.imd.models.Usuario;
import br.ufrn.imd.models.UsuarioVIP;

public class Main {
    public static void main(String[] args){
        UsuarioDAO udao = new UsuarioDAO();
//        Usuario u1 = new Usuario("usuariotest", "test1@mail.com", "12345");
//        UsuarioVIP u2 = new UsuarioVIP("vipvipvip", "vip2@mail.com", "vip1235", 2);

        UsuarioVIP user = new UsuarioVIP("joao", "joao@gmail.com", "password", 0);

        udao.create(user);

//        System.out.println(u1.getUsername());
//        System.out.println(u2.getUsername());
        //udao.create(u1);
        //udao.create(u2);

//        System.out.println(udao.getById(3).getUsername());
//        System.out.println(udao.getById(3) instanceof UsuarioVIP);

        //Usuario u1= udao.getById(2);

        //u1.setUsername("mariasilva");

        //udao.update(u1);

        //udao.delete(u1.getId());

        for (Usuario u : udao.getAll()){
            System.out.println(u.getUsername());
            System.out.println(u instanceof UsuarioVIP);
            System.out.println("-----------");
        }

        if(udao.autenticar("test1@mail.com","1234")){
            System.out.println("Logado");
        } else {
            System.out.println("Senha ou email incorreto");
        }

    }
}
