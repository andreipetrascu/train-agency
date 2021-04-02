package model.persistence;

import model.Ticket;
import model.Tickets;
import model.Trains;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TicketPersistence {

    private String filename;

    public TicketPersistence(String filename) {
        this.filename = filename;
    }

    /* load and save from .xml file */
    public Tickets load() {
        try {
            FileInputStream fis = new FileInputStream(new File(filename));
            XMLDecoder decoder = new XMLDecoder(fis);
            Tickets tickets = (Tickets) decoder.readObject();
            decoder.close();
            fis.close();
            return tickets;
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void save(Tickets tickets) {
        try {
            FileOutputStream fos = new FileOutputStream(new File(filename));
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.writeObject(tickets);
            encoder.close();
            fos.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

}





