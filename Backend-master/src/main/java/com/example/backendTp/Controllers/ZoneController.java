package com.example.backendTp.Controllers;

import com.example.backendTp.Beans.AddResponse;
import com.example.backendTp.Beans.Zone;
import com.example.backendTp.Services.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ZoneController {

    @Autowired
    ZoneService zoneService;
    @GetMapping("/zones")
    public List<Zone> getZones(){
        return zoneService.getAllZones();
    }
    @GetMapping("/zone/{id}")
    public ResponseEntity<Zone> getZoneById(@PathVariable(value="id") int id )
    {
        try{
            Zone zone= zoneService.getZoneByID(id);
            return new ResponseEntity<Zone>(zone, HttpStatus.OK);

        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
//    @GetMapping("/enseignant/code")
//    public ResponseEntity<Enseignant> getEnseignantByCode(@RequestParam(value="code") String code)
//    {
//        try{
//            Enseignant enseignant= enseignantService.getEnseignantByCode(code);
//            return new ResponseEntity<Enseignant>(enseignant, HttpStatus.OK);
//
//        }
//        catch(Exception e)
//        {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//    }

    @PostMapping("/addzone")
    public Zone addZone(@RequestBody Zone zone)
    {
        return zoneService.addZone(zone);
    }

    @PutMapping("/updatezone/{id}")
    public ResponseEntity<Zone> updateZone(@PathVariable(value="id") int id, @RequestBody Zone zone)
    {
        try{
            Zone existZone = zoneService.getZoneByID(id);
            existZone.setNom(zone.getNom());
            existZone.setLongitude(zone.getLongitude());
            existZone.setLattitude(zone.getLattitude());
            existZone.setVille(zone.getVille());
            Zone updated_class = zoneService.updateZone(existZone);
            return new ResponseEntity<Zone>(updated_class,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        }

    }

    @DeleteMapping("/deletezone/{id}")
    public AddResponse deleteZone(@PathVariable(value="id") int id)
    {
        return zoneService.deleteZone(id);
    }
}
