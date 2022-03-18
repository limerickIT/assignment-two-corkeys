package com.SD4.Controller;

import static antlr.Utils.error;
import com.sd4.model.Brewery;
import com.sd4.model.Beer;
import com.sd4.service.BeerService;
import java.util.Collections;

import java.util.List;
import java.util.Optional;
import static org.aspectj.bridge.MessageUtil.error;
import static org.aspectj.bridge.MessageUtil.error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@Validated 
public class BeerController {

    @Autowired
    private BeerService BeerService;
    //method to load the add beer form. This method also creates a Beer object that will back the add beer form    
    
   
 @GetMapping("addBeer")

//public ModelAndView displayAddForm() {
//
//        return new ModelAndView("/addBeer", "aBeer", new Beer());
//        
//}

//method to save the beer entity to the DB    
//decide on mapping etc..
 public ModelAndView addABeer(@ModelAttribute("aBeer") Beer b, BindingResult result) {
                
//        if (result.hasErrors()) {
//          int x =Integer.parseInt("There has been a problem");
//        }
        //save the beer object to the DB
        //display success page
        return null;
        
    }
 
    
    @GetMapping("/beers")
    public List<Beer> getAll() {
        return BeerService.findAll();
        
    }
    
    @GetMapping("/beers/{id}")
    public ResponseEntity<Beer> getOne(@PathVariable long id) {
       Optional<Beer> o =  BeerService.findOne(id);
       
       if (!o.isPresent()) 
            return new ResponseEntity(HttpStatus.NOT_FOUND);
         else 
            return ResponseEntity.ok(o.get());
    }
    
    @GetMapping("/beers/count")
    public long getCount() {
        return BeerService.count();
    }
    
    @DeleteMapping("/beers/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        BeerService.deleteByID(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @PostMapping("/beers/")
    public ResponseEntity add(@RequestBody Beer a) {
        BeerService.saveBeer(a);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @PutMapping("/beers/")
    public ResponseEntity edit(@RequestBody Beer a) { //the edit method should check if the Beer object is already in the DB before attempting to save it.
        BeerService.saveBeer(a);
        return new ResponseEntity(HttpStatus.OK);
    }

}