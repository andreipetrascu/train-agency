package model.persistence;

import model.Train;
import model.Trains;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TrainPersistence {
    private String filename;

    public TrainPersistence(String filename) {
        this.filename = filename;
    }


    /* load and save from .xml file */

    public Trains load() {
        try {
            FileInputStream fis = new FileInputStream(new File(filename));
            XMLDecoder decoder = new XMLDecoder(fis);
            Trains trains = (Trains) decoder.readObject();
            decoder.close();
            fis.close();
            return trains;
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(Trains trains) {
        try {
            FileOutputStream fos = new FileOutputStream(new File(filename));
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.writeObject(trains);
            encoder.close();
            fos.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

}
