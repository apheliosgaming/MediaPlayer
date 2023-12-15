package br.ufrn.imd.controllers;

import br.ufrn.imd.DAO.MusicaDAO;
import br.ufrn.imd.DAO.PlaylistDAO;
import br.ufrn.imd.DAO.UsuarioDAO;
import br.ufrn.imd.models.Musica;
import br.ufrn.imd.models.Playlist;
import br.ufrn.imd.models.Usuario;
import br.ufrn.imd.models.UsuarioVIP;
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

/**
 * Classe controladora da interface principal da aplicação de Media Player.
 * Responsável por gerenciar as ações e eventos associados aos elementos da interface.
 */
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
    private Button becomeVIP;

    @FXML
    private ImageView avatar;

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

        if (usuario instanceof UsuarioVIP){
            PlaylistDAO pdao = new PlaylistDAO();
            List<Playlist> playlistsDoUsuario = pdao.getAllUserPlaylist((UsuarioVIP) usuario);
            if (!playlistsDoUsuario.isEmpty()){
                if (root == null) {
                    root = new TreeItem<>("Playlists");
                    treeViewPlaylist.setRoot(root);
                }
                treeViewPlaylist.setEditable( true );
                for (Playlist playlist : playlistsDoUsuario){
                    TreeItem<String> newPlaylist = new TreeItem<> (playlist.getNome());
                    root.getChildren().addAll(newPlaylist);
                }
            }
        }
    }

    /**
     * Configura o controle deslizante de progresso e seus eventos associados.
     * Atualiza a posição da barra de progresso durante a reprodução da mídia.
     */
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

    /**
     * Altera a velocidade de reprodução do media player com base na escolha do usuário.
     *
     * @param event O evento de ação que desencadeou a chamada do método.
     */
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

    /**
     * Ação executada quando o botão "Muda Avatar" é pressionado.
     */
    public void MudaAvatarOnAction(){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione seu novo avatar");
        File file = fileChooser.showOpenDialog(null);
        if(file != null) {
            path = file.toURI().toString();
            Image novaImagem = new Image(path);
            avatar.setImage(novaImagem);
        }
        else{
            System.out.println("Erro!");
        }
    }

    /**
     * Ação executada quando o botão de avançar para a próxima mídia é pressionado.
     * Avança para a próxima música na lista de reprodução, se disponível.
     *
     * @param event O evento de ação que desencadeou a chamada do método.
     */
    public void nextMedia(ActionEvent event) {
        int indexAtual = musicList.getSelectionModel().getSelectedIndex();
        if (indexAtual < musicList.getHeight() - 1) {
            musicList.getSelectionModel().select(indexAtual + 1);
        }
    }

    /**
     * Inicia a reprodução da mídia selecionada na lista de reprodução.
     *
     * @param event O evento de ação que desencadeou a chamada do método.
     */
    public void playMedia(ActionEvent event) {
        //progressSliderOnAction();
        mediaPlayer.play();
    }

    /**
     * Pausa a reprodução da mídia atual.
     *
     * @param event O evento de ação que desencadeou a chamada do método.
     */
    public void pauseMedia(ActionEvent event) {
        mediaPlayer.pause();
    }

    /**
     * Ação executada quando o botão de retrocesso para a mídia anterior é pressionado.
     * Retrocede para a mídia anterior na lista de reprodução, se disponível.
     *
     * @param event O evento de ação que desencadeou a chamada do método.
     */
    public void previousMedia(ActionEvent event) {
        int indexAtual = musicList.getSelectionModel().getSelectedIndex();
        if(indexAtual > 0){
            musicList.getSelectionModel().select(indexAtual - 1);
        }
    }
    /**
     * Adiciona um diretório de músicas à lista de reprodução.
     * Permite ao usuário escolher um diretório e adiciona todas as músicas encontradas.
     */
    public void AddDirectoryMethod(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Selecione o diretório");
        File directory = directoryChooser.showDialog(null);
        MusicaDAO mdao = new MusicaDAO();

        if(directory != null){
            File[] files = directory.listFiles();
            if(files != null){
                for(File file : files) {
                    path = file.toURI().toString();
                    String titulo = file.getName();
                    Musica musica = new Musica(0, usuario, titulo, 0, path);
                    mdao.create(musica);
                    items.add(path);
                }
            }
        }
    }

    /**
     * Adiciona um arquivo de música à lista de reprodução.
     * Permite ao usuário escolher um arquivo de música e adiciona à lista de reprodução.
     *
     * @param actionEvent O evento de ação que desencadeou a chamada do método.
     */
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

    /**
     * Inicia ou reinicia a reprodução da mídia especificada.
     * Se uma mídia já estiver sendo reproduzida, ela será interrompida antes da nova reprodução.
     *
     * @param newFile O caminho do novo arquivo de mídia.
     */
    public void stopAndPlay(String newFile){
        if(mediaPlayer != null){
            mediaPlayer.stop();
        }
        if(newFile != null){
            media = new Media(newFile);
            mediaPlayer = new MediaPlayer(media);
            progressSliderOnAction();
            mediaPlayer.play();
        }
    }

    /**
     * Cria uma nova playlist e adiciona ao TreeView.
     * Apenas usuários VIP podem criar playlists.
     */
    public void createPlaylistOnAction(){
        if (usuario instanceof  UsuarioVIP){
            ListView<String> novaListView = new ListView<>();

            TextInputDialog td = new TextInputDialog("Playlist1");

            td.setHeaderText("Insira um nome na playlist");
            td.showAndWait();

            if (root == null) {
                root = new TreeItem<>("Playlists");
                treeViewPlaylist.setRoot(root);
            }

            treeViewPlaylist.setEditable( true );
            TreeItem<String> newPlaylist = new TreeItem<> (td.getEditor().getText());

            PlaylistDAO pdao = new PlaylistDAO();
            Playlist playlist = new Playlist(0, td.getEditor().getText(), (UsuarioVIP) usuario);
            pdao.create(playlist);
            root.getChildren().addAll(newPlaylist);

            novaListView = playlistListView;

        } else {
            System.out.println("Apenas usuarios VIP podem criar playlists");
        }

    }

    ObservableList<String> targetList = FXCollections.observableArrayList();

    /**
     * Configura a funcionalidade de arrastar e soltar entre listas.
     *
     * @param listView     A lista de origem do qual os itens podem ser arrastados.
     * @param playListView A lista de destino para onde os itens podem ser soltos.
     */
    public void ListViewDragAndDrop(ListView<String> listView, ListView<String> playListView){
        playlistListView.setItems(targetList);

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
                    //System.out.println( "listcell setOnDragDetected" );
                    Dragboard db = listCell.startDragAndDrop( TransferMode.COPY );
                    ClipboardContent content = new ClipboardContent();
                    content.putString( listCell.getItem() );
                    db.setContent( content );
                    event.consume();
                } );

                listCell.setOnDragOver( ( DragEvent event ) ->
                {
                    Dragboard db = event.getDragboard();
                    if ( db.hasString() )
                    {
                        event.acceptTransferModes( TransferMode.COPY_OR_MOVE );
                    }
                    event.consume();
                } );

                listCell.setOnDragDropped( ( DragEvent event ) ->
                {
                    //System.out.println( "listCell.setOnDragDropped" );
                    Dragboard db = event.getDragboard();
                    boolean success = false;
                    if ( db.hasString() )
                    {
                        
                        targetList.add(db.getString());
                        PlaylistDAO pdao = new PlaylistDAO();
                        MusicaDAO mdao = new MusicaDAO();

                        Musica musicaAdicionada = mdao.getByTitulo(db.getString());
                        List<Playlist> playlists = pdao.getAllUserPlaylist((UsuarioVIP) usuario);
                        pdao.addMusicToPlaylist(musicaAdicionada, playlists.get(0));

                        success = true;
                    }
                    event.setDropCompleted( success );
                    event.consume();
                } );
                return listCell;
            }
        } );
    }

    /**
     * Manipula o evento de clique do mouse no TreeViewPlaylist.
     * Atualiza o rótulo LabelPlaylist com o nome da playlist selecionada.
     *
     * @param event O evento de clique do mouse.
     */
    private void handleMouseClicked(MouseEvent event) {
        Node node = event.getPickResult().getIntersectedNode();
        if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
            String name = (String) ((TreeItem)treeViewPlaylist.getSelectionModel().getSelectedItem()).getValue();
        
            LabelPlaylist.setText(name);
        }
    }

    /**
     * Inicializa a interface do controlador.
     *
     * @param url             A URL usada para localizar o arquivo FXML.
     * @param resourceBundle O pacote de recursos para localizar o arquivo FXML.
     */
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
        
        playlistListView.getSelectionModel().selectedItemProperty().addListener((observable, oldFile, newFile) -> {
            stopAndPlay(newFile);
        });

        ListViewDragAndDrop(musicList, playlistListView);

        EventHandler<MouseEvent> mouseEventHandle = (MouseEvent event) -> {
            handleMouseClicked(event);
        };

        treeViewPlaylist.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle);

    }
}
