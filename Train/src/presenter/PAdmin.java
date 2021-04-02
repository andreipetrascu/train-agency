package presenter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Employee;
import model.Employees;
import model.Train;
import model.persistence.PersonPersistence;
import view.admin.IAdminView;

public class PAdmin {

    private IAdminView adminView;
    private Employees employees;
    private Employee employee;

    PersonPersistence personPersistence = new PersonPersistence("./employees.xml");

    public PAdmin(IAdminView adminView) {
        this.adminView = adminView;
    }

    public void loadDataFromXML() {
        this.employees = personPersistence.load();
    }

    public void handleTable() {
        employee = adminView.getaTable().getSelectionModel().getSelectedItem();
        setTextToTextFields(this.employee);
    }

    private void setTextToTextFields(Employee employee) {
        adminView.getaNameTF().setText(employee.getName());
        adminView.getaEmailTF().setText(employee.getEmail());
        adminView.getaRoleTF().setText(employee.getRole());
        adminView.getaUsernameTF().setText(employee.getUsername());
        adminView.getaPasswordTF().setText(employee.getPassword());
    }

    public void handleAddButton() {
        if (areEmptyTextFields()) return;
        Employee employee = makeEmployeeFromTextFields();
        employees.addEmployee(employee);
        displayTable();
        clearTextFields();
        this.employee = null;
    }

    private void clearTextFields() {
        adminView.getaNameTF().clear();
        adminView.getaEmailTF().clear();
        adminView.getaRoleTF().clear();
        adminView.getaUsernameTF().clear();
        adminView.getaPasswordTF().clear();
    }

    private void displayTable() {
        ObservableList<Employee> employeesList = FXCollections.observableArrayList(employees.getEmployees());
        adminView.getaTable().setItems(employeesList);
    }

    private Employee makeEmployeeFromTextFields() {
        Employee employee = new Employee(adminView.getaNameTF().getText(),
                adminView.getaEmailTF().getText(),
                adminView.getaRoleTF().getText(),
                adminView.getaUsernameTF().getText(),
                adminView.getaPasswordTF().getText());
        return employee;
    }

    private boolean areEmptyTextFields() {
        if (adminView.getaNameTF().getText().length() == 0) return true;
        if (adminView.getaEmailTF().getText().length() == 0) return true;
        if (adminView.getaRoleTF().getText().length() == 0) return true;
        if (adminView.getaUsernameTF().getText().length() == 0) return true;
        if (adminView.getaPasswordTF().getText().length() == 0) return true;
        return false;
    }


    public void handleRefreshButton() {
        displayTable();
        clearTextFields();
        employee = null;
    }

    public void handleUpdateButton() {
        if (areEmptyTextFields()) return;
        Employee e = makeEmployeeFromTextFields();
        // if (!employees.exists(employee)) {
        int index = employees.getEmployees().indexOf(employee);
        employees.getEmployees().set(index, e);
        // }
        displayTable();
        clearTextFields();
        this.employee = null;
    }

    public void handleDeleteButton() {
        if (employee == null) return;
        employees.getEmployees().remove(employee);
        displayTable();
        clearTextFields();
        this.employee = null;
    }


    public void handleLogoutButton() throws Exception {
        personPersistence.save(employees);

        Parent root = FXMLLoader.load(getClass().getResource("/view/passenger/passengerPage.fxml"));
        Stage window = (Stage) adminView.getaLogoutB().getScene().getWindow();
        window.setScene(new Scene(root, 1293, 858));
    }
}
