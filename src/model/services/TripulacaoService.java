package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.TripulacaoDao;
import model.entities.Tripulacao;

public class TripulacaoService {

    private TripulacaoDao dao = DaoFactory.createTripulacaoDao();
    
    public List<Tripulacao> findAll(){
        return dao.findAll();
    }
}
