package model.dao;

import java.util.List;

import model.entities.Tripulacao;

public interface TripulacaoDao {

	void insert(Tripulacao obj);
	void update(Tripulacao obj);
	void deleteById(Integer id);
	Tripulacao findById(Integer id);
	List<Tripulacao> findAll();
}