/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import dao.AirportDAO;
import dao.FlightDAO;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Airport;
import model.Flight;

/**
 * 
 * @author Cedric DELHOME
 */
public class FlightController {
    
    private Flight flight;
    private AirportDAO airportDAO = new AirportDAO();    
    private FlightDAO flightDAO = new FlightDAO();
    
   
    
    public JTable addRowTable(JTable jt_listFlight){
        DefaultTableModel model = (DefaultTableModel) jt_listFlight.getModel();
        ArrayList<Flight> list_flights = flightDAO.getAll();
        
        Object rowData[] = new Object[11];
        
        
        for (Flight flight : list_flights) {
            Airport startAirport = airportDAO.find(flight.getDeparting_aita());
            Airport endAirport = airportDAO.find(flight.getArrival_aita());
            String startCity = startAirport.getCity();
            String startCountry = startAirport.getCountry();
            String endCity = endAirport.getCity();
            String endCountry = endAirport.getCountry();
            rowData[0] = "DF" + flight.getId();
            rowData[1] = startCity;
            rowData[2] = startCountry;
            rowData[3] = flight.getDeparting_aita();
            rowData[4] = endCity;
            rowData[5] = endCountry;
            rowData[6] = flight.getArrival_aita();
            rowData[7] = flight.getDeparting_hour();
            rowData[8] = flight.getDeparting_hour();
            rowData[9] = flight.getDuration();
            rowData[10] = flight.getPrice();
            
            model.addRow(rowData);
        }
        return jt_listFlight;
    }
    

}
