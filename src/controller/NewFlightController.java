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
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import model.Flight;

/**
 *
 * @author Laure-Hélène SOYEUX
 */
public class NewFlightController {

    public NewFlightController() {
    }

    //methode called in the listener of the validation button of the new flight view
    public void addFlight(String departingAirport, String arrivalAirport, String departingDate, String departingTime, int flightDuration, double flightPrice) {

        //instanciation of a controller to use his blinkFlight methode
        NewFlightController nfc = new NewFlightController();
        //instanciation of a Flight, to stock informations of the new flight in it
        Flight newFlight = new Flight();
        //instanciation of a newFlightDAO to use his methodes as well
        FlightDAO newFlightDao = new FlightDAO();
        //concatenation of the two Strings date and time to insert them on the DateTime zone
        String hour = departingDate + " " + departingTime;
        //initialisation of the error message
        String errorMessage = "";

        //catching types errors on the user input by trying to insert informations on newFlight
        try {
            newFlight.setDeparting_aita(departingAirport);
            newFlight.setArrival_aita(arrivalAirport);
            newFlight.setDeparting_hour(hour);
            newFlight.setDuration(flightDuration);
            newFlight.setPrice(flightPrice);
        } catch (Exception e) {
            e.printStackTrace();
            //if newFlight could not be created, the this error message will show in a pop up 
            errorMessage = "Les données doivent correspondre aux indications"
                    + "données dans leurs champs respectifs !";
            JOptionPane.showMessageDialog(null, errorMessage);
            return;
        }

        if (nfc.blinkFlight(newFlight)) {
            Flight createdFlight = newFlightDao.create(newFlight);
        } else {
            //if the blinkFlight methode return false, this error message in a pop up
            errorMessage = "Les informations saisies ne sont pas cohérentes, "
                    + "vérifiez que votre aéroport de départ et d'arrivée "
                    + "soient différents ou encore vos dates ...";
            JOptionPane.showMessageDialog(null, errorMessage);
        }

    }

    //blinking a bit my code (after the casting of the values on addFlight methode)
    public boolean blinkFlight(Flight newFlight) {

        //preparing casting of a String into a date to compare it
        String hour = newFlight.getDeparting_hour();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy hh:mi");
        
        Date dateTime = null; 
        //get today's dateTime
        Date date= new java.util.Date(); 
        //instanciation of a gregorian calendar to use its methodes
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        //+ 1 day on the date of today
        gregorianCalendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
        // back to midnight because it's written "the next day" not "24h later"
        gregorianCalendar.set(GregorianCalendar.HOUR, 0);
        gregorianCalendar.set(GregorianCalendar.MINUTE, 0);
        //even the seconds just to be clean 
        gregorianCalendar.set(GregorianCalendar.SECOND, 0);
        //now I can put it back on date
        date = gregorianCalendar.getTime();
        
        try {
            //casting of the date
            dateTime = sdf.parse(hour);
        } catch (ParseException ex) {
            ex.printStackTrace();
            return false;
        }

        if (newFlight.getDeparting_aita() == newFlight.getArrival_aita()) {
            //of course a flight could not start and finish on the same airport, we don't have concorde 
            return false;
        }

        if (newFlight.getDuration() <= 9) {
            //just Specifications 
            return false;
        }

        if (newFlight.getPrice() < 0) {
            //just logic don't you think ?
            return false;
        }
        
        if (dateTime.before(date) || dateTime.equals(date)){
            //now I have tomorows first hour to compare my input
            return false;
        }

        //I think this is enought tests to say "you can go to database" !
        return true;
    }

}
