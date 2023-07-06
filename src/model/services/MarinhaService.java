package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.MarinhaDao;
import model.entities.Marinha;

public class MarinhaService {

    private MarinhaDao dao = DaoFactory.createMarinhaDao();
    
    public List<Marinha> findAll(){
        return dao.findAll();
    }

    public void saveOrUpdate(Marinha obj) {
        if (obj.getCod_marinha() == null) {
            dao.insert(obj);
        }else{
            dao.update(obj);
        }
    }

    public void remove(Marinha obj) {
        dao.deleteById(obj.getCod_marinha());
    }
}