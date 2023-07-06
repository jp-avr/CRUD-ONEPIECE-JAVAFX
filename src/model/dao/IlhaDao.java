package model.dao;

import java.util.List;

import model.entities.Ilha;

public interface IlhaDao {

	void insert(Ilha obj);
	void update(Ilha obj);
	void deleteById(Integer id);
	Ilha findById(Integer id);
	List<Ilha> findAll();
}