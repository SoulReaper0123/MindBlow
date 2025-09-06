package mindblowgame;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;

/**
 * JavaFX port of your Swing home screen.
 */
public class HomeView {
    private final BorderPane root = new BorderPane();

    public interface Action {
        void run();
    }

    public HomeView(Action onPlay, Action onHowToPlay, Action onAbout) {
        // Gradient background
        StackPane background = new StackPane();
        Canvas canvas = new Canvas(800, 600);
        background.getChildren().add(canvas);
        drawBackground(canvas);
        background.widthProperty().addListener((obs, o, n) -> drawBackground(canvas));
        background.heightProperty().addListener((obs, o, n) -> drawBackground(canvas));

        // Title
        Label title = new Label("MIND BLOW");
        title.setTextFill(Color.web("#FFD700"));
        title.setFont(Font.font("Arial Rounded MT Bold", 60));
        title.setAlignment(Pos.CENTER);

        // Diamond icon (simple polygon)
        Polygon diamond = new Polygon();
        diamond.getPoints().addAll(
                0.0, -25.0,
                25.0, 0.0,
                0.0, 25.0,
                -25.0, 0.0
        );
        diamond.setFill(Color.web("#00BFFF"));
        diamond.setStroke(Color.WHITE);
        StackPane diamondWrap = new StackPane(new Group(diamond));
        diamondWrap.setPrefSize(60, 60);

        // Buttons
        Button play = roundedButton("PLAY", 30);
        play.setOnAction(e -> onPlay.run());
        Button howTo = roundedButton("HOW TO PLAY", 20);
        howTo.setOnAction(e -> onHowToPlay.run());
        Button about = roundedButton("ABOUT", 20);
        about.setOnAction(e -> onAbout.run());

        VBox center = new VBox(20,
                title,
                diamondWrap,
                play,
                howTo,
                about
        );
        center.setAlignment(Pos.CENTER);

        // Footer
        Label footer = new Label("Find Diamonds • Avoid Bombs • Complete the Levels!");
        footer.setTextFill(Color.web("#C8C8FF"));
        footer.setFont(Font.font("Arial", 16));
        StackPane footerBox = new StackPane(footer);
        footerBox.setAlignment(Pos.CENTER);
        footerBox.setPrefHeight(40);

        root.setCenter(center);
        root.setBottom(footerBox);

        // place background underneath
        StackPane layer = new StackPane(background, root.getCenter());
        root.setCenter(layer);
    }

    public BorderPane getRoot() {
        return root;
    }

    private Button roundedButton(String text, int fontSize) {
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: #464678; -fx-text-fill: white; -fx-background-radius: 25; -fx-padding: 10 20; -fx-border-color: #646496; -fx-border-radius: 25;");
        btn.setFont(Font.font("Arial Rounded MT Bold", fontSize));
        return btn;
    }

    private void drawBackground(Canvas canvas) {
        double w = canvas.getWidth();
        double h = canvas.getHeight();
        canvas.setWidth(((int) w));
        canvas.setHeight(((int) h));

        GraphicsContext g = canvas.getGraphicsContext2D();
        LinearGradient lg = new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(40,40,80)),
                new Stop(1, Color.rgb(10,10,30))
        );
        g.setFill(lg);
        g.fillRect(0, 0, w, h);
    }
}