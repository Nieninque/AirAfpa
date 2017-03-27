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
import model.Flight;

/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 */
public class FlightDAO extends DAO<Flight, Long> {

    public FlightDAO() {
        super();
    }

    /**
     *
     * Create flight
     *
     * @param flight
     * @return flight object
     */
    @Override
    public Flight create(Flight flight) {

        Flight flightCreate = new Flight();
        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "INSERT INTO flights ( "
                        + "departing_aita,\n"
                        + " arrival_aita,\n"
                        + " departing_hour,\n"
                        + " duration,\n"
                        + " price,\n"
                        + " id_pilot,\n"
                        + " id_copilot,\n"
                        + " id_staff1,\n"
                        + " id_staff2,\n"
                        + " id_staff3,\n"
                        + " planned"
                        + ") VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                // prepared requete and get return generated key
                PreparedStatement pst = this.bddmanager.getConnectionManager()
                        .prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
                // insert value in requete
                pst.setString(1, flight.getDeparting_aita());
                pst.setString(2, flight.getArrival_aita());
                pst.setString(3, flight.getDeparting_hour());
                pst.setInt(4, flight.getDuration());
                pst.setDouble(5, flight.getPrice());
                // if it's null
                if (flight.getId_pilot() == 0) {
                    pst.setNull(6, Types.BIGINT);
                } else {
                    pst.setLong(6, flight.getId_pilot());
                }
                if (Long.valueOf(flight.getId_copilot()) == 0) {
                    pst.setNull(7, Types.BIGINT);
                } else {
                    pst.setLong(7, flight.getId_copilot());
                }
                if (flight.getId_staff1() == 0) {
                    pst.setNull(8, Types.BIGINT);
                } else {
                    pst.setLong(8, flight.getId_staff1());
                }

                if (flight.getId_staff2() == 0) {
                    pst.setNull(9, Types.BIGINT);
                } else {
                    pst.setLong(9, flight.getId_staff2());
                }
                if (flight.getId_staff3() == 0) {
                    pst.setNull(10, Types.BIGINT);
                } else {
                    pst.setLong(10, flight.getId_staff3());
                }
                pst.setBoolean(11, flight.isPlanned());

                // excute insert row in table
                int insert = pst.executeUpdate();
                // if insert in table 
                if (insert != 0) {
                    // get generate key
                    ResultSet id_increment = pst.getGeneratedKeys();
                    // if it's generate key
                    if (id_increment.next()) {
                        // assign key in fligh object
                        flight.setId(id_increment.getInt(1));
                        // assign object fligh in object return
                        flightCreate = flight;
                    }

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return flightCreate;
            }

        } else {
            return flightCreate;
        }

        return flightCreate;
    }

    /**
     * Update flight
     *
     * @param flight
     * @return true is update flight, false isn't update
     */
    @Override
    public boolean update(Flight flight) {
        boolean success = false;

        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "Update flights set departing_aita = ?,\n"
                        + " arrival_aita = ?,\n"
                        + " departing_hour = ?,\n"
                        + " duration = ?,\n"
                        + " price = ?,\n"
                        + " id_pilot = ?,\n"
                        + " id_copilot = ?,\n"
                        + " id_staff1 = ?,\n"
                        + " id_staff2 = ?,\n"
                        + " id_staff3 = ?,\n"
                        + " planned = ?"
                        + " WHERE id = ?";
                // prepared requete 
                PreparedStatement pst = this.bddmanager
                        .getConnectionManager().prepareStatement(requete);
                // insert value in requete
                pst.setString(1, flight.getDeparting_aita());
                pst.setString(2, flight.getArrival_aita());
                pst.setString(3, flight.getDeparting_hour());
                pst.setInt(4, flight.getDuration());
                pst.setDouble(5, flight.getPrice());
                // if it's null 
                if (flight.getId_pilot() == 0) {
                    pst.setNull(6, Types.BIGINT);

                } else {
                    pst.setLong(6, flight.getId_pilot());
                }
                if (Long.valueOf(flight.getId_copilot()) == 0) {

                    pst.setNull(7, Types.BIGINT);
                } else {
                    pst.setLong(7, flight.getId_copilot());
                }
                if (flight.getId_staff1() == 0) {
                    pst.setNull(8, Types.BIGINT);
                } else {
                    pst.setLong(8, flight.getId_staff1());
                }

                if (flight.getId_staff2() == 0) {
                    pst.setNull(9, Types.BIGINT);
                } else {
                    pst.setLong(9, flight.getId_staff2());
                }
                if (flight.getId_staff3() == 0) {
                    pst.setNull(10, Types.BIGINT);
                } else {
                    pst.setLong(10, flight.getId_staff3());
                }
                pst.setBoolean(11, flight.isPlanned());
                pst.setLong(12, flight.getId());
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
     * delete flight
     *
     * @param primary_key
     * @return true is delete flight, false isn't delete
     */
    @Override
    public boolean delete(Long primary_key) {
        boolean success = false;

        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "DELETE FROM flights WHERE id = ?";
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
     * get all flights
     *
     * @return all flights
     */
    @Override
    public ArrayList getAll() {
        // create array list flight empty
        ArrayList<Flight> listFlight = new ArrayList<>();
        if (this.bddmanager.connect()) {

            try {
                // create statement 
                Statement st = this.bddmanager
                        .getConnectionManager()
                        .createStatement();
                // create requete 
                String requete = "SELECT flights.id,"
                        + "flights.departing_aita, "
                        + "AD.city AS departing_city,  "
                        + "AD.country AS departing_country, "
                        + "flights.arrival_aita, "
                        + "AA.city as arrival_city, "
                        + "AA.country AS arrival_country,"
                        + "flights.departing_hour, "
                        + "flights.duration,"
                        + "flights.price,"
                        + "flights.id_pilot,"
                        + "flights.id_copilot,"
                        + "flights.id_staff1,"
                        + "flights.id_staff2,"
                        + "flights.id_staff3,"
                        + "flights.planned "
                        + "FROM flights "
                        + "JOIN airports as AD on AD.aita=flights.departing_aita "
                        + "JOIN airports as AA on AA.aita = flights.arrival_aita";
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // insert all flights in array object flight
                while (rs.next()) {
                    Flight el = new Flight(
                            rs.getLong("id"),
                            rs.getString("departing_aita"),
                            rs.getString("departing_city"),
                            rs.getString("departing_country"),
                            rs.getString("arrival_aita"),
                            rs.getString("arrival_city"),
                            rs.getString("arrival_country"),
                            rs.getString("departing_hour"),
                            rs.getInt("duration"),
                            rs.getDouble("price"),
                            rs.getInt("id_pilot"),
                            rs.getInt("id_copilot"),
                            rs.getInt("id_staff1"),
                            rs.getInt("id_staff2"),
                            rs.getInt("id_staff3"),
                            rs.getBoolean("planned")
                         
                    );
                    listFlight.add(el);

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return listFlight;
            }

        } else {
            return listFlight;
        }

        return listFlight;
    }

    /**
     * find flight
     *
     * @param primary_key
     * @return flight
     */
    @Override
    public Flight find(Long primary_key) {
        // create array flight empty
        Flight flight = new Flight();
        //check if connect db
        if (this.bddmanager.connect()) {

            try {
                // create statement for find 
                Statement st = this.bddmanager.getConnectionManager()
                        .createStatement();
                // create requete add primary key
                String requete = "SELECT * FROM flights WHERE id = " + primary_key;
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // if result is full
                if (rs.next()) {
                    // insert flights in object
                    flight.setId(rs.getLong("id"));
                    flight.setDeparting_aita(rs.getString("departing_aita"));
                    flight.setArrival_aita(rs.getString("Arrival_aita"));
                    flight.setDeparting_hour(rs.getString("departing_hour"));
                    flight.setDuration(rs.getInt("duration"));
                    flight.setPrice(rs.getDouble("price"));
                    flight.setId_pilot(rs.getLong("id_pilot"));
                    flight.setId_copilot(rs.getLong("id_copilot"));
                    flight.setId_staff1(rs.getLong("id_staff1"));
                    flight.setId_staff2(rs.getLong("id_staff2"));
                    flight.setId_staff3(rs.getLong("id_staff3"));
                    flight.setPlanned(rs.getBoolean("planned"));
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return flight;
            }

        } else {
            return flight;
        }

        return flight;
    }

    /**
     * It checks if the object fligh is filled or empty
     *
     * @param flight
     * @return false is empty and true is full
     */
    public boolean isValid(Flight flight) {
        boolean isValid = true;

        // if id is empty
        if (flight.getId() == 0) {
            isValid = false;
        }

        return isValid;
    }

    public boolean isBooked(Flight flight) {

        BookingDAO bookingInstance = new BookingDAO();

        return !bookingInstance.find(flight).isEmpty();
    }
    
    public Flight.InOut find(Flight flight) {
        // create array booking empty
        String[] data = new String[4];
        int i = 0;
                    
        //check if connect db
        if (this.bddmanager.connect()) {

            try {
                // create statement for find 
                Statement st = this.bddmanager.getConnectionManager()
                        .createStatement();
                // create requete add primary key
                String requete = "SELECT airports.city,airports.pays FROM flights JOIN airports ON flights.departing_aita = airports.aita OR flights.arrival_aita = airports.aita WHERE flights.id= " + flight.getId();
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // if result is full
                while (rs.next()) {
                    data[i] = rs.getString("city");
                    data[i+1] = rs.getString("pays");
                    i += 2;
                }
                flight.setDetailsAirport(data[0],data[1],data[2],data[3]);
            } catch (SQLException ex) {
                ex.printStackTrace();
                return flight.getDetailsAirport();
            }

        } else {
            return flight.getDetailsAirport();
        }

        return flight.getDetailsAirport();
    }
    


   }
