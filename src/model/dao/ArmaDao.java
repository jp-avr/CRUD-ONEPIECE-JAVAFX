package model.dao;

import java.util.List;

import model.entities.Arma;

public interface ArmaDao {

	void insert(Arma obj);
	void update(Arma obj);
	void deleteById(Integer id);
	Arma findById(Integer id);
	List<Arma> findAll();
}