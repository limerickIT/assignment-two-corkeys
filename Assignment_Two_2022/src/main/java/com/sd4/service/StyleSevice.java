/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd4.service;

import com.sd4.model.Beer;
import com.sd4.model.Style;
import com.sd4.repository.StyleRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jordan
 */
public class StyleSevice {
    
    
    @Autowired
    private StyleRepository styleRepo;

    public Optional<Beer> findOne(Long id) {
        return styleRepo.findById(id);
    }

    public List<Style> findAll() {
        return null;
       // return (List<Style>) styleRepo.findAll();
    }

    public long count() {
        return styleRepo.count();
    }

    public void deleteByID(long styleID) {
        styleRepo.deleteById(styleID);
    }

    public void saveStyle(Style a) {
        styleRepo.save(a);
    }  
}
