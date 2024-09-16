/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.animal.ejb;

import com.entity.Animal;
import jakarta.ejb.Remote;
import java.util.List;

/**
 *
 * @author IBRAUM😎🎶🐱‍👤
 */
@Remote
public interface AnimalAjbJDBCRemote {
    public List<Animal> getAnimals() ;
    public Animal addAnimal(Animal a) ;
    public Animal getAnimal(Long id) ;
    public Animal updateAnimal(Long id, Animal a) ;
    public Animal deleteAnimal(Long id) ;
}
