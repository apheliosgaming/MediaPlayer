package br.ufrn.imd.controllers;

import br.ufrn.imd.models.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.*;

/**
 * Classe utilitária para auxiliar na transição entre cenas (páginas) da aplicação.
 */
public class SceneAux {

    /**
     * Método para trocar de cena.
     *
     * @param event      O evento de ação que desencadeou a troca de cena.
     * @param fxmlFile   O arquivo FXML da cena de destino.
     * @param title      O título da nova cena.
     * @param usuario    O usuário associado à cena (pode ser nulo).
     */
    public static void changeScene(ActionEvent event, String fxmlFile, String title, Usuario usuario){
        Parent root = null;

        if(usuario != null){
            try {
                FXMLLoader loader = new FXMLLoader(SceneAux.class.getResource(fxmlFile));
                root = loader.load();
                MainPageController controller = loader.getController();
                controller.setUsuario(usuario);
                System.out.println(usuario.getUsername());
            } catch(IOException e){
                e.printStackTrace();
            }
        } else{
            try{
                root = FXMLLoader.load(SceneAux.class.getResource(fxmlFile));
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}
