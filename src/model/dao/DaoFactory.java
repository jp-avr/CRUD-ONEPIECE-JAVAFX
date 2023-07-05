package model.dao;

import db.DB;
import model.dao.impl.PirataDaoJDBC;
import model.dao.impl.MarinhaDaoJDBC;

public class DaoFactory {

	public static MarinhaDao createMarinhaDao() {
		return new MarinhaDaoJDBC(DB.getConnection());
	}
	
	public static PirataDao createPirataDao() {
		return new PirataDaoJDBC(DB.getConnection());
	}
}