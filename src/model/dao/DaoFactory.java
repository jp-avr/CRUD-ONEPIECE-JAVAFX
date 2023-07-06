package model.dao;

import db.DB;
import model.dao.impl.PirataDaoJDBC;
import model.dao.impl.TripulacaoDaoJDBC;
import model.dao.impl.AkumaNoMiDaoJDBC;
import model.dao.impl.ArcoDaoJDBC;
import model.dao.impl.ArmaDaoJDBC;
import model.dao.impl.ArmaPersonagemDaoJDBC;
import model.dao.impl.IlhaDaoJDBC;
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

	public static IlhaDao createIlhaDao() {
		return new IlhaDaoJDBC(DB.getConnection());
	}

	public static ArmaDao createArmaDao() {
		return new ArmaDaoJDBC(DB.getConnection());
	}

	public static ArmaPersonagemDao createArmaPersonagemDao() {
		return new ArmaPersonagemDaoJDBC(DB.getConnection());
	}

	public static ArcoDao createArcoDao() {
		return new ArcoDaoJDBC(DB.getConnection());
	}

	public static TripulacaoDao createTripulacaoDao() {
		return new TripulacaoDaoJDBC(DB.getConnection());
	}
	
}