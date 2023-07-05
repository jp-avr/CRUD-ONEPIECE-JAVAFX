package model.dao;

import java.util.List;

import model.entities.Pirata;

public interface PirataDao {

	void insert(Pirata obj);
	void update(Pirata obj);
	void deleteById(Integer id);
	Pirata findById(Integer id);
	List<Pirata> findAll();
}