package com.example.backendTp.Services;

import com.example.backendTp.Beans.AddResponse;
import com.example.backendTp.Beans.Zone;
import com.example.backendTp.Repositories.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class ZoneService {
    @Autowired
    ZoneRepository zoneRepository;

    public List<Zone> getAllZones(){
        return zoneRepository.findAll();

    }
    public Zone getZoneByID(int id){
        return zoneRepository.findById(id).get();
    }

    public Zone addZone(Zone zone){
        zone.setId((getMaxId()));
        zoneRepository.save(zone);
        return zone;

    }
    public int getMaxId(){
        return (int) (zoneRepository.findAll().size() + 1);
    }
    public Zone updateZone(Zone zone){
        zoneRepository.save(zone);
        return zone;

    }
    public AddResponse deleteZone(int id){
        zoneRepository.deleteById(id);
        AddResponse res =new AddResponse();
        res.setMsg("Zone Deleted!");
        res.setId(id);
        return res;

    }
}
