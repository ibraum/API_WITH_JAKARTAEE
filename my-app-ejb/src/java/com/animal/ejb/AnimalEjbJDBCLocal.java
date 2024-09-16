/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
 */
package com.animal.ejb;

import com.entity.Animal;
import jakarta.ejb.Local;
import java.util.List;

/**
 *
 * @author IBRAUM😎🎶🐱‍👤
 */
@Local
public interface AnimalEjbJDBCLocal {
    public List<Animal> getAnimals() ;
    public Animal addAnimal(Animal a) ;
    public Animal getAnimal(Long id) ;
    public Animal updateAnimal(Long id, Animal a) ;
    public Animal deleteAnimal(Long id) ;
}
