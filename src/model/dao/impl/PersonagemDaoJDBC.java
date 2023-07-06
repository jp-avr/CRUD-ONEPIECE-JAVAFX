package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.PersonagemDao;
import model.entities.Personagem;

public class PersonagemDaoJDBC implements PersonagemDao {

	private Connection conn;
	
	public PersonagemDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Personagem findById(Integer cod_fruta) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM personagem WHERE cod_personagem = ?");
			st.setInt(1, cod_fruta);
			rs = st.executeQuery();
			if (rs.next()) {
				Personagem obj = new Personagem();
				obj.setCod_personagem(rs.getInt("cod_personagem"));
                obj.setCod_pirata(rs.getInt("cod_pirata"));
                obj.setCod_marinha(rs.getInt("cod_marinha"));
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Personagem> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM personagem ORDER BY cod_personagem");
			rs = st.executeQuery();

			List<Personagem> list = new ArrayList<>();

			while (rs.next()) {
				Personagem obj = new Personagem();
				obj.setCod_personagem(rs.getInt("cod_personagem"));
                obj.setCod_pirata(rs.getInt("cod_pirata"));
                obj.setCod_marinha(rs.getInt("cod_marinha"));
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void insert(Personagem obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO personagem " +
				"(cod_pirata,cod_marinha) " +
				"VALUES " +
				"(?, ?)", 
				Statement.RETURN_GENERATED_KEYS);

			st.setInt(1, obj.getCod_pirata());
			st.setInt(2, obj.getCod_marinha());

			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setCod_personagem(id);
				}
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Personagem obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"UPDATE personagem " +
				"SET cod_personagem = ? " +
				"WHERE cod_personagem = ?");

			st.setInt(1, obj.getCod_personagem());
			st.setInt(2, obj.getCod_personagem());

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer cod_personagem) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM personagem WHERE cod_personagem = ?");

			st.setInt(1, cod_personagem);

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}
}