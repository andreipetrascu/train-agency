package view.employee;


import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Employee;
import model.Ticket;
import model.Train;
import model.Trains;
import model.persistence.TicketPersistence;
import model.persistence.TrainPersistence;
import presenter.PEmployee;

import java.net.URL;
import java.util.ResourceBundle;

public class GUIEmployee implements Initializable, IEmployeeView {

    PEmployee employee;

    // main
    @FXML
    private AnchorPane employeeAP, menuAP, trainsAP, ticketsAP, statisticsAP, reportsAP;
    @FXML
    private Label trainsLB, ticketsLB, statisticsLB, reportsLB;
    @FXML
    Button logoutBT;

    // trains pane
    @FXML
    TextField trNumberTF, trStartTF, trStopTF, trDurationTF, trPriceTF, trFreeTF;
    @FXML
    Button trAddBT, trRefreshBT, trUpdateBT, trDeleteBT, trSearchBT;
    @FXML
    AnchorPane trLeftAP;
    @FXML
    ComboBox<Integer> trNumberCB, trDurationCB, trFreeCB;
    @FXML
    ComboBox<Float> trPriceCB;
    @FXML
    ComboBox<String> trStartCB, trStopCB;
    @FXML
    TableView<Train> trTable;
    @FXML
    TableColumn<Train, ?> trNumberTC, trStartTC, trStopTC, trDurationTC, trPriceTC, trFreeTC;

    // tickets pane
    @FXML
    TableView<Ticket> tiTicketsTV;
    @FXML
    TableView<Train> tiTrainsTV;
    @FXML
    TableColumn<Ticket, ?> tiTiIdTC, tiTiSeatTC, tiTiTrainTC, tiTiStartTC, tiTiStopTC, tiTiDurationTC, tiTiPriceTC;
    //@FXML
    //TableColumn<Train, ?> tiTiTrainTC, tiTiStartTC, tiTiStopTC, tiTiDurationTC, tiTiPriceTC;
    @FXML
    TableColumn<Train, ?> tiTrNumberTC, tiTrStartTC, tiTrStopTC, tiTrDurationTC, tiTrPriceTC, tiTrFreeTC;
    @FXML
    Button tiAddBT, tiRefreshTiBT, tiDeleteBT, tiRefreshTrBT;

    // statistics pane
    @FXML
    ComboBox<String> stCriterionCB;
    @FXML
    Button stGenerateBT;
    @FXML
    BarChart<String, Number> stBC;
    @FXML
    PieChart stPC;

    // reports pane
    @FXML
    ComboBox<String> reTypeCB;
    @FXML
    Button reExportBT;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /* load data from xml file */

        this.employee = new PEmployee(this);
        this.employee.loadDataFromXML();
        this.employee.setTextToComboSt();
        this.employee.setTextToComboRe();


        trainsLB.setOnMouseClicked(mouseEvent -> {
            trainsAP.setVisible(true);
            ticketsAP.setVisible(false);
            statisticsAP.setVisible(false);
            reportsAP.setVisible(false);
        });

        ticketsLB.setOnMouseClicked(mouseEvent -> {
            trainsAP.setVisible(false);
            ticketsAP.setVisible(true);
            statisticsAP.setVisible(false);
            reportsAP.setVisible(false);
        });

        statisticsLB.setOnMouseClicked(mouseEvent -> {
            trainsAP.setVisible(false);
            ticketsAP.setVisible(false);
            statisticsAP.setVisible(true);
            reportsAP.setVisible(false);
        });

        reportsLB.setOnMouseClicked(mouseEvent -> {
            trainsAP.setVisible(false);
            ticketsAP.setVisible(false);
            statisticsAP.setVisible(false);
            reportsAP.setVisible(true);
        });

        initTableTr(trNumberTC, trStartTC, trStopTC, trDurationTC, trPriceTC, trFreeTC);
        initTableTr(tiTrNumberTC, tiTrStartTC, tiTrStopTC, tiTrDurationTC, tiTrPriceTC, tiTrFreeTC);
        initTableTi();

        /* set data to comboboxes */
        employee.setItemsComboBoxes();


        /* every time a row in trains table is selected, the train object is updated */
        trTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                employee.handleTrTable(trTable);
            }
        });

        tiTrainsTV.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                employee.handleTiTrTable();
            }
        });

        tiTicketsTV.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                employee.handleTiTiTable();
            }
        });

    }


    public void initTableTr(TableColumn<Train, ?> trNumberTC, TableColumn<Train, ?> trStartTC,
                            TableColumn<Train, ?> trStopTC, TableColumn<Train, ?> trDurationTC,
                            TableColumn<Train, ?> trPriceTC, TableColumn<Train, ?> trFreeTC) {
        trNumberTC.setCellValueFactory(new PropertyValueFactory<>("number"));
        trStartTC.setCellValueFactory(new PropertyValueFactory<>("startLoc"));
        trStopTC.setCellValueFactory(new PropertyValueFactory<>("stopLoc"));
        trDurationTC.setCellValueFactory(new PropertyValueFactory<>("duration"));
        trPriceTC.setCellValueFactory(new PropertyValueFactory<>("price"));
        trFreeTC.setCellValueFactory(new PropertyValueFactory<>("seats"));
    }

    public void initTableTi() {
        tiTiIdTC.setCellValueFactory(new PropertyValueFactory<>("id"));
        tiTiSeatTC.setCellValueFactory(new PropertyValueFactory<>("seat"));
        tiTiTrainTC.setCellValueFactory(new PropertyValueFactory<>("number"));
        tiTiStartTC.setCellValueFactory(new PropertyValueFactory<>("startLoc"));
        tiTiStopTC.setCellValueFactory(new PropertyValueFactory<>("stopLoc"));
        tiTiDurationTC.setCellValueFactory(new PropertyValueFactory<>("duration"));
        tiTiPriceTC.setCellValueFactory(new PropertyValueFactory<>("price"));

    }


    // trains pane
    public void handleAddButton() {
        this.employee.handleAddButton();
    }

    public void handleRefreshButton() {
        this.employee.handleRefreshButton();
    }

    public void handleUpdateButton() {
        this.employee.handleUpdateButton();
    }

    public void handleDeleteButton() {
        this.employee.handleDeleteButton();
    }

    public void handleSearchButton() {
        this.employee.handleSearchButton();
    }

    @Override
    public void setTextToTextFieldsTr(Train train) {
        trNumberTF.setText(String.valueOf(train.getNumber()));
        trStartTF.setText(train.getStartLoc());
        trStopTF.setText(train.getStopLoc());
        trDurationTF.setText(String.valueOf(train.getDuration()));
        trPriceTF.setText(String.valueOf(train.getPrice()));
        trFreeTF.setText(String.valueOf(train.getSeats()));
    }

    @Override
    public void clearTextFieldsTr() {
        trNumberTF.clear();
        trStartTF.clear();
        trStopTF.clear();
        trDurationTF.clear();
        trPriceTF.clear();
        trFreeTF.clear();
    }

    @Override
    public Train getTextFromTextFieldsTr() {
        Train train = new Train(Integer.parseInt(trNumberTF.getText()),
                trStartTF.getText(), trStopTF.getText(), Integer.parseInt(trDurationTF.getText()),
                Float.parseFloat(trPriceTF.getText()), Integer.parseInt(trFreeTF.getText()));
        return train;
    }

    @Override
    public boolean areEmptyTextFieldsTr() {
        if (trNumberTF.getText().length() == 0) return true;
        if (trStartTF.getText().length() == 0) return true;
        if (trStopTF.getText().length() == 0) return true;
        if (trDurationTF.getText().length() == 0) return true;
        if (trPriceTF.getText().length() == 0) return true;
        if (trFreeTF.getText().length() == 0) return true;
        return false;
    }

    @Override
    public void displayTableTr(Trains trains) {
        ObservableList<Train> trainsList = FXCollections.observableArrayList(trains.getTrainList());
        trTable.setItems(trainsList);
    }


    /* tickets anchor pane */

    public void handleRefreshButtonTiTr() {
        this.employee.handleRefreshButtonTiTr();
    }

    public void handleRefreshButtonTiTi() {
        this.employee.handleRefreshButtonTiTi();
    }

    public void handleAddButtonTi() {
        this.employee.handleAddButtonTi();
    }

    public void handleDeleteButtonTi() {
        this.employee.handleDeleteButtonTi();
    }


    /* statistics anchor pane */
    public void handleGenerateButton() {
        employee.handleGenerateButton();
    }


    /* reports anchor pane */
    public void handleExportButton() {
        employee.handleExportButton();
    }

    /* logout */
    public void handleLogoutButton() throws Exception {
        employee.handleLogoutButton();
    }

    @Override
    public ComboBox<Integer> getTrNumberCB() {
        return trNumberCB;
    }

    @Override
    public ComboBox<Integer> getTrDurationCB() {
        return trDurationCB;
    }

    @Override
    public ComboBox<Integer> getTrFreeCB() {
        return trFreeCB;
    }

    @Override
    public ComboBox<Float> getTrPriceCB() {
        return trPriceCB;
    }

    @Override
    public ComboBox<String> getTrStartCB() {
        return trStartCB;
    }

    @Override
    public ComboBox<String> getTrStopCB() {
        return trStopCB;
    }

    public TableView<Train> getTiTrainsTV() {
        return tiTrainsTV;
    }

    @Override
    public TableView<Ticket> getTiTicketsTV() {
        return tiTicketsTV;
    }

    @Override
    public Button getLogoutBT() {
        return logoutBT;
    }

    @Override
    public ComboBox<String> getStCriterionCB() {
        return stCriterionCB;
    }

    @Override
    public Button getStGenerateBT() {
        return stGenerateBT;
    }

    @Override
    public BarChart<String, Number> getStBC() {
        return stBC;
    }

    @Override
    public PieChart getStPC() {
        return stPC;
    }

    @Override
    public ComboBox<String> getReTypeCB() {
        return reTypeCB;
    }

    @Override
    public Button getReExportBT() {
        return reExportBT;
    }
}
