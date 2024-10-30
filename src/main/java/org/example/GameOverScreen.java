package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameOverScreen extends Application {

    private Scene scene;
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        VideoBackground videoBackground = new VideoBackground();
        String videoPath = "path/to/your/video.mp4"; // עדכן לנתיב המתאים

        // התחלת ניגון הסרטון
        videoBackground.startVideo(videoPath, root);

        // הוספת כפתור חזרה
        Button returnToLobbyButton = new Button("Return to Lobby");
        returnToLobbyButton.setOnAction(event -> {
            System.out.println("Returning to Lobby...");
            // כאן תוכל להוסיף את הלוגיקה להחזרה ללובי
        });

        // הוספת כפתור יציאה
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> {
            videoBackground.stopVideo(); // עצור את הסרטון לפני סגירת התוכנית
            System.exit(0);
        });

        // הוספת הכפתורים ל-root
        layout.getChildren().add(gameOverText);

        // יצירת סצנה
        this.scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Game Over Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public Scene getScene() {
        return scene;
    }
}
