package com.example.backendTp.Services;

import com.example.backendTp.Beans.AddResponse;
import com.example.backendTp.Beans.Monument;
import com.example.backendTp.Beans.Ville;
import com.example.backendTp.Repositories.MonumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Component
@Service

public class MonumentService {

    @Autowired
    MonumentRepository monumentRepository;

    public List<Monument> getAllMonument(){
        return monumentRepository.findAll();

    }
    public Monument getMonumentByID(int id){
        return monumentRepository.findById(id).get();
    }
//    public Monument getMonumentByVille(Ville ville){
//        return monumentRepository.findByVille(ville);
//    }
    public Monument addMonument(Monument monument){
//        monument.setId((getMaxId()));
        monument.setPhoto("https://eljadidascoop.com/wp-content/uploads/2021/07/chateaurouge-jdi.jpg");
        monumentRepository.save(monument);
        return monument;

    }
    public int getMaxId(){
        return (int) (monumentRepository.findAll().size() + 1);
    }
    public Monument updateMonument(Monument monument){
        monumentRepository.save(monument);
        return monument;

    }

    public AddResponse deleteMonument(int id){
        monumentRepository.deleteById(id);
        AddResponse res =new AddResponse();
        res.setMsg("Zone Deleted!");
        res.setId(id);
        return res;

}


}
