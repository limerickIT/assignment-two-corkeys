package com.sd4.Controller;

import com.sd4.model.Beer;
import com.sd4.service.BeerService;
import com.sd4.service.BreweryService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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
      
    
     @Autowired
      private BreweryService Brewerieservice;
 
     
    @GetMapping(value = "/hateoas/getall",produces =MediaTypes.HAL_JSON_VALUE)
   public ResponseEntity<CollectionModel<EntityModel<Beer>>> getAll() {

        List<EntityModel<Beer>> beers = StreamSupport.stream(BeerService.findAll().spliterator(), false).map(beer -> EntityModel.of(beer, 
        linkTo(methodOn(BeerController.class).getOne(beer.getId())).withSelfRel(), 
        linkTo(methodOn(BeerController.class).getAll()).withRel("beer"))) 
                
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
    public ResponseEntity edit(@RequestBody Beer a) {
        
        BeerService.saveBeer(a);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @GetMapping(value = "/hateoas/zip", produces = "application/zip")
    public void zipDownload(HttpServletResponse response) throws IOException {
        
        ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
        Resource resource = new ClassPathResource("static/assets/images/");
        
        File fileToZip = resource.getFile();
        zipFile(fileToZip, fileToZip.getName(), zos);
        zos.close();
    } 

    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] nextfile = fileToZip.listFiles();
            for (File newFile : nextfile) {
            zipFile(newFile, fileName + "/" + newFile.getName(), zipOut);
            }
            return;
        }
        
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
        zipOut.write(bytes, 0, length);
        }
        
        fis.close();
    }  
  
}