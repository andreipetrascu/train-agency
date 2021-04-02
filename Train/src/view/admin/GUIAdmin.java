package view.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.*;
import presenter.PAdmin;

import java.net.URL;
import java.util.ResourceBundle;

public class GUIAdmin implements Initializable, IAdminView {

    private PAdmin admin;

    @FXML
    private TableView<Employee> aTable;
    @FXML
    private TableColumn<Employee, ?> aNameTC, aEmailTC, aRoleTC, aUsernameTC, aPasswordTC;
    @FXML
    private TextField aNameTF, aEmailTF, aRoleTF, aUsernameTF, aPasswordTF;
    @FXML
    private Button aAddB, aRefreshB, aUpdateB, aDeleteB, aLogoutB;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.admin = new PAdmin(this);
        this.admin.loadDataFromXML();
        initTable();

        aTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                admin.handleTable();
            }
        });

    }


    public void initTable() {
        aNameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        aEmailTC.setCellValueFactory(new PropertyValueFactory<>("email"));
        aRoleTC.setCellValueFactory(new PropertyValueFactory<>("role"));
        aUsernameTC.setCellValueFactory(new PropertyValueFactory<>("username"));
        aPasswordTC.setCellValueFactory(new PropertyValueFactory<>("password"));
    }

    public void handleAddButton() {
        admin.handleAddButton();
    }

    public void handleRefreshButton() {
        admin.handleRefreshButton();
    }

    public void handleUpdateButton() {
        admin.handleUpdateButton();
    }

    public void handleDeleteButton(){
        admin.handleDeleteButton();
    }

    @Override
    public void handleLogoutButton() throws Exception {
        admin.handleLogoutButton();
    }

    /**
     * Getters for GUI components
     *
     * @return
     */
    public TableView<Employee> getaTable() {
        return aTable;
    }

    public TextField getaNameTF() {
        return aNameTF;
    }

    public TextField getaEmailTF() {
        return aEmailTF;
    }

    public TextField getaRoleTF() {
        return aRoleTF;
    }

    public TextField getaUsernameTF() {
        return aUsernameTF;
    }

    public TextField getaPasswordTF() {
        return aPasswordTF;
    }

    public Button getaAddB() {
        return aAddB;
    }

    public Button getaRefreshB() {
        return aRefreshB;
    }

    public Button getaUpdateB() {
        return aUpdateB;
    }

    public Button getaDeleteB() {
        return aDeleteB;
    }

    public Button getaLogoutB() {
        return aLogoutB;
    }
}
