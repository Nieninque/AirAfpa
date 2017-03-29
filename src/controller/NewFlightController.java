/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AirportDAO;
import dao.FlightDAO;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import model.Airport;
import model.Flight;

/**
 *
 * @author Laure-Hélène SOYEUX
 */
public class NewFlightController {

    FlightDAO fDao = new FlightDAO();
    Flight fModel = new Flight();
    String errorBlink ="";
    
    public NewFlightController(FlightDAO flightdao) {
        this.fDao = flightdao;
    }

    //methode called in the listener of the validation button of the new flight view
    public void addFlight(String departingAirport, String arrivalAirport, String departingDate,
            String departingTime, String flightDuration, String flightPrice) {

        //concatenation of the two Strings todayDate and time to insert them on the DateTime zone
        String hour = departingDate + " " + departingTime + ":00";
        
        //variables needed to parse Strings into int and double
        int fd;
        double fp;
        //convert string into num
        try {
            fd = Integer.parseInt(flightDuration); 
            fp = Double.parseDouble(flightPrice);
        }catch (NumberFormatException ex){
            errorBlink = "La durée et/ou le tarif ne sont pas correctes";
            JOptionPane.showMessageDialog(null, errorBlink);
            return;
        }
        
        //verification of the date 
        if(!this.verifyDate(departingDate)){
            errorBlink = "La date est invalide";
            JOptionPane.showMessageDialog(null, errorBlink);
            return;
        }
        
       
        //catching types errors on the user input by trying to insert informations on newFlight
        try {
            this.fModel.setDeparting_aita(departingAirport);
            this.fModel.setArrival_aita(arrivalAirport);
            this.fModel.setDeparting_hour(hour);
            this.fModel.setDuration(fd);
            this.fModel.setPrice(fp);
        } catch (Exception e) {
            //if newFlight could not be created, the this error message will show in a pop up 
            errorBlink = "Une erreur est survenue, vérifiez vos entrées et réesseyez";
            JOptionPane.showMessageDialog(null, errorBlink);
            return;
        }
        
        //pass into blink methode to say if the flight is valid
        if (this.blinkFlight(this.fModel)) {
           
            this.fDao.create(this.fModel);
            String success = "Le vol a été ajouté aux vols en attente";
            JOptionPane.showMessageDialog(null, success);
            
        }else {
            //if the blinkFlight methode return false, this error message in a pop up
            JOptionPane.showMessageDialog(null, errorBlink);
        }
        
    }//end addFlight methode

    //blinking a bit my code (after the casting of the values on addFlight methode)
    public boolean blinkFlight(Flight newFlight) {

        //casting of a String into a Date to compare it
        String hour = newFlight.getDeparting_hour();
        LocalDateTime dateTime;
        
        try{       
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateTime = LocalDateTime.parse(hour, formatter);
        }catch(Exception ex){
            this.errorBlink = "La date est invalide";
            return false;
        }
        
        //get tomorrow's dateTime
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime tomorrow = today.plus(1, ChronoUnit.DAYS);
        
       
        
        //comparing the date with today's date
        if (dateTime.isBefore(tomorrow)){
            this.errorBlink = "le vol ne peut pas partir avant demain ";
            return false;
        }

        //of course a flight could not start and finish on the same airport
        if (newFlight.getDeparting_aita().equals(newFlight.getArrival_aita()) ) {
            this.errorBlink = "L'aéroport de départ et celui d'arrivée sont identiques";
            return false;
        }

        if (newFlight.getDuration() <= 9) {
            this.errorBlink = "La durée du vol ne peut être inférieure à 9 minutes ";
            return false;
        }

        if (newFlight.getPrice() < 0) {
            this.errorBlink = "Un prix doit être positif ... ";
            //just logic don't you think ?
            return false;
        }
        
        //I think this is enought tests to say "you can go to database" !
        return true;
    }//end blinking methode
    
    public void addCombobox(JComboBox cb_departureCity, JComboBox cb_arrivalCity) {
        
        //add airports IATA in comboBoxes
        AirportDAO airportDAO = new AirportDAO();
        ArrayList<Airport> airports;
        
        airports = airportDAO.getAll();
        
        for(int i = 0; i < airports.size(); i++){
            cb_departureCity.addItem(airports.get(i).getAita());
            cb_arrivalCity.addItem(airports.get(i).getAita());
        }

    }//end addCombobox methode
    
    public boolean verifyDate(String date){
       
    String[] dateProperties = date.split("-");

    if(dateProperties != null)
    {
        int year = Integer.parseInt(dateProperties[0]);

        // A valid month by default in the case it is not provided.
        int month = dateProperties.length > 1 ? Integer.parseInt(dateProperties[1]) : 1;

        // A valid day by default in the case it is not provided.
        int day = dateProperties.length > 2 ? Integer.parseInt(dateProperties[2]) : 1;

        try
        {
            LocalDate.of(year, month, day);
            return true;
        }
        catch(DateTimeException e)
        {
            return false;
        }
    }

    return false; 
    }
}
