/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FlightDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import model.Flight;

/**
 *
 * @author Laure-Hélène SOYEUX
 */
public class NewFlightController {

    boolean isValid = true;
    String errorMessage = "";

    public NewFlightController() {
    }

    public void addFlight(String departingAirport, String arrivalAirport, String departingDate, String departingTime, int flightDuration, double flightPrice) {

        NewFlightController nfc = new NewFlightController();
        Flight newFlight = new Flight();
        FlightDAO newFlightDao = new FlightDAO();
        String hour = departingDate + " " + departingTime;
        String errorMessage = "Veuillez entrer des données correspondant aux champs et cohérentes";

        try {
            newFlight.setDeparting_aita(departingAirport);
            newFlight.setArrival_aita(arrivalAirport);
            newFlight.setDeparting_hour(hour);
            newFlight.setDuration(flightDuration);
            newFlight.setPrice(flightPrice);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, errorMessage);
            return;
        }

        if (nfc.blinkFlight(newFlight)) {
            Flight createdFlight = newFlightDao.create(newFlight);
        } else {
            JOptionPane.showMessageDialog(null, errorMessage);
        }

    }

    //blink
    public boolean blinkFlight(Flight newFlight) {

        
        String hour = newFlight.getDeparting_hour();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy hh:mi");
        try {
            Date dateTime = sdf.parse(hour);
        } catch (ParseException ex) {
            ex.printStackTrace();
            return false;
        }

        if (newFlight.getDeparting_aita() == newFlight.getArrival_aita()) {
            return false;
        }

        if (newFlight.getDuration() <= 9) {
            return false;
        }

        if (newFlight.getPrice() < 0) {
            return false;
        }

        return true;
    }

}
