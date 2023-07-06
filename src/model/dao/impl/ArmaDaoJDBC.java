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
import model.dao.ArmaDao;
import model.entities.Arma;


public class ArmaDaoJDBC implements ArmaDao {

	private Connection conn;
	
	public ArmaDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Arma findById(Integer cod_arma) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM arma WHERE cod_arma = ?");
			st.setInt(1, cod_arma);
			rs = st.executeQuery();
			if (rs.next()) {
				Arma obj = new Arma();
				obj.setCod_arma(rs.getInt("cod_arma"));
				obj.setNome(rs.getString("nome"));
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
	public List<Arma> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM arma ORDER BY Nome");
			rs = st.executeQuery();

			List<Arma> list = new ArrayList<>();

			while (rs.next()) {
				Arma obj = new Arma();
				obj.setCod_arma(rs.getInt("cod_arma"));
				obj.setNome(rs.getString("nome"));
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
	public void insert(Arma obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO arma " +
				"(nome) " +
				"VALUES " +
				"(?)", 
				Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNome());

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
	public void update(Arma obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"UPDATE arma " +
				"SET nome = ? " +
				"WHERE cod_arma = ?");

			st.setString(1, obj.getNome());
			st.setInt(2, obj.getCod_arma());

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
				"DELETE FROM arma WHERE cod_arma = ?");

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