//package br.ufrn.imd.controllers;
package br.ufrn.imd.controllers;
//package com.kensoftph.javafxmedia;

import br.ufrn.imd.DAO.MusicaDAO;
import br.ufrn.imd.DAO.UsuarioDAO;
import br.ufrn.imd.models.Musica;
import br.ufrn.imd.models.Usuario;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
//import javafx.scene.media.MediaView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.Duration;

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
    private Label LabelPlaylist;


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
    private ImageView imagePause;
    @FXML
    private Slider progressSlider;
    @FXML
    private Button createPlaylist;
    @FXML
    private TreeView treeViewPlaylist;

    @FXML
    private ListView<String> playlistListView;
    private TreeItem<String> root;


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
    //private int atualIndex = -1;

    Usuario usuario;

    ObservableList<String> items = FXCollections.observableArrayList();
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
        welcomeLabel.setText("Oi, " + usuario.getUsername() + "!");

        MusicaDAO mdao = new MusicaDAO();
        List<Musica> musicasDoUsuario = mdao.getAllUserMusics(usuario);
        for (Musica musica : musicasDoUsuario){
            items.add(musica.getCaminho());
        }
    }

    public void progressSliderOnAction(){
        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {
                progressSlider.setValue(t1.toSeconds());

            }
        });
        progressSlider.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.seek(Duration.seconds(progressSlider.getValue()));
            }
        });
        progressSlider.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.seek(Duration.seconds(progressSlider.getValue()));
            }
        });
        mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                Duration total = media.getDuration();
                progressSlider.setMax(total.toSeconds());
            }
        });
    }



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
        int indexAtual = musicList.getSelectionModel().getSelectedIndex();
        if (indexAtual < musicList.getHeight() - 1) {
            musicList.getSelectionModel().select(indexAtual + 1);
        }
    }


    public void playMedia(ActionEvent event) {
        //progressSliderOnAction();
        mediaPlayer.play();
    }
    public void pauseMedia(ActionEvent event) {
        mediaPlayer.pause();
        //imagePause.setImage(new Image("java/resources/util_RESOURCES/play(2).png"));

    }

    public void previousMedia(ActionEvent event) {
        int indexAtual = musicList.getSelectionModel().getSelectedIndex();
        if(indexAtual > 0){
            musicList.getSelectionModel().select(indexAtual - 1);
        }
    }
    public void beginTimer(){

    }

    public void cancelTimer(){

    }

    public void AddDirectoryMethod(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Selecione o diretório");
        File directory = directoryChooser.showDialog(null);
        MusicaDAO mdao = new MusicaDAO();

        if(directory != null){
            File[] files = directory.listFiles();
            if(files != null){
                for(File file : files) {
                    //System.out.println(file.toURI().toString());
                    path = file.toURI().toString();
                    String titulo = file.getName();
                    Musica musica = new Musica(0, usuario, titulo, 0, path);
                    mdao.create(musica);
                    items.add(path);
                }
            }
        }
    }

    @FXML
    public void AddFileMethod(ActionEvent actionEvent){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione o arquivo da música");
        File file = fileChooser.showOpenDialog(null);
        if(file != null){
            path = file.toURI().toString();
            MusicaDAO mdao = new MusicaDAO();
            String titulo = file.getName();
            Musica musica = new Musica(0, usuario, titulo, 0, path);
            mdao.create(musica);
            items.add(path);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("O arquivo fornecido é vazio, incompatível ou inexistente.");
            alert.show();
        }
    }
    public void stopAndPlay(String newFile){
        if(mediaPlayer != null){
            mediaPlayer.stop();
        }
        if(newFile != null){
            //media = new Media(newFile);
            media = new Media(newFile);
            mediaPlayer = new MediaPlayer(media);
            progressSliderOnAction();
            mediaPlayer.play();
            //TESTE
        }
    }
    public void createPlaylistOnAction(){
        ListView<String> novaListView = new ListView<>();

        TextInputDialog td = new TextInputDialog("Playlist1");

        // setHeaderText
        td.setHeaderText("Insira um nome na playlist");
        td.showAndWait();

        if (root == null) {
            root = new TreeItem<>("Playlists");
            treeViewPlaylist.setRoot(root);
        }
        treeViewPlaylist.setEditable( true );
        TreeItem<String> newPlaylist = new TreeItem<> (td.getEditor().getText());

        //LabelPlaylist.setText(td.getEditor().getText());

        //TreeItem<String> folha = new TreeItem<> ("DUO");

        root.getChildren().addAll(newPlaylist);

        //ListView<String> novaListView = new ListView<>(targetList);

        novaListView = playlistListView;

        //newPlaylist.getChildren().addAll(folha);


    }
    ObservableList<String> targetList = FXCollections.observableArrayList();

//    public void ListViewDragAndDrop(ListView<String> listView, ListView<String> playListView){
//        playlistListView.setItems(targetList);
//        //playListView = new ListView<>(targetList);
//        //arrasta e coloca items da musicList na playlistListView
//        musicList.setCellFactory( new Callback<ListView<String>, ListCell<String>>()
//        {
//            @Override
//            public ListCell<String> call( ListView<String> param )
//            {
//                ListCell<String> listCell = new ListCell<String>()
//                {
//                    @Override
//                    protected void updateItem( String item, boolean empty )
//                    {
//                        super.updateItem( item, empty );
//                        setText( item );
//                    }
//                };
//
//                listCell.setOnDragDetected( ( MouseEvent event ) ->
//                {
//                    System.out.println( "listcell setOnDragDetected" );
//                    Dragboard db = listCell.startDragAndDrop( TransferMode.COPY );
//                    ClipboardContent content = new ClipboardContent();
//                    content.putString( listCell.getItem() );
//                    db.setContent( content );
//                    event.consume();
//                } );
//
//                listCell.setOnDragOver( ( DragEvent event ) ->
//                {
//                    System.out.println("POR FAVOR");
//                    Dragboard db = event.getDragboard();
//                    if ( db.hasString() )
//                    {
//                        event.acceptTransferModes( TransferMode.COPY_OR_MOVE );
//                    }
//                    event.consume();
//                } );
//
//                listCell.setOnDragDropped( ( DragEvent event ) ->
//                {
//                    System.out.println( "listCell.setOnDragDropped" );
//                    Dragboard db = event.getDragboard();
//                    boolean success = false;
//                    if ( db.hasString() )
//                    {
//                        System.out.println( "Dropped: " + db.getString() );
//                        targetList.add(db.getString());
//                        success = true;
//                    }
//                    event.setDropCompleted( success );
//                    event.consume();
//                } );
//
//                System.out.println("TESTE");
//
//                return listCell;
//            }
//        } );
//    }
    private void handleMouseClicked(MouseEvent event) {
        Node node = event.getPickResult().getIntersectedNode();
        // Accept clicks only on node cells, and not on empty spaces of the TreeView
        if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
            String name = (String) ((TreeItem)treeViewPlaylist.getSelectionModel().getSelectedItem()).getValue();
            System.out.println("Node click: " + name);
            LabelPlaylist.setText(name);
        }
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
        musicList.setItems(items);
        musicList.getSelectionModel().selectedItemProperty().addListener((observable, oldFile, newFile) -> {
            stopAndPlay(newFile);
        });
        //TESTE
//        playlistListView.getSelectionModel().selectedItemProperty().addListener((observable, oldFile, newFile) -> {
//            stopAndPlay(newFile);
//        });
        //treeViewPlaylist.getSelectionModel().selectedItemProperty().addListener((observable, old F ));
        //TESTE
//        ListViewDragAndDrop(musicList, playlistListView);

        EventHandler<MouseEvent> mouseEventHandle = (MouseEvent event) -> {
            handleMouseClicked(event);
        };

        treeViewPlaylist.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle);
/*
        playlistListView.setItems(targetList);
        //playListView = new ListView<>(targetList);
        //arrasta e coloca items da musicList na playlistListView
        musicList.setCellFactory( new Callback<ListView<String>, ListCell<String>>()
        {
            @Override
            public ListCell<String> call( ListView<String> param )
            {
                ListCell<String> listCell = new ListCell<String>()
                {
                    @Override
                    protected void updateItem( String item, boolean empty )
                    {
                        super.updateItem( item, empty );
                        setText( item );
                    }
                };

                listCell.setOnDragDetected( ( MouseEvent event ) ->
                {
                    System.out.println( "listcell setOnDragDetected" );
                    Dragboard db = listCell.startDragAndDrop( TransferMode.COPY );
                    ClipboardContent content = new ClipboardContent();
                    content.putString( listCell.getItem() );
                    db.setContent( content );
                    event.consume();
                } );
                return listCell;
            }
        } );

        playlistListView.setCellFactory( new Callback<ListView<String>, ListCell<String>>()
        {
            @Override
            public ListCell<String> call( ListView<String> param )
            {
                ListCell<String> listCell2 = new ListCell<String>()
                {
                    @Override
                    protected void updateItem( String item, boolean empty )
                    {
                        super.updateItem( item, empty );
                        setText( item );
                    }
                };

                listCell2.setOnDragOver( ( DragEvent event ) ->
                {
                    System.out.println("POR FAVOR");
                    Dragboard db = event.getDragboard();
                    if ( db.hasString() )
                    {
                        event.acceptTransferModes( TransferMode.COPY_OR_MOVE );
                    }
                    event.consume();
                } );
                listCell2.setOnDragDropped( ( DragEvent event ) ->
                {
                    System.out.println( "listCell.setOnDragDropped" );
                    Dragboard db = event.getDragboard();
                    boolean success = false;
                    if ( db.hasString() )
                    {
                        System.out.println( "Dropped: " + db.getString() );
                        targetList.add(db.getString());
                        success = true;
                    }
                    event.setDropCompleted( success );
                    event.consume();
                } );

                System.out.println("TESTEE");

                return listCell2;
            }
        } );
*/

    }
}
