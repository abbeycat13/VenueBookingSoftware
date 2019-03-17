import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Client extends EventBooking {

    public String firstName;
    public String lastName;
    public int phoneNumber;
    private String emailAdd;

    public void ViewBooking(){
    }

    public void BookEvent(){
        Label messageLbl = new Label("Enter your Name into the Text Fields.");
        TextField firstNameFld = new TextField();
        TextField lastNameFld = new TextField();
        firstNameFld.setPrefColumnCount(15);
        lastNameFld.setPrefColumnCount(15);
    }

    public void CancelBooking(){

    }
}
