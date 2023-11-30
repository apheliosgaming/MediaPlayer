package br.ufrn.imd.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

//import java.sql.Connection;

//import java.awt.event.ActionEvent;

public class LoginController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Button ConfirmButton;

    @FXML
    private Label WarningTextLogin;

    public void ConfirmButtonOnAction(){
        if(!usernameField.getText().isBlank() && !passwordField.getText().isBlank()){
            WarningTextLogin.setText("Você logou!");
            validaLogin();
        }
        else{
            WarningTextLogin.setText("Insira um usuário e senha.");
        }
    }
    public void validaLogin(){
        //parte do UsuarioDAO boto amanha XD
    }

    @FXML
    private ImageView GuyHeadphonesLogin;

    @FXML
    private ImageView IconHeadPhone;

    @FXML
    private Button RegisterButton;

    public void RegisterButtonOnAction(ActionEvent e){}

}
