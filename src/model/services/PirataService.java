package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Pirata;

public class PirataService {
    
    public List<Pirata> findAll(){
        List<Pirata> list = new ArrayList<>();
        list.add(new Pirata(1, "Luffy", 100000000, 1, 1));
        list.add(new Pirata(2, "Zoro", 60000000,1,1));
        list.add(new Pirata(3, "Nami", 16000000,1,1));
        return list;
    }
}
