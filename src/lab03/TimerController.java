package lab03;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TimerController {

    // Must match fx:id in FXML
    @FXML private Button startBtn;
    @FXML private Button stopBtn;
    @FXML private Button resetBtn;
    @FXML private Label  timeLabel;

    private boolean running;
    private long startNanos;
    private long accumulated;     // nanos accumulated before the current start

    private final AnimationTimer ticker = new AnimationTimer() {
        @Override public void handle(long now) {
            long elapsed = accumulated + (running ? now - startNanos : 0);
            timeLabel.setText(formatHMS(elapsed));
        }
    };

    @FXML private void initialize() {
        timeLabel.setText("00:00:00");
    }

    // Handlers must match onAction="#handleStart" etc.
    @FXML private void handleStart() {
        if (running) return;
        startNanos = System.nanoTime();
        running = true;
        ticker.start();
    }

    @FXML private void handleStop() {
        if (!running) return;
        accumulated += System.nanoTime() - startNanos;
        running = false;
        ticker.stop();
    }

    @FXML private void handleReset() {
        running = false;
        accumulated = 0;
        ticker.stop();
        timeLabel.setText("00:00:00");
    }

    private static String formatHMS(long nanos) {
        long ms = nanos / 1_000_000;
        long h = (ms / 3_600_000);
        long m = (ms / 60_000) % 60;
        long s = (ms / 1_000) % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }
}