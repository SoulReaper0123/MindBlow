package mindblowgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) {
        // Initial scene is HomeView (JavaFX port of your Swing home)
        HomeView home = new HomeView(() -> {
            // TODO: switch to Level1 scene
            // For now, just update the title to show click worked
            stage.setTitle("Mind Blow Game - Play clicked");
        }, () -> {
            stage.setTitle("Mind Blow Game - How To Play");
        }, () -> {
            stage.setTitle("Mind Blow Game - About");
        });

        Scene scene = new Scene(home.getRoot(), 800, 600);
        stage.setTitle("Mind Blow Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}