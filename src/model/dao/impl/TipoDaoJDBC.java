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
import model.dao.TipoDao;
import model.entities.Tipo;


public class TipoDaoJDBC implements TipoDao {

	private Connection conn;
	
	public TipoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Tipo findById(Integer cod_tipo) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM Tipo WHERE cod_tipo = ?");
			st.setInt(1, cod_tipo);
			rs = st.executeQuery();
			if (rs.next()) {
				Tipo obj = new Tipo();
				obj.setCod_tipo(rs.getInt("cod_tipo"));
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
	public List<Tipo> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM Tipo ORDER BY Nome");
			rs = st.executeQuery();

			List<Tipo> list = new ArrayList<>();

			while (rs.next()) {
				Tipo obj = new Tipo();
				obj.setCod_tipo(rs.getInt("cod_tipo"));
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
	public void insert(Tipo obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO tipo " +
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
					obj.setCod_tipo(id);
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
	public void update(Tipo obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"UPDATE Tipo " +
				"SET nome = ? " +
				"WHERE cod_tipo = ?");

			st.setString(1, obj.getNome());
			st.setInt(2, obj.getCod_tipo());

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
	public void deleteById(Integer cod_tipo) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM Tipo WHERE cod_tipo = ?");

			st.setInt(1, cod_tipo);

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