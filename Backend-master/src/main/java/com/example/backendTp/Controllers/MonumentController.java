package com.example.backendTp.Controllers;

import com.example.backendTp.Beans.AddResponse;
import com.example.backendTp.Beans.Monument;
import com.example.backendTp.Services.MonumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
public class MonumentController {

    @Autowired
    MonumentService monumentService;
    @GetMapping("/Monument")
    public List<Monument> getMonument(){
        return monumentService.getAllMonument();
    }
    @GetMapping("/Monument/{id}")
    public ResponseEntity<Monument> getMonumentByID(@PathVariable(value="id") int id )
    {
        try{
            Monument monument = monumentService.getMonumentByID(id);
            return new ResponseEntity<Monument>(monument, HttpStatus.OK);

        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/Monument/ville")
    public ResponseEntity<Monument> getMonumentByVille(@PathVariable(value="ville") int id )
    {
        try{
            Monument monument = monumentService.getMonumentByID(id);
            return new ResponseEntity<Monument>(monument, HttpStatus.OK);

        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
//    @GetMapping("/ville/code")
//    public ResponseEntity<Ville> getDomaineByCode(@RequestParam(value="code") String code)
//    {
//        try{
//            Domaine ville= domaineService.getDomaineByCode(code);
//            return new ResponseEntity<Domaine>(ville, HttpStatus.OK);
//
//        }
//        catch(Exception e)
//        {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//    }

    @PostMapping("/addMonument")
    public Monument addMonument(@RequestBody Monument monument)
    {
        return monumentService.addMonument(monument);
    }

//    @PutMapping("/updatemonument/{id}")
//    public ResponseEntity<Monument> updateVille(@PathVariable(value="id") int id, @RequestBody Monument monument)
//    {
//        try{
//            Monument existMonument = monumentService.getMonumentByID(id);
//            existMonument.setNom(monument.getNom());
//            existMonument.setVille(monument.getVille());
//            existMonument.setLongitude(monument.getLongitude());
//            existMonument.setLattitude(monument.getLattitude());
//            Monument updated_class = monumentService.updateMonument(existMonument);
//            return new ResponseEntity<Monument>(updated_class,HttpStatus.OK);
//        }
//        catch(Exception e){
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//
//        }
//
//    }

    @DeleteMapping("/deletemonument/{id}")
    public AddResponse deleteMonument(@PathVariable(value="id") int id)
    {
        return monumentService.deleteMonument(id);
       }
}



