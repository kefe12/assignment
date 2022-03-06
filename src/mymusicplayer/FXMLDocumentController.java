/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package mymusicplayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javafx.beans.*;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author user
 */
public class FXMLDocumentController implements Initializable {
    private String path;
private MediaPlayer mediaPlayer;
@FXML
private MediaView mediaview;
@FXML
private Slider progressBar;
@FXML
private Slider volumeSlider;

   public void chooseFileMethod(ActionEvent event){
       FileChooser fileChooser=new FileChooser();
       File file=fileChooser.showOpenDialog(null);
path=file.toURI().toString();
if(path !=null){
    Media media=new Media(path);
mediaPlayer=new MediaPlayer(media);
mediaview.setMediaPlayer(mediaPlayer);
    DoubleProperty widthprop=mediaview.fitWidthProperty();
   DoubleProperty heightprop=mediaview.fitHeightProperty();
widthprop.bind(Bindings.selectDouble(mediaview.sceneProperty(), "width"));
heightprop.bind(Bindings.selectDouble(mediaview.sceneProperty(), "height"));
mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
@Override
public void changed(ObservableValue<? extends Duration> observable,Duration oldvalue,Duration newValue){
progressBar.setValue(newValue.toSeconds());

}
});
progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
@Override
public void handle(MouseEvent event){
mediaPlayer.seek(Duration.seconds(progressBar.getValue()));

}
});
progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
@Override
public void handle(MouseEvent event){
mediaPlayer.seek(Duration.seconds(progressBar.getValue()));

}
});
mediaPlayer.setOnReady(new Runnable() {
@Override
public void run(){
Duration total=media.getDuration();
progressBar.setMax(total.toSeconds());
}
});
volumeSlider.setValue(mediaPlayer.getVolume()*100);
volumeSlider.valueProperty().addListener(new InvalidationListener() {
@Override
public void invalidated(Observable observable){
mediaPlayer.setVolume(volumeSlider.getValue()/100);
}
});
mediaPlayer.play();
}
}
 public void play(ActionEvent event){
mediaPlayer.play();
mediaPlayer.setRate(1);
}
public void pause(ActionEvent event){
mediaPlayer.pause();
}
public void stop(ActionEvent event){
mediaPlayer.stop();
}
public void slowRate(ActionEvent event){
mediaPlayer.setRate(0.5);
}

public void fastRate(ActionEvent event){
mediaPlayer.setRate(2);
}
public void skip10(ActionEvent event){
mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(10)));
}
public void back10(ActionEvent event){
mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(-10)));
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
