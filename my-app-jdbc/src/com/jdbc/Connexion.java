/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author IBRAUM😎🎶🐱‍👤
 */
public class Connexion {
    private String URL = "jdbc:mysql://localhost:3307/animal_database";
    private String USERNAME = "root";
    private String PASSWORD = "";

    Connection conn;
    public Connexion () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);            
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }
    
    public Connection getConnexion() throws SQLException {
        return this.conn;
    }
    
    public void closeConnexion() throws SQLException{
        this.conn.close();
    }
}
