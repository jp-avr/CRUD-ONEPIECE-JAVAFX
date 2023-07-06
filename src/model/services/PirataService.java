package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.PirataDao;
import model.entities.Pirata;

public class PirataService {

    private PirataDao dao = DaoFactory.createPirataDao();
    
    public List<Pirata> findAll(){
        return dao.findAll();
    }

    public void saveOrUpdate(Pirata obj) {
        if (obj.getCod_pirata() == null) {
            dao.insert(obj);
        }else{
            dao.update(obj);
        }
    }
}
