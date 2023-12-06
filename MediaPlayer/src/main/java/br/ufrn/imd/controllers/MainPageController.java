package br.ufrn.imd.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
//import javafx.scene.media.MediaView;

import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import java.net.URL;
import java.io.File;

import java.util.*;

public class MainPageController implements Initializable {


    @FXML
    private Button addDirectory;

    @FXML
    private Button pauseButton;

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
    private ComboBox speedBox;

    @FXML
    private Slider volumeSlider;

    @FXML
    private Label welcomeLabel;
    @FXML
    private ListView<String> musicList;
    @FXML
    private ListView<String> playlistList;
    @FXML
    private ImageView imagePause;

    private File directory;
    private File[] files;

    private ArrayList<File> songs;

    private int songNumber;

    private Timer timer;
    private TimerTask task;
    private boolean running;
    private String path;
    private MediaPlayer mediaPlayer;
    private Media media;
    private String songName;


    public void changeSpeed(ActionEvent event) {
        String velocidade = speedBox.getSelectionModel().getSelectedItem().toString();
        if(Objects.equals(velocidade, "50%")){
            mediaPlayer.setRate(50*0.01);
        }
        if(Objects.equals(velocidade, "75%")){
            mediaPlayer.setRate(75*0.01);
        }
        if(Objects.equals(velocidade, "100%")){
            mediaPlayer.setRate(100*0.01);
        }
        if(Objects.equals(velocidade, "125%")){
            mediaPlayer.setRate(125*0.01);
        }
        if(Objects.equals(velocidade, "150%")){
            mediaPlayer.setRate(150*0.01);
        }
        if(Objects.equals(velocidade, "200%")){
            mediaPlayer.setRate(200*0.01);
        }

    }

    public void MudaAvatarOnAction(){
        /*
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione seu novo avatar");
        File file = fileChooser.showOpenDialog(null);
        if(file != null) {
            path = file.toURI().toString();
            System.out.println(path);
        }
        else{
            System.out.println("Erro!");
        }
        */
    }


    public void nextMedia(ActionEvent event) {

    }


    public void playMedia(ActionEvent event) {
        mediaPlayer.play();
    }
    public void pauseMedia(ActionEvent event) {
        mediaPlayer.pause();
        //imagePause.setImage(new Image("java/resources/util_RESOURCES/play(2).png"));

    }

    public void previousMedia(ActionEvent event) {

    }
    public void beginTimer(){

    }

    public void cancelTimer(){

    }
    @FXML
    public void AddFileMethod(ActionEvent actionEvent){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione o arquivo da música");
        File file = fileChooser.showOpenDialog(null);
        if(file != null){
            path = file.toURI().toString();
            System.out.println(path);
            media = new Media(path);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();



            //} catch (Exception e){
             //   e.printStackTrace();
           // }
            }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("O arquivo fornecido é vazio, incompatível ou inexistente.");
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
        ObservableList<String> list = FXCollections.observableArrayList(Integer.toString(50) + "%",
                Integer.toString(75) + "%", Integer.toString(100) + "%",
                Integer.toString(125) + "%", Integer.toString(150) + "%",
                Integer.toString(200) + "%");
        speedBox.setItems(list);

        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
            }
        });
        //musicList.getItems().addAll("cachorro", "gato.mp3");
        //playlistList.getItems().addAll("a", "0");



      // songs = new ArrayList<File>();

      // directory = new File("util");

      // files = directory.listFiles();

     //  if(files != null){
         //  for(File file : files){
          //     songs.add(file);
           //    System.out.println(file);
         //  }
     //  }
    }
  //  Media media = new Media(songs.get(songNumber));

}
