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
}