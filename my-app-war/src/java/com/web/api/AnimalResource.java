/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.web.api;

import com.animal.ejb.AnimalEjbJDBC;
import com.entity.Animal;
import jakarta.ejb.EJB;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * REST Web Service
 *
 * @author IBRAUM😎🎶🐱‍👤
 */
@Path("/animal")
public class AnimalResource{

    @Context
    private UriInfo context;
    
    @EJB
    AnimalEjbJDBC animalejbdbc;
    
    
    private static Logger logger = Logger.getLogger(Animal.class);

    /**
     * Creates a new instance of AnimalResource
     */
    public AnimalResource() {
    }
    
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAnimal(Animal a) {
        logger.info("Vous avez ajouté un animal ----- ");
        animalejbdbc.addAnimal(a);
        return Response.status(Response.Status.CREATED).entity(a).build();
    }
    
    @GET
    @Path("/all")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAnimals (){
        List<Animal> animals = animalejbdbc.getAnimals();
        for(Animal a : animals) {
            System.out.println("NAME : " + a.getName() + " - CATEGORY : " + a.getCategory());
        }
        return Response.status(Response.Status.OK).entity(animals).build();
    }
    
    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAnimal (@PathParam("id") Long id){
        System.out.println("ID " + id);
        return Response.ok(animalejbdbc.getAnimal(id)).build();
    }
    
    @PUT
    @Path("/{id}/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAnimal (@PathParam("id") Long id, Animal a){
        a.setId(id);
        return Response.ok(animalejbdbc.updateAnimal(id, a)).build();
    }
    
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAnimal (@PathParam("id") Long id){
        Animal a_exist = animalejbdbc.deleteAnimal(id);
        if (a_exist != null) {
            return Response.ok(animalejbdbc.deleteAnimal(id)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
