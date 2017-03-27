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
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Booking;
import model.Flight;

/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 */
public class BookingDAO extends DAO<Booking, Long> {

    public BookingDAO() {
        super();
    }

    /**
     *
     * Create booking
     *
     * @param booking
     * @return booking object
     */
    @Override
    public Booking create(Booking booking) {

        Booking bookingCreate = new Booking();
        if (this.bddmanager.connect()) {
            try {

                // create requete 
                String requete = "INSERT INTO bookings ( "
                        + "user_id,\n"
                        + " flight_id,\n"
                        + " place\n"
                        + ") VALUES (?,?,?)";
                // prepared requete and get return generated key
                PreparedStatement pst = this.bddmanager.getConnectionManager()
                        .prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
                // insert value in requete
                pst.setLong(1, booking.getUser_id());
                pst.setLong(2, booking.getFlight_id());
                pst.setInt(3, booking.getPlace());
                // excute insert row in table
                int insert = pst.executeUpdate();
                // if insert in table 
                if (insert != 0) {
                    // get generate key
                    ResultSet id_increment = pst.getGeneratedKeys();
                    // if it's generate key
                    if (id_increment.next()) {
                        // assign key in booking object
                        booking.setId(id_increment.getInt(1));
                        // assign object booking in object return
                        bookingCreate = booking;
                    }

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return bookingCreate;
            }

        } else {
            return bookingCreate;
        }

        return bookingCreate;
    }

    /**
     * Update booking
     *
     * @param booking
     * @return true is update booking, false isn't update
     */
    @Override
    public boolean update(Booking booking) {
        boolean success = false;

        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "Update bookings set"
                        + " user_id = ?,\n"
                        + " flight_id = ?,\n"
                        + " place = ?\n"
                        + " WHERE id = ?";
                // prepared requete 
                PreparedStatement pst = this.bddmanager
                        .getConnectionManager().prepareStatement(requete);
                // insert value in requete
                pst.setLong(1, booking.getUser_id());
                pst.setLong(2, booking.getFlight_id());
                pst.setInt(3, booking.getPlace());
                pst.setLong(4, booking.getId());
                // excute update row in table
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
     * delete booking
     *
     * @param primary_key
     * @return true is delete booking, false isn't delete
     */
    @Override
    public boolean delete(Long primary_key) {
        boolean success = false;

        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "DELETE FROM bookings WHERE id = ?";
                // prepared requete 
                PreparedStatement pst = this.bddmanager.getConnectionManager()
                        .prepareStatement(requete);
                // insert value in requete
                pst.setLong(1, primary_key);
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
     * get all bookings
     *
     * @return all bookings
     */
    @Override
    public ArrayList getAll() {
        // create array list booking empty
        ArrayList<Booking> listBooking = new ArrayList<>();
        if (this.bddmanager.connect()) {

            try {
                // create statement 
                Statement st = this.bddmanager
                        .getConnectionManager()
                        .createStatement();
                // create requete 
                String requete = "SELECT * FROM bookings";
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // insert all bookings in array object booking

                while (rs.next()) {
                    Booking el = new Booking(
                            rs.getLong("id"),
                            rs.getLong("user_id"),
                            rs.getLong("flight_id"),
                            rs.getInt("place")
                    );
                    listBooking.add(el);

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return listBooking;
            }

        } else {
            return listBooking;
        }

        return listBooking;
    }

    /**
     * find booking
     *
     * @param primary_key
     * @return booking
     */
    @Override
    public Booking find(Long primary_key) {
        // create array booking empty
        Booking booking = new Booking();
        //check if connect db
        if (this.bddmanager.connect()) {

            try {
                // create statement for find 
                Statement st = this.bddmanager.getConnectionManager()
                        .createStatement();
                // create requete add primary key
                String requete = "SELECT * FROM bookings WHERE id = " + primary_key;
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // if result is full
                if (rs.next()) {
                    // insert bookings in object                 
                    booking.setId(rs.getLong("id"));
                    booking.setUser_id(rs.getLong("user_id"));
                    booking.setFlight_id(rs.getLong("flight_id"));
                    booking.setPlace(rs.getInt("place"));
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return booking;
            }

        } else {
            return booking;
        }

        return booking;
    }

    /**
     * It checks if the object booking is filled or empty
     *
     * @param booking
     * @return false is empty and true is full
     */
    public boolean isValid(Booking booking) {
        boolean isValid = true;

        // if id is empty
        if (booking.getId() == 0) {
            isValid = false;
        }

        return isValid;
    }

    public ArrayList<Booking> find(Flight flight) {
        // create array booking empty
        
        ArrayList<Booking> alBooking = new ArrayList();

        //check if connect db
        if (this.bddmanager.connect()) {

            try {
                // create statement for find 
                Statement st = this.bddmanager.getConnectionManager()
                        .createStatement();
                // create requete add primary key
                String requete = "SELECT * FROM bookings WHERE id = " + flight.getId();
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // if result is full
                if (rs.next()) {
                    Booking booking = new Booking();             
                    booking.setId(rs.getLong("id"));
                    booking.setUser_id(rs.getLong("user_id"));
                    booking.setFlight_id(rs.getLong("flight_id"));
                    booking.setPlace(rs.getInt("place"));
                    
                    alBooking.add(booking);
                    
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return alBooking;
            }

        } else {
            return alBooking;
        }

        return alBooking;
    }
}
