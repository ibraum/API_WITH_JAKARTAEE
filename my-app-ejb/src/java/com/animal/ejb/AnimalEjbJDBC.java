/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.animal.ejb;

import com.entity.Animal;
import com.jdbc.Connexion;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author IBRAUM😎🎶🐱‍👤
 */
@Stateless
@LocalBean
public class AnimalEjbJDBC implements AnimalEjbJDBCLocal, AnimalAjbJDBCRemote {
    
    private static final Logger logger = Logger.getLogger(Animal.class);
    public AnimalEjbJDBC(){}

    @Override
    public List<Animal> getAnimals(){
        Connexion conn = new Connexion();
        List<Animal> animals = new LinkedList<>();
        try {
            Connection conn_req = conn.getConnexion();
            Statement stmt = conn_req.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM animal");
            while(res.next()){
                Animal a = new Animal((long) res.getInt(1), res.getString(2), res.getString(3));
                animals.add(a);
                System.out.println("ID : " + res.getInt(1) + " - NAME : " + a.getName() + " - CATEGORY : " + a.getCategory());
            }
            System.out.println(res);
        } catch (SQLException ex) {
            logger.error("Une erreur est surnenue lors de la **récuprération de tous les animaux** ... ", ex);
        }
        
        return animals;
    }



    @Override
    public Animal addAnimal(Animal a) {
        Connexion conn = new Connexion();
        try {
            Connection conn_req = conn.getConnexion();
            PreparedStatement p_stmt = conn_req.prepareStatement("INSERT INTO animal VALUES(null, ?, ?)");
            p_stmt.setString(1, a.getName());
            p_stmt.setString(2, a.getCategory());
            p_stmt.executeUpdate();
            conn.closeConnexion();
        } catch (SQLException ex) {
            logger.error("Une erreur est surnenue lors de **l'ajout d'un animal** ... ", ex);
        }
        return a;
    }

    @Override
    public Animal getAnimal(Long id) {
        Animal a = null;
        Connexion conn = new Connexion();
        try {
            a = new Animal();
            Connection con_req = conn.getConnexion();
            PreparedStatement p_stmt = con_req.prepareStatement("SELECT * FROM animal WHERE id = ?");
            p_stmt.setInt(1, id.intValue());
            ResultSet res = p_stmt.executeQuery();
            while (res.next()) {
                a.setId((long) res.getInt(1));
                a.setName(res.getString(2));
                a.setCategory(res.getString(3));
            }
            System.out.println("ID : " + a.getId() + " - NAME : " + a.getName() + " - CATEGORY : " + a.getCategory());
            conn.closeConnexion();
        } catch (SQLException ex) {
            logger.error("Une erreur est surnenue lors de la **récuprération d'un animal** ... ", ex);
        }
        return a;
    }

    @Override
    public Animal updateAnimal(Long id, Animal a) {
        Connexion conn = new Connexion();
        try {
            Connection con_req = conn.getConnexion();
            Animal a_exist = getAnimal(id);
            
            if(a_exist != null) {
                System.out.println("UPDATE ------ ID : " + a.getId() + " - NAME : " + a.getName() + " - CATEGORY : " + a.getCategory());
                PreparedStatement p_stmt = con_req.prepareStatement("UPDATE animal SET name = ?, category = ? WHERE id = ?");
                p_stmt.setString(1, a.getName());
                p_stmt.setString(2, a.getCategory());
                p_stmt.setInt(3, a.getId().intValue());
                p_stmt.executeUpdate();
                a_exist = getAnimal(id);
                return a_exist;
            }
            conn.closeConnexion();
        } catch (SQLException ex) {
            logger.error("Une erreur est surnenue lors de la **mise à jour** d'un animal ... ", ex);
        }
        return new Animal();
    }

    @Override
    public Animal deleteAnimal(Long id) {
        Connexion conn = new Connexion();
        try {
            Connection con_req = conn.getConnexion();
            Animal a_exist = getAnimal(id);
            
            if(a_exist != null) {
                PreparedStatement p_stmt = con_req.prepareStatement("DELETE FROM animal WHERE id = ?");
                p_stmt.setInt(1, id.intValue());
                p_stmt.executeUpdate();
                return a_exist;
            }
            conn.closeConnexion();
        } catch (SQLException ex) {
            logger.error("Une erreur est surnenue lors de la **suppression** d'un animal ... ", ex);
        }
        return new Animal();
    }
    
}
