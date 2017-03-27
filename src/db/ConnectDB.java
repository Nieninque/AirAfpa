/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 * @author Cedric DELHOME
 * @author Laure-Helene Soyeux
 */
public class ConnectDB {

    // Assign values ​​for connection db mysql
    private static final String DB_URL = "jdbc:mysql://localhost:3306/airafpa";
    private static final String DB_JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_USER = "formation";
    private static final String DB_PASSWORD = "afpagen";

    private Connection cn = null;

    private ConnectDB() {
        // import jdbc mysql
        try {
            Class.forName(ConnectDB.DB_JDBC_DRIVER);

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Singleton for connexion DB
     *
     * @return
     */
    public static ConnectDB getInstance() {
        return ConnexionBdDHolder.INSTANCE;
    }

    /**
     * Assign Instance class ConnectDB
     */
    private static class ConnexionBdDHolder {

        private static final ConnectDB INSTANCE = new ConnectDB();
    }

    /**
     * check connect DB
     *
     * @return
     */
    public boolean connect() {
        // connect db is null
        if (this.cn == null) {
            // connect db
            try {
                this.cn = DriverManager.getConnection(ConnectDB.DB_URL, ConnectDB.DB_USER, ConnectDB.DB_PASSWORD);
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        } else {
            // check connect db
            try {
                Statement st = this.cn.createStatement();
                String requete = "SELECT 1";
                st.executeQuery(requete);

            } catch (SQLException ex) {
                try {
                    this.cn = DriverManager.getConnection(ConnectDB.DB_URL, ConnectDB.DB_USER, ConnectDB.DB_PASSWORD);
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * connect db
     *
     * @return connect db
     */
    public Connection getConnectionManager() {
        return this.cn;
    }
}
