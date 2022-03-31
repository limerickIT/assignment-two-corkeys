package com.sd4.Controller;

import com.sd4.model.Beer;
import com.sd4.service.BeerService;
import com.sd4.service.BreweryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
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
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

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
    @GetMapping(value = "/hateoas/getall",produces =MediaTypes.HAL_JSON_VALUE)
   public ResponseEntity<CollectionModel<EntityModel<Beer>>> getAll() {

        List<EntityModel<Beer>> beers = StreamSupport.stream(BeerService.findAll().spliterator(), false).map(beer -> EntityModel.of(beer, 
        linkTo(methodOn(BeerController.class).getOne(beer.getId())).withSelfRel(), 
        linkTo(methodOn(BeerController.class).getAll()).withRel("employees"))) 
                
               .collect(Collectors.toList()); 
               return ResponseEntity.ok( 
               CollectionModel.of(beers, 
               linkTo(methodOn(BeerController.class).getAll()).withSelfRel()));

        }    

 @GetMapping(value = "/hateoas/{id}" ,produces = {MediaTypes.HAL_JSON_VALUE})
    public ResponseEntity<EntityModel<Beer>> getOne(@PathVariable long id) {
       Optional<Beer> o =  BeerService.findOne(id);
       return o
               .map(beer -> EntityModel.of(beer, 
               linkTo(methodOn(BeerController.class).getOne(beer.getId())).withSelfRel(), 
               linkTo(methodOn(BeerController.class).getAll()).withRel("beers/"))) 
               .map(ResponseEntity::ok) 
               .orElse(ResponseEntity.notFound().build());
       
    }
    
    @GetMapping(value = "/hateoas/count", produces = MediaTypes.HAL_JSON_VALUE)
    public long getCount() {
        return BeerService.count();
    }
    
    @DeleteMapping(value = "/hateoas/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity delete(@PathVariable long id) {
        BeerService.deleteByID(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @PostMapping(value = "/hateoas/add", consumes = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity add(@RequestBody Beer a) {
        BeerService.saveBeer(a);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @PutMapping(value = "/hateoas/edit", consumes = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity edit(@RequestBody Beer a) { //the edit method should check if the Beer object is already in the DB before attempting to save it.
        BeerService.saveBeer(a);
        return new ResponseEntity(HttpStatus.OK);
    }



    

}