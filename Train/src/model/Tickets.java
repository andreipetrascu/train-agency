package model;

import java.util.ArrayList;

public class Tickets {

    private ArrayList<Ticket> tickets;
    private int freeSeats;

    public Tickets() {
        this.tickets = new ArrayList<>();
    }


    /* check if a ticket exists in the tickets list */
    public boolean exists(Ticket ticket) {
        for (Ticket t : this.getTickets()
        ) {
            if (t.equals(ticket) == true) return true;
        }
        return false;
    }

    public Tickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }

    public void addTicket(Ticket ticket) {
        if (!this.exists(ticket))
            this.tickets.add(ticket);
    }
}
