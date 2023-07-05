package model.dao;

import java.util.List;

import model.entities.Marinha;

public interface MarinhaDao {

	void insert(Marinha obj);
	void update(Marinha obj);
	void deleteById(Integer id);
	Marinha findById(Integer id);
	List<Marinha> findAll();
}