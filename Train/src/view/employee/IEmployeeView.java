package view.employee;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import model.Ticket;
import model.Train;
import model.Trains;

public interface IEmployeeView {
    void setTextToTextFieldsTr(Train train);

    Train getTextFromTextFieldsTr();

    void displayTableTr(Trains trains);

    void clearTextFieldsTr();

    boolean areEmptyTextFieldsTr();


    ComboBox<Integer> getTrNumberCB();

    ComboBox<String> getTrStartCB();

    ComboBox<String> getTrStopCB();

    ComboBox<Integer> getTrDurationCB();

    ComboBox<Float> getTrPriceCB();

    ComboBox<Integer> getTrFreeCB();

    TableView<Train> getTiTrainsTV();

    TableView<Ticket> getTiTicketsTV();

    Button getLogoutBT();

    ComboBox<String> getStCriterionCB();

    Button getStGenerateBT();

    public BarChart<String, Number> getStBC();

    public PieChart getStPC();

    ComboBox<String> getReTypeCB();

    public Button getReExportBT();
}
