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

    public void saveOrUpdate(Tripulacao obj) {
        if (obj.getCod_tripulacao() == null) {
            dao.insert(obj);
        }else{
            dao.update(obj);
        }
    }

    public void remove(Tripulacao obj) {
        dao.deleteById(obj.getCod_tripulacao());
    }
}
