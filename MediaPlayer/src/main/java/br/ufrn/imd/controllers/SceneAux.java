package br.ufrn.imd.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.*;

public class SceneAux {

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username){
        Parent root = null;

        if(username != null){
            try {
                FXMLLoader loader = new FXMLLoader(SceneAux.class.getResource(fxmlFile));
                root = loader.load();
                MainPageController controller = loader.getController();
                controller.userInfo(username);
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
    public static void logInUser(ActionEvent actionEvent, String username, String senha){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:mediaplayer.db");
            preparedStatement = connection.prepareStatement("SELECT senha FROM usuarios WHERE usuarios = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Usuário não encontrado na db.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Usuário ou senha incorretos.");
                alert.show();
            }
            else{
                while(resultSet.next()) {
                    String userPassword = resultSet.getString("senha");
                    if(userPassword.equals(senha)) {
                        changeScene(actionEvent, "/MainPage.fxml", "Media Player", username);
                    } else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Usuário ou senha incorretos.");
                        alert.show();
                    }
                }
            }
        } catch (SQLException s){
            s.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException s){
                    s.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try{
                    preparedStatement.close();
                } catch (SQLException s){
                    s.printStackTrace();
                }
            }
            if(connection != null){
                try{
                    connection.close();
                } catch (SQLException s){
                    s.printStackTrace();
                }
            }
        }
    }
}
