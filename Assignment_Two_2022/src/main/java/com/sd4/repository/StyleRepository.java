/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd4.repository;

import com.sd4.model.Beer;
import com.sd4.model.Style;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jordan
 */
@Repository
public interface StyleRepository extends CrudRepository<Beer, Long>{

    public void save(Style a);
    
}
