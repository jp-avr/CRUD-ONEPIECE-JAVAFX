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
import model.dao.TripulacaoDao;
import model.entities.Tripulacao;

public class TripulacaoDaoJDBC implements TripulacaoDao {

	private Connection conn;
	
	public TripulacaoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Tripulacao findById(Integer cod_Tripulacao) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM tripulacao WHERE cod_tripulacao = ?");
			st.setInt(1, cod_Tripulacao);
			rs = st.executeQuery();
			if (rs.next()) {
				Tripulacao obj = new Tripulacao();
				obj.setCod_tripulacao(rs.getInt("cod_tripulacao"));
				obj.setNome(rs.getString("nome"));
                obj.setCod_alianca(rs.getInt("cod_alianca"));
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
	public List<Tripulacao> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM tripulacao ORDER BY Nome");
			rs = st.executeQuery();

			List<Tripulacao> list = new ArrayList<>();

			while (rs.next()) {
				Tripulacao obj = new Tripulacao();
				obj.setCod_tripulacao(rs.getInt("cod_tripulacao"));
				obj.setNome(rs.getString("nome"));
                obj.setCod_alianca(rs.getInt("cod_alianca"));
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
	public void insert(Tripulacao obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO tripulacao " +
				"(nome, cod_alianca) " +
				"VALUES " +
				"(?, ?)", 
				Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNome());
			st.setInt(2, obj.getCod_alianca());

			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int cod_tripulacao = rs.getInt(1);
					obj.setCod_tripulacao(cod_tripulacao);
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
	public void update(Tripulacao obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"UPDATE tripulacao " +
				"SET cod_alianca = ? " +
				"WHERE cod_tripulacao = ?");

			st.setInt(1, obj.getCod_alianca());
			st.setInt(2, obj.getCod_tripulacao());

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
	public void deleteById(Integer cod_tripulacao) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM tripulacao WHERE cod_tripulacao = ?");

			st.setInt(1, cod_tripulacao);

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