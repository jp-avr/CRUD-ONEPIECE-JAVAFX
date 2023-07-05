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
import model.dao.PirataDao;
import model.entities.Pirata;

public class PirataDaoJDBC implements PirataDao {

	private Connection conn;
	
	public PirataDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Pirata findById(Integer cod_pirata) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM pirata WHERE cod_pirata = ?");
			st.setInt(1, cod_pirata);
			rs = st.executeQuery();
			if (rs.next()) {
				Pirata obj = new Pirata();
				obj.setCod_pirata(rs.getInt("cod_pirata"));
				obj.setNome(rs.getString("nome"));
                obj.setRecompensa(rs.getLong("recompensa"));
                obj.setCod_ilha(rs.getInt("cod_ilha"));
                obj.setCod_tripulacao(rs.getInt("cod_tripulacao"));
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
	public List<Pirata> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM pirata ORDER BY Nome");
			rs = st.executeQuery();

			List<Pirata> list = new ArrayList<>();

			while (rs.next()) {
				Pirata obj = new Pirata();
				obj.setCod_pirata(rs.getInt("cod_pirata"));
				obj.setNome(rs.getString("nome"));
                obj.setRecompensa(rs.getLong("recompensa"));
                obj.setCod_ilha(rs.getInt("cod_ilha"));
                obj.setCod_tripulacao(rs.getInt("cod_tripulacao"));
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
	public void insert(Pirata obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO pirata " +
				"(nome) " +
				"VALUES " +
				"(?)", 
				Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNome());

			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int cod_pirata = rs.getInt(1);
					obj.setCod_pirata(cod_pirata);
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
	public void update(Pirata obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"UPDATE pirata " +
				"SET nome = ? " +
				"WHERE cod_pirata = ?");

			st.setString(1, obj.getNome());
			st.setInt(2, obj.getCod_pirata());

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
	public void deleteById(Integer cod_pirata) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM pirata WHERE cod_pirata = ?");

			st.setInt(1, cod_pirata);

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