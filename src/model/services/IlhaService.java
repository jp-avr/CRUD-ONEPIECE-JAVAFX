package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.IlhaDao;
import model.entities.Ilha;

public class IlhaService {

    private IlhaDao dao = DaoFactory.createIlhaDao();
    
    public List<Ilha> findAll(){
        return dao.findAll();
    }

    public void saveOrUpdate(Ilha obj) {
        if (obj.getCod_ilha() == null) {
            dao.insert(obj);
        }else{
            dao.update(obj);
        }
    }
}