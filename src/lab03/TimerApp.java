package lab03;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TimerApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/lab03/TimerView.fxml"));
        Scene scene     = new Scene(fxml.load());
        //scene.getStylesheets().add(getClass().getResource("timer.css").toExternalForm());

        stage.setTitle("Modern Timer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

