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
import model.Function;

/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 */
public class FunctionDAO extends DAO<Function, Integer> {

    public FunctionDAO() {
        super();
    }

    /**
     *
     * Create function
     *
     * @param function
     * @return function object
     */
    @Override
    public Function create(Function function) {

        Function functionCreate = new Function();
        if (this.bddmanager.connect()) {
            try {

                // create requete 
                String requete = "INSERT INTO functions ( "
                        + "definition\n"
                        + ") VALUES (?)";
                // prepared requete and get return generated key
                PreparedStatement pst = this.bddmanager.getConnectionManager()
                        .prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
                // insert value in requete
                pst.setString(1, function.getDefinition());
                // excute insert row in table
                int insert = pst.executeUpdate();
                // if insert in table 
                if (insert != 0) {
                    // get generate key
                    ResultSet id_increment = pst.getGeneratedKeys();
                    // if it's generate key
                    if (id_increment.next()) {
                        // assign key in function object
                        function.setId(id_increment.getInt(1));
                        // assign object function in object return
                        functionCreate = function;
                    }

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return functionCreate;
            }

        } else {
            return functionCreate;
        }

        return functionCreate;
    }

    /**
     * Update function
     *
     * @param function
     * @return true is update function, false isn't update
     */
    @Override
    public boolean update(Function function) {
        boolean success = false;

        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "Update functions set"
                        + " definition = ?\n"
                        + " WHERE id = ?";
                // prepared requete 
                PreparedStatement pst = this.bddmanager
                        .getConnectionManager().prepareStatement(requete);
                // insert value in requete
                pst.setString(1, function.getDefinition());
                pst.setInt(2, function.getId());
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
     * delete function
     *
     * @param primary_key
     * @return true is delete function, false isn't delete
     */
    @Override
    public boolean delete(Integer primary_key) {
        boolean success = false;

        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "DELETE FROM functions WHERE id = ?";
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
     * get all functions
     *
     * @return all functions
     */
    @Override
    public ArrayList getAll() {
        // create array list function empty
        ArrayList<Function> listFunction = new ArrayList<>();
        if (this.bddmanager.connect()) {

            try {
                // create statement 
                Statement st = this.bddmanager
                        .getConnectionManager()
                        .createStatement();
                // create requete 
                String requete = "SELECT * FROM functions";
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // insert all functions in array object function

                while (rs.next()) {
                    Function el = new Function(
                            rs.getInt("id"),
                            rs.getString("definition")
                    );
                    listFunction.add(el);

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return listFunction;
            }

        } else {
            return listFunction;
        }

        return listFunction;
    }

    /**
     * find function
     *
     * @param primary_key
     * @return function
     */
    @Override
    public Function find(Integer primary_key) {
        // create array function empty
        Function function = new Function();
        //check if connect db
        if (this.bddmanager.connect()) {

            try {
                // create statement for find 
                Statement st = this.bddmanager.getConnectionManager()
                        .createStatement();
                // create requete add primary key
                String requete = "SELECT * FROM functions WHERE id = " + primary_key;
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // if result is full
                if (rs.next()) {
                    // insert functions in object                 
                    function.setId(rs.getInt("id"));
                    function.setDefinition(rs.getString("definition"));
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return function;
            }

        } else {
            return function;
        }

        return function;
    }

    /**
     * It checks if the object function is filled or empty
     *
     * @param function
     * @return false is empty and true is full
     */
    public boolean isValid(Function function) {
        boolean isValid = true;

        // if id is empty
        if (function.getId() == 0) {
            isValid = false;
        }

        return isValid;
    }

}
