package model;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.beans.OrderBean;
import java.util.ArrayList;
import java.util.List;

public class OrderModel {
    
    private Database db;

    public OrderModel() throws ClassNotFoundException, SQLException{
        db = new Database();
    }

    public List<OrderBean> getOrderByUsername(String username) throws SQLException, ClassNotFoundException{
        
        ResultSet set = db.execQuery("select * from acquisto where acquisto.utente_id = '"+username+"'");
        List<OrderBean> list = new ArrayList<OrderBean>();
        TravelModel travelModel = new TravelModel();
        
        while(set.next()){
            OrderBean order = new OrderBean();
            order.setOrder(travelModel.getTravelByKey(set.getString("viaggio_id")));
            order.setOrderID(set.getInt("acquisto_id"));
            order.setUserID(set.getString("utente_id"));
            order.setQuantita(set.getInt("quantita"));
            order.setIndirizzoOrdine(set.getString("indirizzo_ordine"));
            order.setCapOrdine(set.getString("cap_ordine"));
            order.setCittaOrdine(set.getString("citta_ordine"));
            order.setDate(set.getString("data_ordine"));

            list.add(order);
        }
        return list;
    }

    public List<OrderBean> getAll() throws SQLException, ClassNotFoundException{
        
        ResultSet set = db.execQuery("select * from acquisto");
        List<OrderBean> list = new ArrayList<OrderBean>();
        TravelModel travelModel = new TravelModel();
        
        while(set.next()){
            OrderBean order = new OrderBean();
            order.setOrder(travelModel.getTravelByKey(set.getString("viaggio_id")));
            order.setOrderID(set.getInt("acquisto_id"));
            order.setUserID(set.getString("utente_id"));
            order.setQuantita(set.getInt("quantita"));
            order.setIndirizzoOrdine(set.getString("indirizzo_ordine"));
            order.setCapOrdine(set.getString("cap_ordine"));
            order.setCittaOrdine(set.getString("citta_ordine"));
            order.setDate(set.getString("data_ordine"));

            list.add(order);
        }
        return list;
    }

    public OrderBean getOrderByKey(String orderCode) throws SQLException, ClassNotFoundException {

        ResultSet set = db.execQuery("select * from acquisto where acquisto_id = '"+orderCode+"'");
        TravelModel travelModel = new TravelModel(); 
        OrderBean order = new OrderBean();
        while(set.next()){
            
            order.setOrder(travelModel.getTravelByKey(set.getString("viaggio_id")));
            order.setOrderID(set.getInt("acquisto_id"));
            order.setUserID(set.getString("utente_id"));
            order.setQuantita(set.getInt("quantita"));
            order.setIndirizzoOrdine(set.getString("indirizzo_ordine"));
            order.setCapOrdine(set.getString("cap_ordine"));
            order.setCittaOrdine(set.getString("citta_ordine"));
            order.setDate(set.getString("data_ordine"));
        }
        return order;
    }

    
   
}
