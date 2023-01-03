package model;

import java.sql.SQLException;

public class CreditCardModel {
    
    private Database db; 

    public CreditCardModel() throws SQLException, ClassNotFoundException{
        db = new Database();
    }

    public void InsertCreditCard(String user_id, String codice, String ccv, int mese_scadenza, int anno_scadenza) throws SQLException{
        db.InsertCreditCard(user_id, codice, ccv, mese_scadenza, anno_scadenza);
    }

    public void editCreditCard(String user_id, String codice, String ccv, int mese_scadenza, int anno_scadenza) throws SQLException {
        db.updateCreditCard(user_id, codice, ccv, mese_scadenza, anno_scadenza);
    }
}
