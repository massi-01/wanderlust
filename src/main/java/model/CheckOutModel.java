package model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import model.beans.CartBean;

public class CheckOutModel {

    private Database db;

    public CheckOutModel() throws ClassNotFoundException, SQLException{
        db = new Database();
    }
    

    public void checkOut(String username, String carta, HashMap<String, CartBean> cart, String data, String indirizzo, String cap, String citta) throws SQLException{

        for(Map.Entry<String, CartBean> entry : cart.entrySet()) {
            
            String key = entry.getKey();
            CartBean bean = entry.getValue();

           db.InsertQuery("Insert into acquisto (utente_id, pagamento_id, viaggio_id, data_ordine, quantita, indirizzo_ordine, cap_ordine, citta_ordine)"
           +"values('"+username+"','"+carta+"','"+key+"','"+data+"',"+bean.getQuantity()+",'"+indirizzo+"','"+cap+"','"+citta+"')");
        }

    }
}
