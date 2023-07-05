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
import model.dao.MarinhaDao;
import model.entities.Marinha;

public class MarinhaDaoJDBC implements MarinhaDao {

	private Connection conn;
	
	public MarinhaDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Marinha findById(Integer cod_marinha) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM marinha WHERE cod_marinha = ?");
			st.setInt(1, cod_marinha);
			rs = st.executeQuery();
			if (rs.next()) {
				Marinha obj = new Marinha();
				obj.setCod_marinha(rs.getInt("cod_marinha"));
				obj.setName(rs.getString("nome"));
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
	public List<Marinha> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM pirata ORDER BY Name");
			rs = st.executeQuery();

			List<Marinha> list = new ArrayList<>();

			while (rs.next()) {
				Marinha obj = new Marinha();
				obj.setCod_marinha(rs.getInt("cod_marinha"));
				obj.setName(rs.getString("nome"));
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
	public void insert(Marinha obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO marinha " +
				"(nome) " +
				"VALUES " +
				"(?)", 
				Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getName());

			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setCod_marinha(id);
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
	public void update(Marinha obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"UPDATE marinha " +
				"SET nome = ? " +
				"WHERE cod_marinha = ?");

			st.setString(1, obj.getName());
			st.setInt(2, obj.getCod_marinha());

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
	public void deleteById(Integer cod_marinha) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM marinha WHERE cod_marinha = ?");

			st.setInt(1, cod_marinha);

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