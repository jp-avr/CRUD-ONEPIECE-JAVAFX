package model.dao;

import java.util.List;

import model.entities.AkumaNoMi;

public interface AkumaNoMiDao {

	void insert(AkumaNoMi obj);
	void update(AkumaNoMi obj);
	void deleteById(Integer id);
	AkumaNoMi findById(Integer id);
	List<AkumaNoMi> findAll();
}