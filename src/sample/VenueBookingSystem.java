package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import java.sql.*;
import java.util.ArrayList;
import java.text.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


/**
 * MAIN CODE FILE
 * This file runs the software, and also controls the UI.
 * ALL methods to do with buttons clicks and displaying
 * things for the user should be here.
 */

public class VenueBookingSystem extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Venue Booking System");
        primaryStage.setScene(new Scene(root, 500, 600));
        primaryStage.show();
    }

    private Client client;

    /**
     * Note: UI elements need to be declared here in order to be referenced in code
     */
    @FXML
    TextField clientIDField, newIDField, newFNField, newLNField, newPhoneField, newEmailField;
    @FXML
    PasswordField passwordField, newPassField;
    @FXML
    Label msgText, clientIDLabel;
    @FXML
    VBox bookingVBox;
    @FXML
    Pane loginPane, registerPane, mainPane;
    @FXML
    ScrollPane viewBookingsPane;

    /**
     * METHOD: handleLogin
     * Runs when user clicks the 'Login' button.
     * Checks if Client ID is in the database -- if found, checks if password matches.
     * If not found, or password is incorrect, shows error message.
     */
    @FXML
    private void handleLogin(ActionEvent event) {
        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS clients (id TEXT, password TEXT, firstName TEXT, " +
                    "lastName TEXT, phone INTEGER, email TEXT)");
            // search database for client ID
            ResultSet rs = statement.executeQuery("SELECT * FROM clients");
            boolean loginSuccess = false;
            while (rs.next() && !loginSuccess) { // searches until login is successful or there are no more records
                if (clientIDField.getText().equals(rs.getString("id")))
                // if ID found, check if password matches
                    if (passwordField.getText().equals(rs.getString("password")))
                    {
                        loginPane.setVisible(false); // hide login screen
                        mainPane.setVisible(true); // show main screen
                        // load client details from database and store in client object
                        client = new Client(rs.getString("id"), rs.getString("password"),
                                rs.getString("firstName"), rs.getString("lastName"),
                                rs.getDouble("phone"), rs.getString("email"));
                        clientIDLabel.setText(clientIDLabel.getText()+client.getFirstName()+" "+client.getLastName());
                        loginSuccess = true; // stop searching
                    }
            }
            // if not found, print error message
            if (!loginSuccess)
                msgText.setText("Login failed. Try again or register a new user.");
            statement.close();
            conn.close();
        } catch (SQLException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * METHOD: handleNewUser
     * Runs when user clicks the 'New User' button.
     * Shows the registration screen.
     */
    @FXML
    private void handleNewUser(ActionEvent event) {
        loginPane.setVisible(false); // hide login screen
        registerPane.setVisible(true); // show register screen
    }

    /**
     * METHOD: handleReg
     * Runs when user clicks the 'Register New User' button.
     * Instantiates a client with the input data. Shows error message if data is invalid.
     * Else, adds the new client to the database and allows them into the system.
     */
    @FXML
    private void handleReg(ActionEvent event) {
        client = new Client();
        client.setId(newIDField.getText());
        client.setPassword(newPassField.getText());
        client.setFirstName(newFNField.getText());
        client.setLastName(newLNField.getText());
        client.setPhoneNumber(newPhoneField.getText());
        client.setEmailAddress(newEmailField.getText());
        if (client.getId().equals("INVALID"))
            msgText.setText("That ID is already in use.");
        else if (client.getPhoneNumber() == 0)
            msgText.setText("Invalid phone number. Please enter all 10 digits.");
        else if (client.getEmailAddress().equals("INVALID"))
            msgText.setText("Invalid email address.");
        else {
            client.addToDatabase(); // add new client to database
            registerPane.setVisible(false); // hide register pane
            mainPane.setVisible(true); // show main screen
            clientIDLabel.setText(clientIDLabel.getText()+client.getFirstName()+" "+client.getLastName());
        }
    }

    /**
     * METHOD: handleBookEvent
     * Runs when user clicks the 'Book Event' button.
     *
     * Note: This method should show a form to collect the event info from the user.
     * Open sample.fxml in Scene Builder to set this up.
     * Then should probably instantiate EventBooking object from data and pass to
     * client.bookEvent method
     */
    @FXML
    private void handleBookEvent(ActionEvent event) {

        // insert code here

    }

    /**
     * METHOD: handleCancelBooking
     * Runs when user clicks the 'Cancel Booking' button.
     *
     * Note: This method should display all of a client's current bookings,
     * and somehow allow them to indicate which one they want to cancel.
     * Then call client.cancelBooking to do the actual cancelling.
     */
    @FXML
    private void handleCancelBooking(ActionEvent event) {

        // insert code here

    }

    /**
     * METHOD: handleViewBookings
     * Runs when user clicks the 'View Bookings' button.
     * Calls the viewBookings method in Client class, then displays the client's current event bookings
     */
    @FXML
    private void handleViewBookings(ActionEvent event) {
        if (!bookingVBox.isVisible()) {
            bookingVBox.setVisible(true);

            /**
             * Note: probably want to move the rest of this method to its own method within the
             * client class, as the same code will probably be used for the 'Cancel Booking' button.
             * In that case, probably need to pass VBox as parameter.
             */

            // get client's bookings and store in array list
            ArrayList<EventBooking>clientBookings = client.viewBookings();
            // display data in array list
            for (EventBooking eventBooking : clientBookings) {
                Label dateLabel = new Label(eventBooking.getEventDate());
                Label nameLabel = new Label(eventBooking.getEventName());
                if (eventBooking.isPrivateEvent())
                    nameLabel.setText(nameLabel.getText() + " (private event)");
                Label timeLabel = new Label(eventBooking.getStartTime() + " - " + eventBooking.getEndTime());
                Label venueLabel = new Label(eventBooking.getVenue());
                Label feeLabel = new Label("Booking Fee: "+
                        NumberFormat.getCurrencyInstance().format(eventBooking.getBookingFee())+ " Paid? ");
                if (eventBooking.isFeePaid())
                    feeLabel.setText(feeLabel.getText() + "Y");
                else
                    feeLabel.setText(feeLabel.getText() + "N");
                Separator separator = new Separator();
                bookingVBox.getChildren().addAll(dateLabel, nameLabel, timeLabel, venueLabel, feeLabel, separator);
            }
        }
    }

    /**
     * METHOD: handleEventCal
     * Runs when user clicks the 'Event Calendar' button.
     *
     * Note: This method should show a drop-down menu of venues, then when a venue is selected,
     * display all the events at that particular venue.
     * OR... This method can simply display all the events in the database.
     */
    @FXML
    private void handleEventCal(ActionEvent event) {

        // insert code here

    }

    public static void main(String[] args) {
        launch(args);
    }
}
