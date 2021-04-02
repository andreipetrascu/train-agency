package view.passenger;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import model.Train;
import model.Trains;

public interface IPassengerView {

    void handleLoginButton() throws Exception;

    void setContentTable(Trains trains);

    int getValueNumberCB();

    ComboBox<String> getStartCB();

    ComboBox<String> getStopCB();

    ComboBox<Integer> getNrCB();

    ComboBox<Integer> getDurationCB();

    ComboBox<Integer> getSeatsCB();

    ComboBox<Float> getPriceCB();

    TableView<Train> getTableT();

    Button getLoginB();

    TextField getUserTF();

    TextField getPasswordTF();

    CheckBox getCheckBox();

}
