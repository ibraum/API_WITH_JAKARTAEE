/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.executequery;

import com.jdbc.Connexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author IBRAUM😎🎶🐱‍👤
 */
public class MyAppJdbc {
    public static void main (String[] args) throws SQLException {
        Connexion conn = new Connexion();
        Connection conn_req = conn.getConnexion();
        Statement stmt = conn_req.createStatement();
        ResultSet res = stmt.executeQuery("SELECT * FROM animal");
        
        while(res.next()) {
            System.out.println("ID : " + res.getInt(1) + " - NAME : " + res.getString(2) + " - CATEGORY : " + res.getString(3));
        }
    }
}
