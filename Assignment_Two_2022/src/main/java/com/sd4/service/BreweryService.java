/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd4.service;

import com.sd4.model.Beer;
import com.sd4.model.Brewery;
import com.sd4.model.Brewery;
import com.sd4.repository.BreweryRepository;
import com.sd4.repository.BreweryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jordan
 */
public class BreweryService {
    
    @Autowired
    private BreweryRepository BreweryRepo;

    
    @Autowired
    private BreweryRepository breweryRepo;

    public Optional<Beer> findOne(Long id) {
        return breweryRepo.findById(id);
    }

    public List<Brewery> findAll() {
        return null;
       // return (List<Brewery>) breweryRepo.findAll();
    }

    public long count() {
        return breweryRepo.count();
    }

    public void deleteByID(long breweryID) {
        breweryRepo.deleteById(breweryID);
    }

    public void saveBrewery(Brewery a) {
        breweryRepo.save(a);
    }  
}
