package model;

import java.io.InputStream;
import java.sql.*;

public class Database {

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/tsw";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "dado1000";
	
	private Connection con; 
	private final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	
	public Database() throws ClassNotFoundException, SQLException{
		
		Class.forName(DRIVER_CLASS); //load driver
		this.con = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
	}
	
	public ResultSet execQuery(String query) throws SQLException {
		
		ResultSet set = null; 
		
		Statement stmt = this.con.createStatement();
		set = stmt.executeQuery(query);
		return set;
	}

	public void InsertQuery(String query) throws SQLException {
		try {
			Statement stmt = this.con.createStatement();
			stmt.executeUpdate(query);	
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	
	}

	public void insertImage(String codice, String nome, float prezzo, int giorni, String citta, String stato, String descrizione, InputStream foto) throws SQLException{
		
		PreparedStatement stmt = con.prepareStatement("Insert into viaggio values(?,?,?,?,?,?,?,?)");
		stmt.setString(1, codice);
		stmt.setString(2, nome);
		stmt.setFloat(3, prezzo);
		stmt.setInt(4, giorni);
		stmt.setString(5,citta);
		stmt.setString(6, stato);
		stmt.setString(7, descrizione);
		if (foto != null) {
			// fetches input stream of the upload file for the blob column
			stmt.setBlob(8,foto);
		}

		stmt.executeUpdate();
		
	}

	public void updateWithImage(String codice, String nome, float prezzo, int giorni, String citta, String stato, String descrizione, InputStream foto) throws SQLException{
		
		PreparedStatement stmt = con.prepareStatement("update viaggio set codice = ?, nome = ?, prezzo = ?, giorni = ?, citta = ?, stato = ?, descrizione = ?, foto = ? where codice = ?");
		stmt.setString(1, codice);
		stmt.setString(2, nome);
		stmt.setFloat(3, prezzo);
		stmt.setInt(4, giorni);
		stmt.setString(5,citta);
		stmt.setString(6, stato);
		stmt.setString(7, descrizione);
		if (foto != null) {
			// fetches input stream of the upload file for the blob column
			stmt.setBlob(8,foto);
		}
		stmt.setString(9, codice);

		stmt.executeUpdate();
		
	}

	public void deleteFromViaggio(String codice) throws SQLException{
		PreparedStatement stmt = con.prepareStatement("delete from viaggio where codice = ?");
		stmt.setString(1, codice);
		stmt.executeUpdate();
	}

	public void registerUserQuery(String email, String username, String password, String firstname, String lastname, String address, String city, String postalcode, String phone) throws SQLException{
		PreparedStatement stmt = con.prepareStatement("insert into users values(?, ?, ?, ?, ?, ? ,? , ?, ?, ?)");
		boolean flagEditor = false; 
		stmt.setString(1, email);
		stmt.setString(2, username);
		stmt.setString(3, password);
		stmt.setString(4, firstname);
		stmt.setString(5, lastname);
		stmt.setString(6, address);
		stmt.setString(7, city);
		stmt.setString(8, postalcode);
		stmt.setString(9, phone);
		stmt.setBoolean(10, flagEditor);
		stmt.executeUpdate();
		
	}

	public void dbInsertCreditCard (String user_id, String codice, String ccv, java.util.Date date) throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement("insert into pagamento values(?, ?, ?, ?)");
		stmt.setString(1, user_id);
		stmt.setString(2, codice);
		stmt.setString(3, ccv);
		stmt.setDate(4, (Date) date);
		stmt.executeUpdate();
	}

    public void restorePassword(String username, String password) throws SQLException {
		PreparedStatement stmt = this.con.prepareStatement("update users set pwd = ? where username = ?");
		stmt.setString(1, password);
		stmt.setString(2, username);
		stmt.executeUpdate();
    }

	public void InsertCreditCard(String user_id, String codice, String ccv, int mese_scadenza, int anno_scadenza) throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement("insert into pagamento values(?,?,?,?,?)");
		stmt.setString(1, user_id);
		stmt.setString(2, codice);
		stmt.setString(3, ccv);
		stmt.setInt(4, mese_scadenza);
		stmt.setInt(5, anno_scadenza);

		stmt.executeUpdate();
	}

    public void updateCreditCard(String user_id, String codice, String ccv, int mese_scadenza, int anno_scadenza) throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement("update pagamento set codice = ?, ccv = ?, mese_scadenza = ?, anno_scadenza = ? where utente_id = ?");
		stmt.setString(1, codice);
		stmt.setString(2, ccv);
		stmt.setInt(3, mese_scadenza);
		stmt.setInt(4,anno_scadenza);
		stmt.setString(5, user_id);

		stmt.executeUpdate();
    }
}
