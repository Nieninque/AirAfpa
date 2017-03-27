/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import db.ConnectDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 */
public abstract class DAO<T,S> {
    protected ConnectDB bddmanager = null;
    public DAO(){
        this.bddmanager = ConnectDB.getInstance();
    }
    /**
     * Insert row in table
     * @param obj
     * @return if success insert then return true else false
     */
    public abstract T create(T obj);
    /**
     * Update row in table
     * @param obj
     * @return if success update then return true else false
     */
    public abstract boolean update(T obj);
    /**
     * Delete row in table
     * @param obj
     * @return if success delete then return true else false
     */
    public abstract boolean delete(S primary_key);
    /**
     * get all row in table
     * @return list collection all row 
     */
    public abstract ArrayList<T> getAll();  
    /**
     * get row 
     * @param id
     * @return collection row
     */
    public abstract T find(S primary_key);

    public ResultSet queryBuilder (String query){

        
        ResultSet rs = null;
   
        if (this.bddmanager.connect()) {
       
            try {
                     Statement st = this.bddmanager.getConnectionManager().createStatement();
         rs = st.executeQuery(query);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return rs;
        }
    
    }
    else {return rs;}
   return rs;}
    
    
}
