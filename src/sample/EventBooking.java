import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EventBooking extends Application {

    private String eventType;
    private boolean isPublic;
    private double date;
    private boolean paidInFull;
    Client client = new Client();
    Venue venue = new Venue();

    public static void main(String args[]) {
        Application.launch(args);
    }

    public void start(Stage stage) {
        Button viewBtn = new Button("View Booking");
        Button bookBtn = new Button("Book an Event");
        Button cancelBtn = new Button("Cancel a Booking");
        viewBtn.setOnAction((event) -> {
            client.ViewBooking();
        });
        bookBtn.setOnAction((event) -> {
            client.BookEvent();
        });
        cancelBtn.setOnAction((event) -> {
            client.CancelBooking();
        });
    }
}
