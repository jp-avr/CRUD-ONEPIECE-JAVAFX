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
}