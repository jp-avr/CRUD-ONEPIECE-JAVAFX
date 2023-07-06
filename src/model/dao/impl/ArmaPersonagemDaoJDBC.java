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
import model.dao.ArmaPersonagemDao;
import model.entities.ArmaPersonagem;


public class ArmaPersonagemDaoJDBC implements ArmaPersonagemDao {

	private Connection conn;
	
	public ArmaPersonagemDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public ArmaPersonagem findById(Integer cod_armapersonagem) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM armapersonagem WHERE cod_armapersonagem = ?");
			st.setInt(1, cod_armapersonagem);
			rs = st.executeQuery();
			if (rs.next()) {
				ArmaPersonagem obj = new ArmaPersonagem();
				obj.setCod_armapersonagem(rs.getInt("cod_armapersonagem"));
				obj.setCod_arma(rs.getInt("cod_arma"));
				obj.setCod_personagem(rs.getInt("cod_personagem"));
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
	public List<ArmaPersonagem> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM armapersonagem ORDER BY cod_armapersonagem");
			rs = st.executeQuery();

			List<ArmaPersonagem> list = new ArrayList<>();

			while (rs.next()) {
				ArmaPersonagem obj = new ArmaPersonagem();
				obj.setCod_armapersonagem(rs.getInt("cod_armapersonagem"));
				obj.setCod_arma(rs.getInt("cod_arma"));
				obj.setCod_personagem(rs.getInt("cod_personagem"));
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
	public void insert(ArmaPersonagem obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO armapersonagem " +
				"(cod_arma, cod_personagem) " +
				"VALUES " +
				"(?, ?)", 
				Statement.RETURN_GENERATED_KEYS);

			st.setInt(1, obj.getCod_arma());
			st.setInt(2, obj.getCod_personagem());

			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setCod_arma(id);
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
	public void update(ArmaPersonagem obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"UPDATE armapersonagem " +
				"SET cod_arma = ? " +
				"WHERE cod_armapersonagem = ?");

			
			st.setInt(1, obj.getCod_arma());
			st.setInt(2, obj.getCod_armapersonagem());

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
	public void deleteById(Integer cod_arma) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM armapersonagem WHERE cod_armapersonagem = ?");

			st.setInt(1, cod_arma);

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