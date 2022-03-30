package com.sd4.Controller;

import com.sd4.model.Beer;
import com.sd4.service.BeerService;
import com.sd4.service.BreweryService;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@Validated 
@RequestMapping("")
public class BeerController {

    @Autowired
    private BeerService BeerService;
    //method to load the add beer form. This method also creates a Beer object that will back the add beer form    
    
     @Autowired
      private BreweryService Brewerieservice;
   


//public ModelAndView displayAddForm() {
//
//        return new ModelAndView("/addBeer", "aBeer", new Beer());
//        
//}

//method to save the beer entity to the DB    
//decide on mapping etc..
// @GetMapping("addBeer")
// public ModelAndView addABeer(@ModelAttribute("aBeer") Beer b, BindingResult result) {
//                
////        if (result.hasErrors()) {
////          int x =Integer.parseInt("There has been a problem");
////        }
//        //save the beer object to the DB
//        //display success page
//        return null;
//        
//    }
// 
//    //     produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
//
//     
////    @GetMapping("/beers")
////    public List<Beer> getAll() {
////        return BeerService.findAll();
////        
////    }     
    @GetMapping(value = "/hateoas/getall",produces =MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<Beer>> getAll() {
        List<Beer> alist = BeerService.findAll();
       
        if(alist.isEmpty()){
        
        return new ResponseEntity(HttpStatus.NOT_FOUND);
        
        }else{
      // Link selfLink = linkTo(methodOn(BeerController.class).getOne(Beer)).withselfRel();    
        //a.get().add(selfLink);
        return ResponseEntity.ok(alist);
             }
        }

    @GetMapping(value = "/beers/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Beer> getOne(@PathVariable long id) {
       Optional<Beer> o =  BeerService.findOne(id);
       
       if (!o.isPresent()){ 
            return new ResponseEntity(HttpStatus.NOT_FOUND);
            }else{ 
            return ResponseEntity.ok(o.get());
       }
    }
    
    @GetMapping(value = "/beers/count", produces = {MediaType.APPLICATION_JSON_VALUE})
    public long getCount() {
        return BeerService.count();
    }
    
    @DeleteMapping(value = "/beers/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity delete(@PathVariable long id) {
        BeerService.deleteByID(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @PostMapping(value = "/beers/add", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity add(@RequestBody Beer a) {
        BeerService.saveBeer(a);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @PutMapping(value = "/beers/edit", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity edit(@RequestBody Beer a) { //the edit method should check if the Beer object is already in the DB before attempting to save it.
        BeerService.saveBeer(a);
        return new ResponseEntity(HttpStatus.OK);
    }
    

}