package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.AkumaNoMiDao;
import model.entities.AkumaNoMi;

public class AkumaNoMiService {

    private AkumaNoMiDao dao = DaoFactory.createAkumaNoMiDao();
    
    public List<AkumaNoMi> findAll(){
        return dao.findAll();
    }

    public void saveOrUpdate(AkumaNoMi obj) {
        if (obj.getCod_fruta() == null) {
            dao.insert(obj);
        }else{
            dao.update(obj);
        }
    }
}