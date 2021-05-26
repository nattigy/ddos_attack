package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;


/*
* Nathnael Yewondwosen ATR/3174/10
* Bereket Zemed ATR/7417/10
* Moti Dinsa ATR/2366/10
* */

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
