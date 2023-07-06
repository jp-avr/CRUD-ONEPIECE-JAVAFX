package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.ArmaDao;
import model.entities.Arma;

public class ArmaService {

    private ArmaDao dao = DaoFactory.createArmaDao();
    
    public List<Arma> findAll(){
        return dao.findAll();
    }

    public void saveOrUpdate(Arma obj) {
        if (obj.getCod_arma() == null) {
            dao.insert(obj);
        }else{
            dao.update(obj);
        }
    }

    public void remove(Arma obj) {
        dao.deleteById(obj.getCod_arma());
    }
}