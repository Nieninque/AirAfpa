/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AirportDAO;
import dao.FlightDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
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
    public void addFlight(String departingAirport, String arrivalAirport, String departingDate, String departingTime, String flightDuration, String flightPrice) {

        //concatenation of the two Strings todayDate and time to insert them on the DateTime zone
        String hour = departingDate + " " + departingTime + ":00";
        //initialisation of the error message
        String errorMessage;
        //variables needed to parse Strings into int and double
        int fd;
        double fp;
        try {
            
            fd = Integer.parseInt(flightDuration); 
            fp = Double.parseDouble(flightPrice);
        }catch (Exception ex){
            errorMessage = "La durée et/ou le tarif ne sont pas correctes";
            JOptionPane.showMessageDialog(null, errorMessage);
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
            errorMessage = "Une erreur est survenue, vérifiez vos entrées et réesseyez";
            JOptionPane.showMessageDialog(null, errorMessage);
            return;
        }
        
        if (this.blinkFlight(this.fModel)) {
            Flight createdFlight = this.fDao.create(this.fModel);
            String success = "Le vol a été ajouté aux vols en attente";
            JOptionPane.showMessageDialog(null, success);
        }else {
            //if the blinkFlight methode return false, this error message in a pop up
            
            JOptionPane.showMessageDialog(null, errorBlink);
            return;
        }
        
    }//end addFlight methode

    //blinking a bit my code (after the casting of the values on addFlight methode)
    public boolean blinkFlight(Flight newFlight) {

        //preparing casting of a String into a todayDate to compare it
        String hour = newFlight.getDeparting_hour();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date dateTime = null; 
        try {
            //casting of the todayDate
            dateTime = sdf.parse(hour);
        } catch (ParseException ex) {
            ex.printStackTrace();
            errorBlink = "La date saisie est incorrecte";
            return false;
        }
        
        
        //get today's dateTime
        Date todayDate= new java.util.Date(); 
        //instanciation of a gregorian calendar to use its methodes
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(todayDate);
        //+ 1 day on the todayDate of today
        gregorianCalendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
        // back to midnight because it's written "the next day" not "24h later"
        gregorianCalendar.set(GregorianCalendar.HOUR, 0);
        gregorianCalendar.set(GregorianCalendar.MINUTE, 0);
        //even the seconds just to be clean 
        gregorianCalendar.set(GregorianCalendar.SECOND, 0);
        //now I can put it back on todayDate
        todayDate = gregorianCalendar.getTime();
        
        

        if (newFlight.getDeparting_aita().equals(newFlight.getArrival_aita()) ) {
            //of course a flight could not start and finish on the same airport, we don't have concorde 
            this.errorBlink = "L'aéroport de départ et celui d'arrivée sont identiques";
            return false;
        }

        if (newFlight.getDuration() <= 9) {
            this.errorBlink = "La durée minimale de vol est de 9 minutes ";
            return false;
        }

        if (newFlight.getPrice() < 0) {
            this.errorBlink = "Un prix doit être positif ... ";
            //just logic don't you think ?
            return false;
        }
        
        if (todayDate.before(dateTime)){
            this.errorBlink = "le vol ne peut pas partir avant demain ";
            //now I have tomorows first hour to compare my input
            return false;
        }

        //I think this is enought tests to say "you can go to database" !
        return true;
    }//end blinking methode
    
    public void addCombobox(JComboBox cb_departureCity, JComboBox cb_arrivalCity) {
        
        //add airports cities in comboBoxes
        AirportDAO airportDAO = new AirportDAO();
        ArrayList<Airport> airports = new ArrayList<>();
        
        airports = airportDAO.getAll();
        
        for(int i = 0; i < airports.size(); i++){
            cb_departureCity.addItem(airports.get(i).getAita());
            cb_arrivalCity.addItem(airports.get(i).getAita());
        }

    }//end addCombobox methode
    
}
