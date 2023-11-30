package app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

//import java.sql.Connection;

//import java.awt.event.ActionEvent;

public class RegistrationController {

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
    public void ConfirmButtonOnAction() {
        if(!usernameField.getText().isBlank() && !passwordField.getText().isBlank() && !emailField.getText().isBlank()){
            WarningTextLogin.setText("Você se registrou!");
        }
        else{
            WarningTextLogin.setText("Preencha todos os componentes.");
        }
    }

}
