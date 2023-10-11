package com.example.backendTp.Services;

import com.example.backendTp.Beans.AddResponse;
import com.example.backendTp.Beans.Ville;
import com.example.backendTp.Repositories.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class VilleService {
    @Autowired
    VilleRepository villerep;

    public List<Ville> getAllVilles(){
        return villerep.findAll();

    }
    public Ville getVilleByID(int id){

        return villerep.findById(id).get();

    }

    public Ville addVille(Ville ville){

        ville.setId((getMaxId()));
        villerep.save(ville);
        return ville;



    }
    public int getMaxId(){
        return (int) (villerep.findAll().size() + 1);
    }
    public Ville updateVille(Ville ville){

        villerep.save(ville);
        return ville;

    }
    public AddResponse deleteVille(int id){
        villerep.deleteById(id);
        AddResponse res =new AddResponse();
        res.setMsg("Ville Deleted!");
        res.setId(id);
        return res;
    }
}
