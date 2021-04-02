package view.passenger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;
import model.persistence.TrainPersistence;
import presenter.PEmployee;
import presenter.PPassenger;

import java.net.URL;
import java.util.ResourceBundle;

public class GUIPassenger implements Initializable, IPassengerView {

    PPassenger passenger;

    @FXML
    Button loginB, searchB, eBtn;
    @FXML
    ComboBox<String> startCB, stopCB;
    @FXML
    ComboBox<Integer> nrCB, durationCB, seatsCB;
    @FXML
    ComboBox<Float> priceCB;
    @FXML
    TextField userTF, passwordTF;
    @FXML
    CheckBox checkBox;
    @FXML
    TableView<Train> tableT;
    @FXML
    private TableColumn<Train, String> nrCo, startCo, stopCo, durationCo, priceCo, freeCo;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // load data from xml
        this.passenger = new PPassenger(this);
        this.passenger.loadDataFromXML();
        passenger.setItemsComboBoxes();
        initTable();
    }

    public void initTable() {
        nrCo.setCellValueFactory(new PropertyValueFactory<>("number"));
        startCo.setCellValueFactory(new PropertyValueFactory<>("startLoc"));
        stopCo.setCellValueFactory(new PropertyValueFactory<>("stopLoc"));
        durationCo.setCellValueFactory(new PropertyValueFactory<>("duration"));
        priceCo.setCellValueFactory(new PropertyValueFactory<>("price"));
        freeCo.setCellValueFactory(new PropertyValueFactory<>("seats"));
    }

    @Override
    public void setContentTable(Trains trains) {
        ObservableList<Train> trainList = FXCollections.observableArrayList(trains.getTrainList());
        tableT.setItems(trainList);
    }

    public void handleSearchButton() {
        passenger.handleSearchButton();
    }

    @Override
    public void handleLoginButton() throws Exception {
        passenger.handleLoginButton();
    }

    /* get value from comboboxes*/
    @Override
    public int getValueNumberCB() {
        return Integer.valueOf(nrCB.getValue());
    }

    @Override
    public ComboBox<String> getStartCB() {
        return startCB;
    }

    @Override
    public ComboBox<String> getStopCB() {
        return stopCB;
    }

    @Override
    public ComboBox<Integer> getNrCB() {
        return nrCB;
    }

    @Override
    public ComboBox<Integer> getDurationCB() {
        return durationCB;
    }

    @Override
    public ComboBox<Integer> getSeatsCB() {
        return seatsCB;
    }

    @Override
    public ComboBox<Float> getPriceCB() {
        return priceCB;
    }

    public TableView<Train> getTableT() {
        return tableT;
    }

    public Button getLoginB() {
        return loginB;
    }

    public TextField getUserTF() {
        return userTF;
    }

    public TextField getPasswordTF() {
        return passwordTF;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }
}


