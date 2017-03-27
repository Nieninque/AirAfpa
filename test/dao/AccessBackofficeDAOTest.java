/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
 
import model.AccessBackoffice;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
 
/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 */
public class AccessBackofficeDAOTest {
 
    /**
     * Test of create method, of class AccessBackofficeDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        // create object access_backoffice    
        AccessBackoffice access_backoffice = new AccessBackoffice(6, "E00006", "user6",false,false,false);
        AccessBackofficeDAO access_backofficeDAO = new AccessBackofficeDAO();
        // find access_backoffice  create
        AccessBackoffice expResult = access_backofficeDAO.find(access_backoffice.getUser_id());
 
        // if find aita is empty or null
        if (!access_backofficeDAO.isValid(expResult)) {          
            // insert access_backoffice in table
            AccessBackoffice result = access_backofficeDAO.create(access_backoffice);
            // find access_backoffice
            expResult = access_backofficeDAO.find(access_backoffice.getUser_id());  
            // delete access_backoffice test
            access_backofficeDAO.delete(result.getUser_id());
            assertEquals(expResult, result);
         
        }
 
    }
 
    /**
     * Test of update method, of class AccessBackofficeDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        // create object access_backoffice      
        AccessBackoffice access_backofficeInsert = new AccessBackoffice(8, "U00008", "user8",false,false,false);
        // instatied access_backofficeDAO
        AccessBackofficeDAO access_backofficeDAO = new AccessBackofficeDAO();
        // find access_backoffice create
        AccessBackoffice findAccessBackoffice = access_backofficeDAO.find(access_backofficeInsert.getUser_id());
        // if find access_backoffice is empty
        if (!access_backofficeDAO.isValid(findAccessBackoffice)) {
            // Insert access_backoffice in table
            AccessBackoffice resultAccessBackoffice = access_backofficeDAO.create(access_backofficeInsert);  
            // find access_backoffice
            findAccessBackoffice = access_backofficeDAO.find(resultAccessBackoffice.getUser_id());
        }
            // modify access_backoffice
            findAccessBackoffice.setNickname("toto");
            findAccessBackoffice.setPassword("");        
        // update access_backoffice
        boolean result = access_backofficeDAO.update(findAccessBackoffice);  
        boolean expResult = true;    
        // delete access_backoffice test
        access_backofficeDAO.delete(findAccessBackoffice.getUser_id());
        assertEquals(expResult, result);
    }
 
    /**
     * Test of delete method, of class AccessBackofficeDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        AccessBackofficeDAO access_backofficeDAO = new AccessBackofficeDAO();
 
        // create object access_backoffice 
        AccessBackoffice access_backofficeInsert = new AccessBackoffice(9, "U00009", "user9",false,false,false);
        // find access_backoffice  create
        AccessBackoffice findAccessBackoffice = access_backofficeDAO.find(access_backofficeInsert.getUser_id());
   
        // if find access_backoffice is empty
        if (!access_backofficeDAO.isValid(findAccessBackoffice)) {            
            // Insert access_backoffice in table
            AccessBackoffice resultAccessBackoffice = access_backofficeDAO.create(access_backofficeInsert);  
            // find access_backoffice
            findAccessBackoffice = access_backofficeDAO.find(resultAccessBackoffice.getUser_id());
            // delete access_backoffice
            boolean result = access_backofficeDAO.delete(findAccessBackoffice.getUser_id());
            boolean expResult = true;
            assertEquals(expResult, result);
        }
    }
 
    /**
     * Test of findAll method, of class AccessBackofficeDAO.
     */
    @Test
    public void testGetdAll() {
        System.out.println("findAll");
        AccessBackofficeDAO access_backofficeDAO = new AccessBackofficeDAO();
        ArrayList<AccessBackoffice> arrayAccessBackoffice = access_backofficeDAO.getAll();
        String expResult = "";
        String result = "";
        for (int i = 0; i < arrayAccessBackoffice.size(); i++) {
            expResult += access_backofficeDAO.find(arrayAccessBackoffice.get(i).getUser_id());
            result += arrayAccessBackoffice.get(i);
        }
        assertEquals(expResult, result);
 
    }
 
    /**
     * Test of find method, of class AccessBackofficeDAO.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        AccessBackofficeDAO access_backofficeDAO = new AccessBackofficeDAO();
        // assign primary key
        long primary_key = 96;
        // assign result exemple
        String expResult = "AccessBackoffice{user_id=96, nickname=root, "
                + "password=dc76e9f0c0006e8f919e0c515c66dbba3982f785, isAdmin=1, isBlocked=0, hasChanged=0}";
 
        // find access_backoffice
        String result = access_backofficeDAO.find(primary_key).toString();
        assertEquals(expResult, result);
    }
}
