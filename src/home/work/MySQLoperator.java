/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.work;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Глеб
 */
public class MySQLoperator implements CommandSQL {

    public Scanner scn = new Scanner(System.in);
    public String hostname;
    public int hostport;
    public String database;
    public String username;
    public String password;
    public Statement stmt;
    public ResultSet rs;

    @Override
    public void connect() {
        loadDriver();
        String connectionString = String.format("jdbc:mysql://%s:%d/%s?useSSL=true", hostname, hostport, database);

        try (Connection conn = DriverManager.getConnection(connectionString, username, password)) {

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SHOW TABLES");

            System.out.println("+----------------------+");
            System.out.println("| - TABLES ----------- |");
            System.out.println("+----------------------+");

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void writeDB() {
       
        int i = 1;

        try {
            while (rs.next()) {
                String tableName = rs.getString(1); // first column - 1, second - 2

                System.out.printf("| %02d. %-16s |\n", i++, tableName);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLoperator.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("+----------------------+");

    }

    @Override
    public void loadDriver() {
        try {
            System.out.println("The driver successfuly downloded");
            Class.forName(DRIVER_PATH);
        } catch (ClassNotFoundException ex) {
            System.err.println("\"The driver didnt downloded\"");

        }
    }

    @Override
    public void enterConnectionData() {
        System.out.println("Enter hostname");
        hostname = scn.nextLine();
        System.out.println("Enter hostport ");
        hostport = Integer.parseInt(scn.nextLine());
        System.out.println("Enter database");
        database = scn.nextLine();
        System.out.println("Enter username");
        username = scn.nextLine();
        System.out.println("Enter password");
        password = scn.nextLine();

    }

    public void start() {

        enterConnectionData();

        connect();

        writeDB();
    }

}
