package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class MainPageController implements Initializable {


    @FXML
    private Button addDirectory;

    @FXML
    private Button addFile;

    @FXML
    private Button buttonBackward;

    @FXML
    private Rectangle buttonControlMusic;

    @FXML
    private Button buttonForward;

    @FXML
    private Button buttonPlay;

    @FXML
    private Button mudaAvatar;

    @FXML
    private AnchorPane player;

    @FXML
    private ComboBox<?> speedBox;

    @FXML
    private Slider volumeSlider;

    @FXML
    private Label welcomeLabel;

    @FXML
    private MediaView mediaView;

    private File directory;
    private File[] files;

    private ArrayList<File> songs;

    private int songNumber;
    private int[] speeds = {25, 50, 100, 125, 150, 200};

    private Timer timer;
    private TimerTask task;
    private boolean running;
    private String path;
    private MediaPlayer mediaPlayer;
    private Media media;


    public void changeSpeed(ActionEvent event) {

    }


    public void nextMedia(ActionEvent event) {

    }


    public void playMedia(ActionEvent event) {

    }

    public void previousMedia(ActionEvent event) {

    }
    public void beginTimer(){

    }

    public void cancelTimer(){

    }
    public void AddFileMethod(ActionEvent actionEvent){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        path = file.toURI().toString();
        if(path != null){
            try {
                media = new Media(path);
                mediaPlayer = new MediaPlayer(media);
                mediaView.setMediaPlayer(mediaPlayer);
                mediaPlayer.play();
                mediaPlayer.setVolume(0.5);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("ALGO NO PROCESSO DEU ERRADO.");
            alert.show();
        }
    }
    public void userInfo(String username){
        welcomeLabel.setText("Oi, " + username + "!");
    }
    public void audioPlayerButtons(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       // songs = new ArrayList<File>();

        //directory = new File();
    }
}

