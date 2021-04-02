package presenter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import model.persistence.PersonPersistence;
import model.persistence.TrainPersistence;
import view.passenger.IPassengerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class PPassenger {

    private IPassengerView passengerView;
    private Trains trains;
    private Employees employees;

    TrainPersistence trainPersistence = new TrainPersistence("./trains.xml");
    PersonPersistence personPersistence = new PersonPersistence("./employees.xml");

    public PPassenger(IPassengerView iPassengerView) {
        this.passengerView = iPassengerView;
    }

    public PPassenger(IPassengerView passengerView, Trains trains) {
        this.passengerView = passengerView;
        this.trains = trains;
    }

    public void loadDataFromXML() {
        trains = trainPersistence.load();
        employees = personPersistence.load();
    }

    /**
     * login section
     */

    public void handleLoginButton() throws Exception {
        /* check if the username and password are correct */
        if (passengerView.getCheckBox().isSelected()) {

            String username = passengerView.getUserTF().getText();
            String password = passengerView.getPasswordTF().getText();

            if (username.equals("") || password.equals("")) return;

            Employee employee = searchForEmployee(username, password);

            // two types of employee: admin and normal

            if (employee.getRole().equals("admin")) {
                Parent root = FXMLLoader.load(getClass().getResource("/view/admin/adminPage.fxml"));
                Stage window = (Stage) passengerView.getLoginB().getScene().getWindow();
                window.setTitle("Admin Page");
                window.setScene(new Scene(root, 1300, 800));
            } else {
                Parent root = FXMLLoader.load(getClass().getResource("/view/employee/employeePage.fxml"));
                Stage window = (Stage) passengerView.getLoginB().getScene().getWindow();
                window.setTitle("Employee Page");
                window.setScene(new Scene(root, 1400, 900));
            }
        } else {
            System.out.println("tick the checkbox!");
        }

    }

    /**
     * Search for an employee which matches username and password
     *
     * @param username
     * @param password
     * @return
     */
    public Employee searchForEmployee(String username, String password) {
        for (Employee e : employees.getEmployees()
        ) {
            if (e.getUsername().equals(username) && e.getPassword().equals(password))
                return e;
        }
        return null;
    }


    /* comboboxes */
    public void setItemsComboBoxes() {
        if (trains == null) return;
        setItemsNumberCB();
        setItemsStartCB();
        setItemsStopCB();
        setItemsDurationCB();
        setItemsPriceCB();
        setItemsFreeCB();
    }

    public void setItemsNumberCB() {
        List<Integer> elems = trains.getTrainList()
                .stream()
                .map(Train::getNumber)
                .collect(Collectors.toList());

        ObservableList<Integer> items = FXCollections.observableList(elems);
        passengerView.getNrCB().setItems(items);
    }

    public void setItemsStartCB() {
        List<String> elems = trains.getTrainList()
                .stream()
                .map(Train::getStartLoc)
                .collect(Collectors.toList());

        ObservableList<String> items = FXCollections.observableList(elems);
        passengerView.getStartCB().setItems(items);
    }

    public void setItemsStopCB() {
        List<String> elems = trains.getTrainList()
                .stream()
                .map(Train::getStopLoc)
                .collect(Collectors.toList());

        ObservableList<String> items = FXCollections.observableList(elems);
        passengerView.getStopCB().setItems(items);
    }

    public void setItemsDurationCB() {
        List<Integer> elems = trains.getTrainList()
                .stream()
                .map(Train::getDuration)
                .collect(Collectors.toList());

        ObservableList<Integer> items = FXCollections.observableList(elems);
        passengerView.getDurationCB().setItems(items);
    }

    public void setItemsPriceCB() {
        List<Float> elems = trains.getTrainList()
                .stream()
                .map(Train::getPrice)
                .collect(Collectors.toList());

        ObservableList<Float> items = FXCollections.observableList(elems);
        passengerView.getPriceCB().setItems(items);
    }

    public void setItemsFreeCB() {
        List<Integer> elems = trains.getTrainList()
                .stream()
                .map(Train::getSeats)
                .collect(Collectors.toList());

        ObservableList<Integer> items = FXCollections.observableList(elems);
        passengerView.getSeatsCB().setItems(items);
    }

    /* clear the selection of comboboxes */
    public void clearComboBoxesSelection() {
        passengerView.getNrCB().valueProperty().set(null);
        passengerView.getStartCB().valueProperty().set(null);
        passengerView.getStopCB().valueProperty().set(null);
        passengerView.getDurationCB().valueProperty().set(null);
        passengerView.getPriceCB().valueProperty().set(null);
        passengerView.getSeatsCB().valueProperty().set(null);
    }


    public void handleSearchButton() {
        List<Train> searched = trains.getTrainList();
        if (passengerView.getNrCB().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getNumber() == passengerView.getNrCB().getValue())
                    .collect(Collectors.toList());
        }
        if (passengerView.getStartCB().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getStartLoc().equals(passengerView.getStartCB().getValue()))
                    .collect(Collectors.toList());
        }
        if (passengerView.getStopCB().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getStopLoc().equals(passengerView.getStopCB().getValue()))
                    .collect(Collectors.toList());
        }
        if (passengerView.getDurationCB().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getDuration() == passengerView.getDurationCB().getValue())
                    .collect(Collectors.toList());
        }
        if (passengerView.getPriceCB().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getPrice() == passengerView.getPriceCB().getValue())
                    .collect(Collectors.toList());
        }
        if (passengerView.getSeatsCB().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getSeats() == passengerView.getSeatsCB().getValue())
                    .collect(Collectors.toList());
        }

        ArrayList<Train> aux = new ArrayList<>(searched);
        Trains searchedTrains = new Trains(aux);
        displayTable(searchedTrains);

        clearComboBoxesSelection();
    }


    public void displayTable(Trains t) {
        ObservableList<Train> trainsList = FXCollections.observableArrayList(t.getTrainList());
        passengerView.getTableT().setItems(trainsList);
    }


}
