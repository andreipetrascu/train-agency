package presenter;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Ticket;
import model.Tickets;
import model.Train;
import model.Trains;
import model.persistence.TicketPersistence;
import model.persistence.TrainPersistence;
import view.employee.IEmployeeView;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.concurrent.ThreadLocalRandom;


public class PEmployee {

    private IEmployeeView employeeView;
    
    private Trains trains;
    private Tickets tickets;

    TrainPersistence trainPersistence = new TrainPersistence("./trains.xml");
    TicketPersistence ticketPersistence = new TicketPersistence("./tickets.xml");

    // train object for table selection
    private Train trTrain;
    private Train tiTrain;
    private Ticket tiTicket;


    public PEmployee(IEmployeeView employeeView) {
        this.employeeView = employeeView;
        trains = new Trains();
        tickets = new Tickets();
    }

    public void loadDataFromXML() {
        this.trains = trainPersistence.load();
        this.tickets = ticketPersistence.load();
    }


    /* trains anchor pane */

    public void handleTrTable(TableView<Train> table) {
        trTrain = table.getSelectionModel().getSelectedItem();
        if (trTrain != null)
            employeeView.setTextToTextFieldsTr(trTrain);
    }

    public void handleAddButton() {
        if (employeeView.areEmptyTextFieldsTr() == true) return;
        Train train = employeeView.getTextFromTextFieldsTr();
        trains.addTrain(train);
        employeeView.displayTableTr(getTrains());
        employeeView.clearTextFieldsTr();
        trTrain = null;
        setItemsComboBoxes();
    }

    public void handleRefreshButton() {
        employeeView.displayTableTr(getTrains());
        employeeView.clearTextFieldsTr();
        trTrain = null;
        setItemsComboBoxes();
    }

    public void handleUpdateButton() {
        if (employeeView.areEmptyTextFieldsTr()) return;
        Train train = employeeView.getTextFromTextFieldsTr();
        if (!trains.exists(train)) {
            int index = trains.getTrainList().indexOf(trTrain);
            trains.getTrainList().set(index, train);
        }
        employeeView.displayTableTr(getTrains());
        employeeView.clearTextFieldsTr();
        trTrain = null;
        setItemsComboBoxes();
    }

    public void handleDeleteButton() {
        if (trTrain == null) return;
        trains.getTrainList().remove(trTrain);
        employeeView.displayTableTr(getTrains());
        employeeView.clearTextFieldsTr();
        trTrain = null;
        setItemsComboBoxes();
    }

    public void handleSearchButton() {

        List<Train> searched = trains.getTrainList();
        if (employeeView.getTrNumberCB().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getNumber() == employeeView.getTrNumberCB().getValue())
                    .collect(Collectors.toList());
        }
        if (employeeView.getTrStartCB().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getStartLoc().equals(employeeView.getTrStartCB().getValue()))
                    .collect(Collectors.toList());
        }
        if (employeeView.getTrStopCB().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getStopLoc().equals(employeeView.getTrStopCB().getValue()))
                    .collect(Collectors.toList());
        }
        if (employeeView.getTrDurationCB().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getDuration() == employeeView.getTrDurationCB().getValue())
                    .collect(Collectors.toList());
        }
        if (employeeView.getTrPriceCB().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getPrice() == employeeView.getTrPriceCB().getValue())
                    .collect(Collectors.toList());
        }
        if (employeeView.getTrFreeCB().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getSeats() == employeeView.getTrFreeCB().getValue())
                    .collect(Collectors.toList());
        }

        ArrayList<Train> aux = new ArrayList<>(searched);
        Trains searchedTrains = new Trains(aux);
        employeeView.displayTableTr(searchedTrains);

        clearComboBoxesSelection();
    }

    /* clear the selection of comboboxes */
    public void clearComboBoxesSelection() {
        employeeView.getTrNumberCB().valueProperty().set(null);
        employeeView.getTrStartCB().valueProperty().set(null);
        employeeView.getTrStopCB().valueProperty().set(null);
        employeeView.getTrDurationCB().valueProperty().set(null);
        employeeView.getTrPriceCB().valueProperty().set(null);
        employeeView.getTrFreeCB().valueProperty().set(null);
    }

    // combo boxes

    public void setItemsComboBoxes() {
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
        employeeView.getTrNumberCB().setItems(items);
    }

    public void setItemsStartCB() {
        List<String> elems = trains.getTrainList()
                .stream()
                .map(Train::getStartLoc)
                .collect(Collectors.toList());

        ObservableList<String> items = FXCollections.observableList(elems);
        employeeView.getTrStartCB().setItems(items);
    }

    public void setItemsStopCB() {
        List<String> elems = trains.getTrainList()
                .stream()
                .map(Train::getStopLoc)
                .collect(Collectors.toList());

        ObservableList<String> items = FXCollections.observableList(elems);
        employeeView.getTrStopCB().setItems(items);
    }

    public void setItemsDurationCB() {
        List<Integer> elems = trains.getTrainList()
                .stream()
                .map(Train::getDuration)
                .collect(Collectors.toList());

        ObservableList<Integer> items = FXCollections.observableList(elems);
        employeeView.getTrDurationCB().setItems(items);
    }

    public void setItemsPriceCB() {
        List<Float> elems = trains.getTrainList()
                .stream()
                .map(Train::getPrice)
                .collect(Collectors.toList());

        ObservableList<Float> items = FXCollections.observableList(elems);
        employeeView.getTrPriceCB().setItems(items);
    }

    public void setItemsFreeCB() {
        List<Integer> elems = trains.getTrainList()
                .stream()
                .map(Train::getSeats)
                .collect(Collectors.toList());

        ObservableList<Integer> items = FXCollections.observableList(elems);
        employeeView.getTrFreeCB().setItems(items);
    }




    /* tickets anchor pane */

    public void handleTiTrTable() {
        tiTrain = employeeView.getTiTrainsTV().getSelectionModel().getSelectedItem();
    }

    public void handleTiTiTable() {
        tiTicket = employeeView.getTiTicketsTV().getSelectionModel().getSelectedItem();
    }

    public void handleAddButtonTi() {
        if (tiTrain == null) return;
        if (tiTrain.getSeats() < 1) return;

        int randomId = ThreadLocalRandom.current().nextInt(100, 10000);
        int seat = tiTrain.getSeats();

        Ticket ticket = new Ticket(randomId, seat, tiTrain);

        if (tickets == null)
            tickets.addTicket(ticket);
        else if (!tickets.exists(ticket))
            tickets.addTicket(ticket);

        displayTableTiTi();

        int index = trains.getTrainList().indexOf(tiTrain);
        trains.getTrainList().get(index).setSeats(seat - 1);

        displayTableTiTr();

        tiTrain = null;
    }

    public void handleRefreshButtonTiTi() {
        displayTableTiTi();
        tiTrain = null;
    }

    public void handleDeleteButtonTi() {
        if (tiTicket == null) return;
        tickets.getTickets().remove(tiTicket);
        displayTableTiTi();

        tiTicket = null;
    }

    public void handleRefreshButtonTiTr() {
        displayTableTiTr();
        tiTrain = null;
    }

    public void displayTableTiTi() {
        ObservableList<Ticket> ticketsList = FXCollections.observableArrayList(tickets.getTickets());
        employeeView.getTiTicketsTV().setItems(ticketsList);
    }

    public void displayTableTiTr() {
        ObservableList<Train> trainsList = FXCollections.observableArrayList(trains.getTrainList());
        employeeView.getTiTrainsTV().setItems(trainsList);
    }



    /* statistics anchor pane */

    public void handleGenerateButton() {
        String criterion = employeeView.getStCriterionCB().getValue();
        if (criterion == null) return;
        employeeView.getStBC().setTitle("Tickets Comparison");
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        HashMap<String, Number> hashMap = null;

        switch (criterion) {
            case "start location":
                series.setName("comparison by start station");
                hashMap = computeTicketsByStartLoc();
                break;
            case "stop location":
                series.setName("comparison by stop station");
                hashMap = computeTicketsByStopLoc();
                break;
            case "price":
                series.setName("comparison by price");
                hashMap = computeTicketsByPrice();
                break;

            default:
                break;
        }

        for (Map.Entry<String, Number> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            Number value = entry.getValue();
            series.getData().add(new XYChart.Data(key, value));
            list.add(new PieChart.Data(key, value.doubleValue()));
        }

        employeeView.getStBC().getData().add(series);
        employeeView.getStPC().setData(list);
    }

    public HashMap<String, Number> computeTicketsByStartLoc() {
        HashMap<String, Number> hashMap = new HashMap<>();
        Set<String> set = new HashSet<String>();

        for (int i = 0; i < tickets.getTickets().size(); i++) {
            set.add(tickets.getTickets().get(i).getStartLoc());
        }

        for (String s : set
        ) {
            int count = 0;
            for (Ticket t : tickets.getTickets()
            ) {
                if (t.getStartLoc().equals(s))
                    count++;
            }
            hashMap.put(s, count);
        }

        return hashMap;
    }

    public HashMap<String, Number> computeTicketsByStopLoc() {
        HashMap<String, Number> hashMap = new HashMap<>();
        Set<String> set = new HashSet<String>();

        for (int i = 0; i < tickets.getTickets().size(); i++) {
            set.add(tickets.getTickets().get(i).getStopLoc());
        }

        for (String s : set
        ) {
            int count = 0;
            for (Ticket t : tickets.getTickets()
            ) {
                if (t.getStopLoc().equals(s))
                    count++;
            }
            hashMap.put(s, count);
        }

        return hashMap;
    }

    public HashMap<String, Number> computeTicketsByPrice() {
        HashMap<String, Number> hashMap = new HashMap<>();
        Set<String> set = new HashSet<>();

        for (int i = 0; i < tickets.getTickets().size(); i++) {
            set.add(String.valueOf(tickets.getTickets().get(i).getPrice()));
        }

        for (String s : set
        ) {
            int count = 0;
            for (Ticket t : tickets.getTickets()
            ) {
                if (s.equals(String.valueOf(t.getPrice())))
                    count++;
            }
            hashMap.put(s, count);
        }

        return hashMap;
    }

    public void setTextToComboSt() {
        String[] cr = {"start location", "stop location", "price"};
        List<String> criteria = Arrays.asList(cr);
        ObservableList<String> items = FXCollections.observableList(criteria);
        employeeView.getStCriterionCB().setItems(items);
    }



    /* reports anchor pane */

    public void setTextToComboRe() {
        String[] cr = {"csv", "json"};
        List<String> criteria = Arrays.asList(cr);
        ObservableList<String> items = FXCollections.observableList(criteria);
        employeeView.getReTypeCB().setItems(items);
    }

    public void handleExportButton() {
        String fileType = employeeView.getReTypeCB().getValue();
        if (fileType == null) return;
        switch (fileType) {
            case "csv":
                writeTrainsToCSV();
                break;
            case "json":
                writeTrainsToJSON();
                break;
            default:
                writeTrainsToCSV();
        }
        writeTrainsToCSV();
    }


    private static final String CSV_SEPARATOR = ",";

    private void writeTrainsToCSV() {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("trainsCSV.csv"), "UTF-8"));
            for (Train t : this.trains.getTrainList()) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(t.getNumber() <= 0 ? "" : t.getNumber());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(t.getStartLoc().length() == 0 ? "" : t.getStartLoc());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(t.getStopLoc().length() == 0 ? "" : t.getStopLoc());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(t.getDuration() < 0 ? "" : t.getDuration());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(t.getPrice() < 0.0f ? "" : t.getPrice());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(t.getSeats() < 0 ? "" : t.getSeats());
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (UnsupportedEncodingException e) {
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    private void writeTrainsToJSON() {
        Gson json = new Gson();
        List<String> trainsJSON = new ArrayList<>();
        for (Train t : trains.getTrainList()
        ) {
            trainsJSON.add(json.toJson(t));
        }
        System.out.println(trainsJSON);
    }

    /* logout */

    public void handleLogoutButton() throws Exception {

        /* save data to .xml before logging out */

        trainPersistence.save(trains);
        ticketPersistence.save(tickets);

        Parent root = FXMLLoader.load(getClass().getResource("/view/passenger/passengerPage.fxml"));
        Stage window = (Stage) employeeView.getLogoutBT().getScene().getWindow();
        window.setScene(new Scene(root, 1293, 858));
    }

    public Trains getTrains() {
        return trains;
    }

    public void setTrains(Trains trains) {
        this.trains = trains;
    }
}
