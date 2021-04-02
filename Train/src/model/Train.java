package model;

public class Train {

    private int number;
    private String startLoc;
    private String stopLoc;
    private int duration;
    private float price;
    private int seats;

    public Train() {
    }

    public Train(int number, String startLoc, String stopLoc, int duration, float price, int seats) {
        this.number = number;
        this.startLoc = startLoc;
        this.stopLoc = stopLoc;
        this.duration = duration;
        this.price = price;
        this.seats = seats;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Train)) {
            return false;
        }

        Train t = (Train) o;

        // Compare the data members and return accordingly
        return (this.getNumber() == t.getNumber()) && this.getStartLoc().equals(t.getStartLoc()) &&
                this.getStopLoc().equals(t.getStopLoc()) && (this.getDuration() == t.getDuration()) &&
                (this.getPrice() == t.getPrice()) && (this.getSeats() == t.getSeats());
    }


    @Override
    public String toString() {
        return number + " " + startLoc + " " + stopLoc + " " + duration + " " + price + " " + seats;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStartLoc() {
        return startLoc;
    }

    public void setStartLoc(String startLoc) {
        this.startLoc = startLoc;
    }

    public String getStopLoc() {
        return stopLoc;
    }

    public void setStopLoc(String stopLoc) {
        this.stopLoc = stopLoc;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}
