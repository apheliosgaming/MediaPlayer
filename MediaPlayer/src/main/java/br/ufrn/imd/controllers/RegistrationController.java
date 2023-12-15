package br.ufrn.imd.controllers;

import br.ufrn.imd.DAO.UsuarioDAO;
import br.ufrn.imd.models.Usuario;
import br.ufrn.imd.models.UsuarioVIP;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//import java.sql.Connection;

//import java.awt.event.ActionEvent;

public class RegistrationController implements Initializable {

    @FXML
    private Button ConfirmButton;

    @FXML
    private ImageView GuyHeadphonesLogin;

    @FXML
    private ImageView IconHeadPhone;

    @FXML
    private Button LoginButton;

    @FXML
    private Label WarningTextLogin;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private CheckBox VIPChecker;

    /**
     * Controlador da interface de registro da aplicação.
     * Responsável por gerenciar as ações e eventos associados aos elementos da interface.
     */
    @FXML
    public void ConfirmButtonOnAction() {
        if(!usernameField.getText().isBlank() && !passwordField.getText().isBlank() && !emailField.getText().isBlank()){
            UsuarioDAO udao = new UsuarioDAO();
            if(VIPChecker.isSelected()){
                UsuarioVIP usuarioVIP = new UsuarioVIP(
                        0,
                        usernameField.getText(),
                        emailField.getText(),
                        passwordField.getText()
                );
                udao.create(usuarioVIP);
                WarningTextLogin.setText("Você se registrou!");
            }
            else {
                Usuario usuario = new Usuario(
                        0,
                        usernameField.getText(),
                        emailField.getText(),
                        passwordField.getText()
                );
                udao.create(usuario);

                WarningTextLogin.setText("Você se registrou!");
            }
        }
        else{
            WarningTextLogin.setText("Preencha todos os componentes.");
        }
    }

    /**
     * Ação executada quando o botão de confirmação é pressionado.
     * Cria um novo usuário com as informações fornecidas e exibe uma mensagem de confirmação ou erro.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/LoginPage.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setTitle("Tela de Login");
                stage.setScene(new Scene(root, 520, 400));
                stage.show();
            }
        });
    }
}

