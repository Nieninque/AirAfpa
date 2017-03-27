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
import model.AccessSite;

/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 */
public class AccessSiteDAO extends DAO<AccessSite, Long> {

    public AccessSiteDAO() {
        super();
    }

    /**
     *
     * Create access_site
     *
     * @param access_site
     * @return access_site object
     */
    @Override
    public AccessSite create(AccessSite access_site) {

        AccessSite access_siteCreate = new AccessSite();
        
        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "INSERT INTO access_site ( "
                        + "user_id,\n"
                        + " nickname,\n"
                        + " password\n"
                        + ") VALUES (?,?,MD5(?))";
                // prepared requete and get return generated key
                PreparedStatement pst = this.bddmanager.getConnectionManager()
                        .prepareStatement(requete);
                // insert value in requete
                pst.setLong(1, access_site.getUser_id());
                pst.setString(2, access_site.getNickname());
                pst.setString(3, access_site.getPassword());
                // excute insert row in table
                int insert = pst.executeUpdate();
                // if insert in table 
                if (insert != 0) {
                        
                        // assign find access_site params primary key user_id  in object return
                        access_siteCreate = this.find(access_site.getUser_id());
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return access_siteCreate;
            }

        } else {
            return access_siteCreate;
        }

        return access_siteCreate;
    }

    /**
     * Update access_site
     *
     * @param access_site
     * @return true is update access_site, false isn't update
     */
    @Override
    public boolean update(AccessSite access_site) {
        boolean success = false;

        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "Update access_site set";
                requete += " nickname = ?";
                if(! access_site.getPassword().isEmpty()){
                 requete += ", password = MD5(?)";                   
                }                
                requete +=" WHERE user_id = ?";
                // prepared requete 
                PreparedStatement pst = this.bddmanager
                        .getConnectionManager().prepareStatement(requete);
                // insert value in requete
                pst.setString(1, access_site.getNickname());
                if(!access_site.getPassword().isEmpty()){
                pst.setString(2, access_site.getPassword());
                }
                if(! access_site.getPassword().isEmpty()){           
                    pst.setLong(3, access_site.getUser_id());
                }else{
                    pst.setLong(2, access_site.getUser_id()); 
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
     * delete access_site
     *
     * @param primary_key
     * @return true is delete access_site, false isn't delete
     */
    @Override
    public boolean delete(Long primary_key) {
        boolean success = false;

        if (this.bddmanager.connect()) {

            try {
                // create requete 
                String requete = "DELETE FROM access_site WHERE user_id = ?";
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
     * get all access_sites
     *
     * @return all access_sites
     */
    @Override
    public ArrayList getAll() {
        // create array list access_site empty
        ArrayList<AccessSite> listAccessSite = new ArrayList<>();
        if (this.bddmanager.connect()) {

            try {
                // create statement 
                Statement st = this.bddmanager
                        .getConnectionManager()
                        .createStatement();
                // create requete 
                String requete = "SELECT * FROM access_site";
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // insert all access_sites in array object access_site

                while (rs.next()) {
                    AccessSite el = new AccessSite(
                            rs.getLong("user_id"),
                            rs.getString("nickname"),
                            rs.getString("password")
                    );
                    listAccessSite.add(el);

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return listAccessSite;
            }

        } else {
            return listAccessSite;
        }

        return listAccessSite;
    }

    /**
     * find access_site
     *
     * @param primary_key
     * @return access_site
     */
    @Override
    public AccessSite find(Long primary_key) {
        // create array access_site empty
        AccessSite access_site = new AccessSite();
        //check if connect db
        if (this.bddmanager.connect()) {

            try {
                // create statement for find 
                Statement st = this.bddmanager.getConnectionManager()
                        .createStatement();
                // create requete add primary key
                String requete = "SELECT * FROM access_site WHERE user_id = " + primary_key;
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // if result is full
                if (rs.next()) {
                    // insert access_sites in object                   
                    access_site.setUser_id(rs.getLong("user_id"));
                    access_site.setNickname(rs.getString("nickname"));
                    access_site.setPassword(rs.getString("password"));
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return access_site;
            }

        } else {
            return access_site;
        }

        return access_site;
    }

    /**
     * It checks if the object access_site is filled or empty
     *
     * @param access_site
     * @return false is empty and true is full
     */
    public boolean isValid(AccessSite access_site) {
        boolean isValid = true;

        // if id is empty
        if (access_site.getUser_id()== 0) {
            isValid = false;
        }

        return isValid;
    }

}
