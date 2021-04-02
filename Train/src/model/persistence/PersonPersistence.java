package model.persistence;

import model.Employee;
import model.Employees;
import model.Tickets;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PersonPersistence {

    private String filename;

    public PersonPersistence(String filename) {
        this.filename = filename;
    }

    /* load and save from .xml file */
    public Employees load() {
        try {
            FileInputStream fis = new FileInputStream(new File(filename));
            XMLDecoder decoder = new XMLDecoder(fis);
            Employees employees = (Employees) decoder.readObject();
            decoder.close();
            fis.close();
            return employees;
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void save(Employees employees) {
        try {
            FileOutputStream fos = new FileOutputStream(new File(filename));
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.writeObject(employees);
            encoder.close();
            fos.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
