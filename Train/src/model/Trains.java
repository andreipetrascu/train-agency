package model;

import java.util.ArrayList;

public class Trains {
    private ArrayList<Train> trainList;

    public Trains() {
        this.trainList = new ArrayList<>();
    }

    public Trains(ArrayList<Train> trainList) {
        this.trainList = trainList;
    }

    /* CRUD */

    // Create
    public void addTrain(Train train) {
        if (!this.exists(train))
            this.trainList.add(train);
    }

    // Read
    public ArrayList<Train> getTrainList() {
        return trainList;
    }

    // Update
    public void updateTrain(Train train) {

    }

    // Delete
    public void deleteTrain(Train train) {

    }


    public void setTrainList(ArrayList<Train> trainList) {
        this.trainList = trainList;
    }


    /* check if train exists in the trains list */
    public boolean exists(Train train) {
        for (Train t : this.getTrainList()
        ) {
            if (t.equals(train) == true) return true;
        }
        return false;
    }

}
