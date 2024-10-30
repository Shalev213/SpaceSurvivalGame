package org.example;

import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

public class VideoBackground extends Application {
    private MediaPlayer mediaPlayer;

    public void startVideo(String videoPath, StackPane root) {
        // יצירת מדיה ושחקן מדיה
        Media media = new Media(new File(videoPath).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        // הגדרת גודל הוידאו
        mediaView.setFitWidth(800); // עדכן לפי הצורך
        mediaView.setFitHeight(600); // עדכן לפי הצורך

        // הגדרת השמע לחזור
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setAutoPlay(true);

        // הוספת הוידאו לפאנל
        root.getChildren().add(mediaView);
    }

    public void stopVideo() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        // אין צורך כאן, כי נשתמש ב-GUI של GameOverScreen
    }

    public static void main(String[] args) {
        launch(args);
    }
}
