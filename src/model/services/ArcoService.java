package model.services;

import java.util.List;

import model.dao.ArcoDao;
import model.dao.DaoFactory;
import model.entities.Arco;

public class ArcoService {

    private ArcoDao dao = DaoFactory.createArcoDao();
    
    public List<Arco> findAll(){
        return dao.findAll();
    }

    public void saveOrUpdate(Arco obj) {
        if (obj.getCod_arco() == null) {
            dao.insert(obj);
        }else{
            dao.update(obj);
        }
    }
}