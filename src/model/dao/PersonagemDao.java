package model.dao;

import java.util.List;

import model.entities.Personagem;

public interface PersonagemDao {

	void insert(Personagem obj);
	void update(Personagem obj);
	void deleteById(Integer id);
	Personagem findById(Integer id);
	List<Personagem> findAll();
}