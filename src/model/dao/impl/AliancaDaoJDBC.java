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
import model.dao.AliancaDao;
import model.entities.Alianca;


public class AliancaDaoJDBC implements AliancaDao {

	private Connection conn;
	
	public AliancaDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Alianca findById(Integer cod_alianca) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM Alianca WHERE cod_alianca = ?");
			st.setInt(1, cod_alianca);
			rs = st.executeQuery();
			if (rs.next()) {
				Alianca obj = new Alianca();
				obj.setCod_alianca(rs.getInt("cod_alianca"));
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
	public List<Alianca> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM alianca ORDER BY Nome");
			rs = st.executeQuery();

			List<Alianca> list = new ArrayList<>();

			while (rs.next()) {
				Alianca obj = new Alianca();
				obj.setCod_alianca(rs.getInt("cod_alianca"));
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
	public void insert(Alianca obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO alianca " +
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
					obj.setCod_alianca(id);
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
	public void update(Alianca obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"UPDATE alianca " +
				"SET nome = ? " +
				"WHERE cod_alianca = ?");

			st.setString(1, obj.getNome());
			st.setInt(2, obj.getCod_alianca());

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
	public void deleteById(Integer cod_alianca) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM alianca WHERE cod_alianca = ?");

			st.setInt(1, cod_alianca);

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