package com.example.backendTp.Controllers;

import com.example.backendTp.Beans.AddResponse;
import com.example.backendTp.Beans.Ville;
import com.example.backendTp.Services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VilleController {

    @Autowired
    VilleService villeService;
    @GetMapping("/villes")
    public List<Ville> getVilles(){
        return villeService.getAllVilles();
    }
    @GetMapping("/ville/{id}")
    public ResponseEntity<Ville> getVilleById(@PathVariable(value="id") int id )
    {
        try{
            Ville ville = villeService.getVilleByID(id);
            return new ResponseEntity<Ville>(ville, HttpStatus.OK);

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

    @PostMapping("/addville")
    public Ville addVille(@RequestBody Ville ville)
    {
        return villeService.addVille(ville);
    }

    @PutMapping("/updateville/{id}")
    public ResponseEntity<Ville> updateVille(@PathVariable(value="id") int id, @RequestBody Ville ville)
    {
        try{
            Ville existVille = villeService.getVilleByID(id);
            existVille.setNom(ville.getNom());
            existVille.setPays(ville.getPays());
            existVille.setLongitude(ville.getLongitude());
            existVille.setLattitude(ville.getLattitude());
            existVille.setZones(ville.getZones());
            Ville updated_class = villeService.updateVille(existVille);
            return new ResponseEntity<Ville>(updated_class,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        }

    }

    @DeleteMapping("/deleteville/{id}")
    public AddResponse deleteVille(@PathVariable(value="id") int id)
    {
        return villeService.deleteVille(id);
    }
}
