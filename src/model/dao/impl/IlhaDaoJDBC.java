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
import model.dao.IlhaDao;
import model.entities.Ilha;


public class IlhaDaoJDBC implements IlhaDao {

	private Connection conn;
	
	public IlhaDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Ilha findById(Integer cod_ilha) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM ilha WHERE cod_ilha = ?");
			st.setInt(1, cod_ilha);
			rs = st.executeQuery();
			if (rs.next()) {
				Ilha obj = new Ilha();
				obj.setCod_ilha(rs.getInt("cod_ilha"));
				obj.setNome(rs.getString("nome"));
                obj.setCod_arco(rs.getInt("cod_arco"));
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
	public List<Ilha> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM ilha ORDER BY Nome");
			rs = st.executeQuery();

			List<Ilha> list = new ArrayList<>();

			while (rs.next()) {
				Ilha obj = new Ilha();
				obj.setCod_ilha(rs.getInt("cod_ilha"));
				obj.setNome(rs.getString("nome"));
                obj.setCod_arco(rs.getInt("cod_arco"));
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
	public void insert(Ilha obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO ilha " +
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
					obj.setCod_ilha(id);
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
	public void update(Ilha obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"UPDATE ilha " +
				"SET nome = ? " +
				"WHERE cod_ilha = ?");

			st.setString(1, obj.getNome());
			st.setInt(2, obj.getCod_ilha());

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
	public void deleteById(Integer cod_ilha) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM ilha WHERE cod_ilha = ?");

			st.setInt(1, cod_ilha);

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