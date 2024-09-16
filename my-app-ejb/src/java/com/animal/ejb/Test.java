/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.animal.ejb;

import com.entity.Animal;
import com.jdbc.Connexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author IBRAUM😎🎶🐱‍👤
 */
public class Test {
    public static void main(String[] args) throws SQLException{    
        Connexion conn = new Connexion();
        Connection conn_req = conn.getConnexion();
        List<Animal> Animals = new LinkedList<>();
        try {
            Statement stmt = conn_req.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM animal");
            while(res.next()){
                Animal a = new Animal(res.getString(2), res.getString(3));
                Animals.add(a);
                System.out.println("ID : " + res.getInt(1) + " - NAME : " + a.getName() + " - CATEGORY : " + a.getCategory());
            }
            System.out.println(res);
        } catch (SQLException ex) {
            Logger.getLogger(AnimalEjbJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
