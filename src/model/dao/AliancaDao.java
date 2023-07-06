package model.dao;

import java.util.List;

import model.entities.Alianca;

public interface AliancaDao {

	void insert(Alianca obj);
	void update(Alianca obj);
	void deleteById(Integer id);
	Alianca findById(Integer id);
	List<Alianca> findAll();
}