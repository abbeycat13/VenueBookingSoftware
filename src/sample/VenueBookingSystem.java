package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import java.sql.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;

public class VenueBookingSystem extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Venue Booking System");
        primaryStage.setScene(new Scene(root, 500, 600));
        primaryStage.show();
    }

    Client client;

    @FXML
    TextField clientIDField, newIDField, newFNField, newLNField, newPhoneField, newEmailField;
    @FXML
    PasswordField passwordField, newPassField;
    @FXML
    Label msgText;
    @FXML
    HBox menuBar;
    @FXML
    Pane loginPane, registerPane;

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
                        menuBar.setVisible(true); // show menu bar
                        // initialize client details
                        client = new Client(rs.getString("id"), rs.getString("password"),
                                rs.getString("firstName"), rs.getString("lastName"),
                                rs.getDouble("phone"), rs.getString("email"));
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

    @FXML
    private void handleNewUser(ActionEvent event) {
        loginPane.setVisible(false); // hide login screen
        registerPane.setVisible(true); // show register screen
    }

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
            client.addToDatabase();
            registerPane.setVisible(false);
            menuBar.setVisible(true);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


}
