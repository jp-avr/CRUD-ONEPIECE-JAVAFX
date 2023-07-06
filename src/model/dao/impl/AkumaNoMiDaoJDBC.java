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
import model.dao.AkumaNoMiDao;
import model.entities.AkumaNoMi;

public class AkumaNoMiDaoJDBC implements AkumaNoMiDao {

	private Connection conn;
	
	public AkumaNoMiDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public AkumaNoMi findById(Integer cod_fruta) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM akumanomi WHERE cod_fruta = ?");
			st.setInt(1, cod_fruta);
			rs = st.executeQuery();
			if (rs.next()) {
				AkumaNoMi obj = new AkumaNoMi();
				obj.setCod_fruta(rs.getInt("cod_fruta"));
				obj.setNome(rs.getString("nome"));
                obj.setCod_tipo(rs.getInt("cod_tipo"));
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
	public List<AkumaNoMi> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM akumanomi ORDER BY Nome");
			rs = st.executeQuery();

			List<AkumaNoMi> list = new ArrayList<>();

			while (rs.next()) {
				AkumaNoMi obj = new AkumaNoMi();
				obj.setCod_fruta(rs.getInt("cod_fruta"));
				obj.setNome(rs.getString("nome"));
                obj.setCod_tipo(rs.getInt("cod_tipo"));
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
	public void insert(AkumaNoMi obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO akumanomi " +
				"(nome, cod_tipo, cod_personagem) " +
				"VALUES " +
				"(?,?,?)", 
				Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNome());
			st.setInt(2, obj.getCod_tipo());
			st.setInt(3, obj.getCod_personagem());

			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setCod_fruta(id);
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
	public void update(AkumaNoMi obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"UPDATE akumanomi " +
				"SET nome = ? " +
				"WHERE cod_fruta = ?");

			st.setString(1, obj.getNome());
			st.setInt(2, obj.getCod_fruta());

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
	public void deleteById(Integer cod_fruta) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM akumanomi WHERE cod_fruta = ?");

			st.setInt(1, cod_fruta);

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