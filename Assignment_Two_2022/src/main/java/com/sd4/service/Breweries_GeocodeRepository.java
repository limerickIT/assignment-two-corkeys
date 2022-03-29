/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd4.service;

import com.sd4.model.Beer;
import com.sd4.model.Breweries_Geocode;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jordan
 */
@Service
public class Breweries_GeocodeRepository {
    
    @Autowired
    private Breweries_GeocodeRepository breweries_GeocodeRepo;

    public Optional<Beer> findOne(Long id) {
        return breweries_GeocodeRepo.findById(id);
    }

    public List<Breweries_Geocode> findAll() {
        return null;
        //return (List<Breweries_Geocode>) breweries_GeocodeRepo.findAll();
    }

    public long count() {
        return breweries_GeocodeRepo.count();
    }

    public void deleteByID(long breweries_GeocodeID) {
        breweries_GeocodeRepo.deleteById(breweries_GeocodeID);
    }

    public void saveBreweries_Geocode(Breweries_Geocode a) {
        breweries_GeocodeRepo.save(a);
    }  

    private Optional<Beer> findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void deleteById(long breweries_GeocodeID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void save(Breweries_Geocode a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
