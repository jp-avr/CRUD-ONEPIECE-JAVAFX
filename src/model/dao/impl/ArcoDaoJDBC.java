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
import model.dao.ArcoDao;
import model.entities.Arco;


public class ArcoDaoJDBC implements ArcoDao {

	private Connection conn;
	
	public ArcoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Arco findById(Integer cod_Arco) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM Arco WHERE cod_arco = ?");
			st.setInt(1, cod_Arco);
			rs = st.executeQuery();
			if (rs.next()) {
				Arco obj = new Arco();
				obj.setCod_arco(rs.getInt("cod_arco"));
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
	public List<Arco> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM arco ORDER BY Nome");
			rs = st.executeQuery();

			List<Arco> list = new ArrayList<>();

			while (rs.next()) {
				Arco obj = new Arco();
				obj.setCod_arco(rs.getInt("cod_arco"));
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
	public void insert(Arco obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO arco " +
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
					obj.setCod_arco(id);
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
	public void update(Arco obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"UPDATE arco " +
				"SET nome = ? " +
				"WHERE cod_arco = ?");

			st.setString(1, obj.getNome());
			st.setInt(2, obj.getCod_arco());

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
	public void deleteById(Integer cod_arco) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM Arco WHERE cod_arco = ?");

			st.setInt(1, cod_arco);

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