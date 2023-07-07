package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

    private static Connection conn = null; //Objeto de conexão com um banco de dados do

    public static Connection getConnection() {
        if (conn == null) {
            try{
                //Properties props = loadProperties();
                //String url = props.getProperty("dburl");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/prova3DB2", "postgres", "1234");
            }catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            }catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static Properties loadProperties() { //Método para carregar as propriedades definidas no db.properties 
        try (FileInputStream fs = new FileInputStream("src/db.properties")) {
            
            Properties props = new Properties();
            props.load(fs); //Faz a leitura do arquivo propeties apontado pelo input fs
            return props;
        }catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }
    
    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            }catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
    }
}
