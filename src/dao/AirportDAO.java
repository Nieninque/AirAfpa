/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Airport;
import model.Flight;
import model.Flight.InOut;

/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 */
public class AirportDAO extends DAO<Airport, String> {

    public AirportDAO() {
        super();
    }

    /**
     *
     * Create airport
     *
     * @param airport
     * @return airport object
     */
    @Override
    public Airport create(Airport airport) {
        // create aiport empty
        Airport airportCreate = new Airport();
        //check if connect db
        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "INSERT INTO airports (aita,city,country) VALUES (?,?,?)";
                // prepared requete 
                PreparedStatement pst = this.bddmanager.getConnectionManager().prepareStatement(requete);
                // insert value in requete
                pst.setString(1, airport.getAita());
                pst.setString(2, airport.getCity());
                pst.setString(3, airport.getCountry());
                // excute update row in table
                int insert = pst.executeUpdate();
                // if update in table 
                if (insert != 0) {
                    // assign airport
                    airportCreate = airport;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return airportCreate;
            }

        } else {
            return airportCreate;
        }

        return airportCreate;
    }

    /**
     * Update airport
     *
     * @param airport
     * @return true is update airport, false isn't update
     */
    @Override
    public boolean update(Airport airport) {
        boolean success = false;
        //check if connect db
        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "Update airports set city = ?,country = ? WHERE aita LIKE ?";
                // prepared requete 
                PreparedStatement pst = this.bddmanager.getConnectionManager().prepareStatement(requete);
                // insert value in requete
                pst.setString(1, airport.getCity());
                pst.setString(2, airport.getCountry());
                pst.setString(3, airport.getAita());
                // excute delete row in table
                int insert = pst.executeUpdate();
                // if delete in table 
                if (insert != 0) {
                    success = true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return success;
            }

        } else {
            return success;
        }
        return success;
    }

    /**
     * delete airport
     *
     * @param primary_key
     * @return true is delete airport, false isn't delete
     */
    @Override
    public boolean delete(String primary_key) {
        boolean success = false;
        //check if connect db
        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "DELETE FROM airports WHERE aita LIKE ?";
                // prepared requete 
                PreparedStatement pst = this.bddmanager.getConnectionManager().prepareStatement(requete);
                // insert value in requete
                pst.setString(1, primary_key);
                // excute delete row in table
                int insert = pst.executeUpdate();
                // if insert in table 
                if (insert != 0) {
                    success = true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return success;
            }

        } else {
            return success;
        }
        return success;

    }

    /**
     * get all airports
     *
     * @return all airports
     */
    @Override
    public ArrayList getAll() {
        // create array airport empty
        ArrayList<Airport> listAirport = new ArrayList<>();
        //check if connect db
        if (this.bddmanager.connect()) {

            try {
                // create requete 
                Statement st = this.bddmanager
                        .getConnectionManager()
                        .createStatement();
                String requete = "SELECT * FROM airports";
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // insert all airports in array object airport
                while (rs.next()) {
                    Airport el = new Airport(
                            rs.getString("aita"),
                            rs.getString("city"),
                            rs.getString("country")
                    );
                    listAirport.add(el);

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return listAirport;
            }

        } else {
            return listAirport;
        }

        return listAirport;
    }

    /**
     * find airport
     *
     * @param primary_key
     * @return airport
     */
    @Override
    public Airport find(String primary_key) {
        // create array airport empty
        Airport airport = new Airport();
        //check if connect db
        if (this.bddmanager.connect()) {

            try {
                // create statement for find 
                Statement st = this.bddmanager.getConnectionManager().createStatement();
                // create requete add primary key
                String requete = "SELECT * FROM airports WHERE aita LIKE \"" + primary_key + "\"";
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // if result is full
                if (rs.next()) {
                    // insert airports in object
                    airport.setAita(rs.getString("aita"));
                    airport.setCity(rs.getString("city"));
                    airport.setCountry(rs.getString("country"));
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return airport;
            }

        } else {
            return airport;
        }

        return airport;
    }

    /**
     * It checks if the object airport is filled or empty
     *
     * @param airport
     * @return false is empty and true is full
     */
    public boolean isValid(Airport airport) {
        boolean isValid = true;

        // if code aita is empty or null
        if (airport.getAita().isEmpty() || airport.getAita() == null) {
            isValid = false;
        } // if city is empty or null
        else if (airport.getCity().isEmpty() || airport.getCity() == null) {
            isValid = false;
        } // if country is empty or null
        else if (airport.getCountry().isEmpty() || airport.getCountry() == null) {
            isValid = false;
        }
        return isValid;
    }
    
    
}
