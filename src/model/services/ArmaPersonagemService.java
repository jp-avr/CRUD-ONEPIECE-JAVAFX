package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.ArmaPersonagemDao;
import model.entities.ArmaPersonagem;

public class ArmaPersonagemService {

    private ArmaPersonagemDao dao = DaoFactory.createArmaPersonagemDao();
    
    public List<ArmaPersonagem> findAll(){
        return dao.findAll();
    }

    public void saveOrUpdate(ArmaPersonagem obj) {
        if (obj.getCod_armapersonagem() == null) {
            dao.insert(obj);
        }else{
            dao.update(obj);
        }
    }

    public void remove(ArmaPersonagem obj) {
        dao.deleteById(obj.getCod_armapersonagem());
    }
}