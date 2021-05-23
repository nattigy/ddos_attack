package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    public static void main(String... args) {
        //launch program
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //loading the ui or fxml file file
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home_ui.fxml")));
        primaryStage.setTitle("DDoS Attack");
        primaryStage.setScene(new Scene(root, 598, 520));
        primaryStage.show();
    }
}
