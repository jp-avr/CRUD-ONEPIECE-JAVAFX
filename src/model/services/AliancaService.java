package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.AliancaDao;
import model.entities.Alianca;

public class AliancaService {

    private AliancaDao dao = DaoFactory.createAliancaDao();
    
    public List<Alianca> findAll(){
        return dao.findAll();
    }

    public void saveOrUpdate(Alianca obj) {
        if (obj.getCod_alianca() == null) {
            dao.insert(obj);
        }else{
            dao.update(obj);
        }
    }

    public void remove(Alianca obj) {
        dao.deleteById(obj.getCod_alianca());
    }
}