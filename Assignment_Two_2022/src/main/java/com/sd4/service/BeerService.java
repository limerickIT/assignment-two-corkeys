/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd4.service;

/**
 *
 * @beer Jordan
 */
import com.sd4.model.Beer;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sd4.repository.BeerRepository;
import com.sd4.repository.BreweryRepository;



@Service
public class BeerService {

    @Autowired
    private BeerRepository beerRepo;

    public Optional<Beer> findOne(Long id) {
        return beerRepo.findById(id);
    }

    public List<Beer> findAll() {
        return (List<Beer>) beerRepo.findAll();
    }

    public long count() {
        return beerRepo.count();
    }

    public void deleteByID(long beerID) {
        beerRepo.deleteById(beerID);
    }

    public void saveBeer(Beer a) {
        beerRepo.save(a);
    }  

//    public void mapBeer(Beer a) {
//        beerRepo.ShowMap(a);
//    }
//
//    public void drillBeer(Beer a) {
//        beerRepo.drillDown(a);
//    }
//
//    public void zipBeer(Beer a) {
//        beerRepo.zipFolder(a);
//    }
//
//    public void qrBeer(Beer a) {
//        beerRepo.getQR(a);
//    }
//
//    public void pdfBeer(Beer a) {
//        beerRepo.pdfBeer(a);
//    }
    
}//end class