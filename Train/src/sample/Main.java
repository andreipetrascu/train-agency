package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Employee;
import model.Employees;
import model.Train;
import model.Trains;
import model.persistence.PersonPersistence;
import model.persistence.TicketPersistence;
import model.persistence.TrainPersistence;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/passenger/passengerPage.fxml"));
        primaryStage.setTitle("Train Agency");
        primaryStage.setScene(new Scene(root, 1293, 858));
        primaryStage.show();
    }


    public static void main(String[] args) {


       launch(args);

    }
}
