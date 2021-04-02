package view.admin;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Employee;

public interface IAdminView {

    void handleLogoutButton() throws Exception;



    public TableView<Employee> getaTable();

    public TextField getaNameTF();

    public TextField getaEmailTF();

    public TextField getaRoleTF();

    public TextField getaUsernameTF();

    public TextField getaPasswordTF();

    public Button getaAddB();

    public Button getaRefreshB();

    public Button getaUpdateB();

    public Button getaDeleteB();

    Button getaLogoutB();
}
