//package br.ufrn.imd.DAO;
//
//import br.ufrn.imd.models.Diretorio;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class DiretorioDAO implements DAOI{
//    private Connection connection; // Guarda a conexão com o banco
//
//    public DiretorioDAO(){
//        String jdbcUrl = "jdbc:sqlite:mediaplayerdb.db";
//        try {
//            this.connection = DriverManager.getConnection(jdbcUrl);
//            System.out.println("Conexão com db estabelecida");
//        } catch (SQLException e) {
//            System.out.println("Não foi possivel conectar com a db");
//            e.printStackTrace();
//        }
//    }
////
//    public Diretorio getById(int id){
//        Diretorio aux = new Diretorio();
//        return aux;
//    }
////
//    public List<Diretorio> getAll(){
//        List<Diretorio> aux= new ArrayList<Diretorio>();
//        return aux;
//    }
//
//
//    @Override
//    public void create(Diretorio diretorio){
//        // query
//    }
//
//    @Override
//    public void update(Diretorio diretorio){
//        //query
//    }
//
//    @Override
//    public void delete(Diretorio diretorio){
//        // query
//    }
//}
