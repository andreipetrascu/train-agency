package model;

public class Ticket {
    private int id;
    private int seat;
    private Train train;

    public Ticket() {
    }

    public Ticket(int id, int seat, Train train) {
        this.id = id;
        this.seat = seat;
        this.train = train;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Ticket)) {
            return false;
        }

        Ticket t = (Ticket) o;

        // Compare the data members and return accordingly
        return id == t.getId() && seat == t.getSeat() && train.equals(t.getTrain());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }


    // getters from train for table
    public int getNumber() {
        return train.getNumber();
    }

    public String getStartLoc() {
        return train.getStartLoc();
    }

    public String getStopLoc() {
        return train.getStopLoc();
    }

    public int getDuration() {
        return train.getDuration();
    }

    public float getPrice() {
        return train.getPrice();
    }

}
