package model.dao;

import java.util.List;

import model.entities.Arco;

public interface ArcoDao {

	void insert(Arco obj);
	void update(Arco obj);
	void deleteById(Integer id);
	Arco findById(Integer id);
	List<Arco> findAll();
}