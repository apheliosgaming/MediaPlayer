package br.ufrn.imd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe principal da aplicação que estende a classe Application do JavaFX.
 * Responsável por iniciar a interface gráfica da aplicação.
 */
public class App extends Application {

    /**
     * Método start que configura e exibe a interface gráfica da aplicação.
     *
     * @param stage O palco principal da aplicação.
     * @throws Exception Caso ocorra um erro durante a inicialização da interface gráfica.
     */
    @Override
    public void start(Stage stage)  throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/LoginPage.fxml"));
        stage.setTitle("Tela de Login");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    /**
     * Método principal que inicia a aplicação.
     *
     * @param args Os argumentos passados durante a execução da aplicação.
     */
    public static void main(String[] args){
        launch(args);
    }
}
