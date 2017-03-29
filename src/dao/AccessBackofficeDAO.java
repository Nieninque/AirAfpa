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
import model.AccessBackoffice;

/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 */
public class AccessBackofficeDAO extends DAO<AccessBackoffice, Long> {

    public AccessBackofficeDAO() {
        super();
    }

    /**
     *
     * Create access_backoffice
     *
     * @param access_backoffice
     * @return access_backoffice object
     */
    @Override
    public AccessBackoffice create(AccessBackoffice access_backoffice) {

        AccessBackoffice access_backofficeCreate = new AccessBackoffice();

        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "INSERT INTO access_backoffice ( "
                        + "user_id,\n"
                        + " nickname,\n"
                        + " password,\n"
                        + " isAdmin,\n"
                        + " isBlocked,\n"
                        + " hasChanged,\n"
                        + ") VALUES (?,?,MD5(?),?,?,?)";
                // prepared requete and get return generated key
                PreparedStatement pst = this.bddmanager.getConnectionManager()
                        .prepareStatement(requete);
                // insert value in requete
                pst.setLong(1, access_backoffice.getUser_id());
                pst.setString(2, access_backoffice.getNickname());
                pst.setString(3, access_backoffice.getPassword());
                pst.setBoolean(4, access_backoffice.isIsAdmin());
                pst.setBoolean(5, access_backoffice.isIsBlocked());
                pst.setBoolean(6, access_backoffice.isHasChanged());
                // excute insert row in table
                int insert = pst.executeUpdate();
                // if insert in table 
                if (insert != 0) {

                    // assign find access_backoffice params primary key user_id  in object return
                    access_backofficeCreate = this.find(access_backoffice.getUser_id());
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return access_backofficeCreate;
            }

        } else {
            return access_backofficeCreate;
        }

        return access_backofficeCreate;
    }

    /**
     * Update access_backoffice
     *
     * @param access_backoffice
     * @return true is update access_backoffice, false isn't update
     */
    @Override
    public boolean update(AccessBackoffice access_backoffice) {
        boolean success = false;

        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "Update access_backoffice set";
                requete += " nickname = ?";
                requete += ", isAdmin = ?";
                requete += ", isBlocked = ?";
                requete += ", hasChanged = ?";
                if (!access_backoffice.getPassword().isEmpty()) {
                    requete += ", password = MD5(?)";
                }
                requete += " WHERE user_id = ?";
                // prepared requete 
                PreparedStatement pst = this.bddmanager
                        .getConnectionManager().prepareStatement(requete);
                // insert value in requete
                pst.setString(1, access_backoffice.getNickname());
                pst.setBoolean(2, access_backoffice.isIsAdmin());
                pst.setBoolean(3, access_backoffice.isIsBlocked());
                pst.setBoolean(4, access_backoffice.isHasChanged());
                if (!access_backoffice.getPassword().isEmpty()) {
                    pst.setString(5, access_backoffice.getPassword());
                }
                if (!access_backoffice.getPassword().isEmpty()) {
                    pst.setLong(6, access_backoffice.getUser_id());
                } else {
                    pst.setLong(5, access_backoffice.getUser_id());
                }

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
     * delete access_backoffice
     *
     * @param primary_key
     * @return true is delete access_backoffice, false isn't delete
     */
    @Override
    public boolean delete(Long primary_key) {
        boolean success = false;

        if (this.bddmanager.connect()) {

            try {
                // create requete 
                String requete = "DELETE FROM access_backoffice WHERE user_id = ?";
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
     * get all access_backoffices
     *
     * @return all access_backoffices
     */
    @Override
    public ArrayList getAll() {
        // create array list airport empty
        ArrayList<AccessBackoffice> listAccessBackoffice = new ArrayList<>();
        if (this.bddmanager.connect()) {

            try {
                // create statement 
                Statement st = this.bddmanager
                        .getConnectionManager()
                        .createStatement();
                // create requete 
                String requete = "SELECT * FROM access_backoffice";
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // insert all airports in array object airport

                while (rs.next()) {
                    AccessBackoffice el = new AccessBackoffice(
                            rs.getLong("user_id"),
                            rs.getString("nickname"),
                            rs.getString("password"),
                            rs.getBoolean("isAdmin"),
                            rs.getBoolean("isBlocked"),
                            rs.getBoolean("hasChanged")
                    );
                    listAccessBackoffice.add(el);

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return listAccessBackoffice;
            }

        } else {
            return listAccessBackoffice;
        }

        return listAccessBackoffice;
    }

    /**
     * find access_backoffice
     *
     * @param primary_key
     * @return access_backoffice
     */
    @Override
    public AccessBackoffice find(Long primary_key) {
        // create array airport empty
        AccessBackoffice access_backoffice = new AccessBackoffice();
        //check if connect db
        if (this.bddmanager.connect()) {

            try {
                // create statement for find 
                Statement st = this.bddmanager.getConnectionManager()
                        .createStatement();
                // create requete add primary key
                String requete = "SELECT * FROM access_backoffice WHERE user_id = " + primary_key;
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // if result is full
                if (rs.next()) {
                    // insert airports in object                   
                    access_backoffice.setUser_id(rs.getLong("user_id"));
                    access_backoffice.setNickname(rs.getString("nickname"));
                    access_backoffice.setPassword(rs.getString("password"));
                    access_backoffice.setIsAdmin(rs.getBoolean("isAdmin"));
                    access_backoffice.setIsBlocked(rs.getBoolean("isBlocked"));
                    access_backoffice.setHasChanged(rs.getBoolean("hasChanged"));
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return access_backoffice;
            }

        } else {
            return access_backoffice;
        }

        return access_backoffice;
    }
    /**
     * find access_backoffice
     *
     * @param primary_key
     * @return access_backoffice
     */

    public AccessBackoffice find(String login) {
        // create array airport empty
        AccessBackoffice access_backoffice = new AccessBackoffice();
        //check if connect db
        if (this.bddmanager.connect()) {

            try {
                // create statement for find 
                Statement st = this.bddmanager.getConnectionManager()
                        .createStatement();
                // create requete add primary key
                String requete = "SELECT * FROM access_backoffice WHERE nickname LIKE '" + login+"'";
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // if result is full
                if (rs.next()) {
                    // insert airports in object                   
                    access_backoffice.setUser_id(rs.getLong("user_id"));
                    access_backoffice.setNickname(rs.getString("nickname"));
                    access_backoffice.setPassword(rs.getString("password"));
                    access_backoffice.setIsAdmin(rs.getBoolean("isAdmin"));
                    access_backoffice.setIsBlocked(rs.getBoolean("isBlocked"));
                    access_backoffice.setHasChanged(rs.getBoolean("hasChanged"));
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return access_backoffice;
            }

        } else {
            return access_backoffice;
        }

        return access_backoffice;
    }
    /**
     * It checks if the object access_backoffice is filled or empty
     *
     * @param access_backoffice
     * @return false is empty and true is full
     */
    public boolean isValid(AccessBackoffice access_backoffice) {
        boolean isValid = true;

        // if id is empty
        if (access_backoffice.getUser_id() == 0) {
            isValid = false;
        }

        return isValid;
    }

}
