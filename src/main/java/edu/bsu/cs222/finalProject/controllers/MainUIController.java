package edu.bsu.cs222.finalProject.controllers;

import edu.bsu.cs222.finalProject.Music;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MainUIController implements Initializable {

    @FXML
    public AnchorPane rootPane;
    public Pane pane;
    @FXML
    private Label nowPlaying_Label;
    @FXML
    private Label songTitle_Label;
    @FXML
    private Label artist_Label;
    @FXML
    private Label alreadyHaveCC_Label;
    @FXML
    private Label bsu_Student_Label;
    @FXML
    private Label signUpToday_Label;
    @FXML
    private VBox vBox;
    @FXML
    private Parent fxml;

    private final Random random = new Random();
    private final Circle[] snowballs = new Circle[2000];

    Music audioPlayer = new Music();

    public MainUIController() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {}

    @Override
    public void initialize( URL location , ResourceBundle resources ) {
        audioPlayer.clipStatus = "playing";
        initSnowBalls(snowballs);
        bsu_Student_Label.setVisible( false );
        alreadyHaveCC_Label.setVisible( false );
        try { loadUI_IntoVBox(); } catch ( IOException ignored ){}
        try { playSong(); } catch ( Exception e ) { e.printStackTrace(); }
        TranslateTransition sceneTransition  = new TranslateTransition( Duration.seconds( 1 ), vBox);
        sceneTransition.setToX( vBox.getLayoutX() * 20 );
        sceneTransition.play();
        sceneTransition.setOnFinished( (e) ->
        { try { loadUI_IntoVBox(); } catch ( IOException ignored ) {} } );
    }

    public void playSong()
    { audioPlayer.play(); showMusicInfo();}

    public void showMusicInfo() {
        artist_Label.setVisible( true );
        songTitle_Label.setVisible( true );
        nowPlaying_Label.setVisible( true );
    }
    public void hideMusicInfo() {
        artist_Label.setVisible( false );
        songTitle_Label.setVisible( false );
        nowPlaying_Label.setVisible( false );
    }

    public void pauseSong ()
    { audioPlayer.pause(); hideMusicInfo(); }

    public void initSnowBalls(Circle[] snowballs) {
        Random random = new Random();
        for (int i = 0; i < 2000; i++) {
            snowballs[i] = new Circle(1, 1, 1);
            snowballs[i].setRadius(random.nextDouble() * 3);
            Color color = Color.rgb(255, 255, 255, random.nextDouble());
            snowballs[i].setFill(color);
            this.pane.getChildren().add(snowballs[i]);
            raining(snowballs[i]);
        }
    }

    public void raining( Circle snowball) {
        int windowHeight = 534; int windowWidth = 1225;
        int time = 10 + random.nextInt(50);
        TranslateTransition snowfall_Animation = new TranslateTransition(Duration.seconds( time ));
        snowball.setCenterX(random.nextInt(windowWidth));
        snowfall_Animation.setNode(snowball);
        snowfall_Animation.setFromY(-200);
        snowfall_Animation.setToY(windowHeight+200);
        snowfall_Animation.setToX(random.nextDouble() * pane.getLayoutX());
        snowfall_Animation.setOnFinished( t -> raining(snowball) );
        snowfall_Animation.play();
    }

    public void loadUI_IntoVBox() throws IOException {
        fxml = FXMLLoader.load( getClass().getResource( "/ui/loginUI.fxml" ));
        vBox.getChildren().removeAll();
        vBox.getChildren().setAll( fxml );
    }

    @FXML
    private void launchSignIn() {
        TranslateTransition transition = new TranslateTransition( Duration.seconds( 1 ), vBox);
        transition.setToX( vBox.getLayoutX() * 20 );
        transition.play();
        alreadyHaveCC_Label.setVisible( false );
        transition.setOnFinished( (e) -> {
            try {
                fxml = FXMLLoader.load( getClass().getResource( "/ui/loginUI.fxml" ));
                vBox.getChildren().removeAll();
                vBox.getChildren().setAll( fxml );
                alreadyHaveCC_Label.setVisible( false );
                bsu_Student_Label.setVisible( false );
                signUpToday_Label.setVisible( true );
            }
            catch ( IOException ignored ) {}
        } );
    }

    @FXML
    private void launchSignUp() {
        alreadyHaveCC_Label.setVisible( false );
        bsu_Student_Label.setVisible( true );
        TranslateTransition transition = new TranslateTransition( Duration.seconds( 1 ), vBox);
        transition.setToX(0);
        transition.play();
        signUpToday_Label.setVisible( false );
        transition.setOnFinished( (e) -> {
            try {
                fxml = FXMLLoader.load( getClass().getResource( "/ui/signupUI.fxml" ));
                vBox.getChildren().removeAll();
                vBox.getChildren().setAll( fxml );
                alreadyHaveCC_Label.setVisible( true );
            }
            catch ( IOException ignored ) {}
        } );
    }

    public void closeProgram()
    { System.exit(0); }
}
