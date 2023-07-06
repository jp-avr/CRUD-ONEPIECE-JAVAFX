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
}