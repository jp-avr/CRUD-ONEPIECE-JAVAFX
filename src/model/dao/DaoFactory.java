package model.dao;

import db.DB;
import model.dao.impl.PirataDaoJDBC;
import model.dao.impl.AkumaNoMiDaoJDBC;
import model.dao.impl.MarinhaDaoJDBC;
import model.dao.impl.PersonagemDaoJDBC;

public class DaoFactory {

	public static MarinhaDao createMarinhaDao() {
		return new MarinhaDaoJDBC(DB.getConnection());
	}
	
	public static PirataDao createPirataDao() {
		return new PirataDaoJDBC(DB.getConnection());
	}

	public static AkumaNoMiDao createAkumaNoMiDao() {
		return new AkumaNoMiDaoJDBC(DB.getConnection());
	}

	public static PersonagemDao createPersonagemDao() {
		return new PersonagemDaoJDBC(DB.getConnection());
	}
}