/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.work;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Глеб
 */
public interface CommandSQL {

    public static final String DRIVER_PATH = "com.mysql.jdbc.Driver";

    public void enterConnectionData();
    
    public Connection connect() throws SQLException;

    public void writeDB() throws SQLException;

    public void loadDriver();

}
