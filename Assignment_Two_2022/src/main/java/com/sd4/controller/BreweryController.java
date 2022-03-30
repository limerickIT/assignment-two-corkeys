/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd4.controller;

import com.sd4.model.Brewery;
import com.sd4.service.BreweryService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jordan
 */
@RestController
public class BreweryController {
       @Autowired
    private BreweryService BreweryService;
    //method to load the add brewery form. This method also creates a Brewery object that will back the add brewery form    
    
     @Autowired
      private BreweryService Brewerieservice;
   

  
    @GetMapping(value = "/hateoas/getallbrewery",produces =MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<Brewery>> getAll() {
        List<Brewery> alist = BreweryService.findAll();
       
        if(alist.isEmpty()){
        
        return new ResponseEntity(HttpStatus.NOT_FOUND);
        
        }else{
      // Link selfLink = linkTo(methodOn(BreweryController.class).getOne(Brewery)).withselfRel();    
        //a.get().add(selfLink);
        return ResponseEntity.ok(alist);
             }
        }

    @GetMapping(value = "/brewerys/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Brewery> getOne(@PathVariable long id) {
       Optional<Brewery> o =  BreweryService.findOne(id);
       
       if (!o.isPresent()){ 
            return new ResponseEntity(HttpStatus.NOT_FOUND);
            }else{ 
            return ResponseEntity.ok(o.get());
       }
    }
    
    @GetMapping(value = "/brewerys/count", produces = {MediaType.APPLICATION_JSON_VALUE})
    public long getCount() {
        return BreweryService.count();
    }
    
    @DeleteMapping(value = "/brewerys/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity delete(@PathVariable long id) {
        BreweryService.deleteByID(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @PostMapping(value = "/brewerys/add", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity add(@RequestBody Brewery a) {
        BreweryService.saveBrewery(a);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @PutMapping(value = "/brewerys/edit", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity edit(@RequestBody Brewery a) { //the edit method should check if the Brewery object is already in the DB before attempting to save it.
        BreweryService.saveBrewery(a);
        return new ResponseEntity(HttpStatus.OK);
    }
}
