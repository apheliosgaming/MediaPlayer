package br.ufrn.imd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage)  throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/LoginPage.fxml"));
        stage.setTitle("Tela de Login");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
