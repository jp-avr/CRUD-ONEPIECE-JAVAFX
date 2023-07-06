package model.dao;

import java.util.List;

import model.entities.ArmaPersonagem;

public interface ArmaPersonagemDao {

	void insert(ArmaPersonagem obj);
	void update(ArmaPersonagem obj);
	void deleteById(Integer id);
	ArmaPersonagem findById(Integer id);
	List<ArmaPersonagem> findAll();
}