package model.beans;

public class OrderBean {
    private TravelBean order;
    private int orderID;
    private String userID;
    private String date;
    private int quantita;
    private String indirizzo_ordine;
    private String cap_ordine;
    private String citta_ordine; 
    
    
    
    public TravelBean getOrder(){
        return this.order;
    }

    public int getOrderID(){
        return this.orderID;
    }
    public String getUserID(){
        return this.userID;
    }

    public String getDate(){
        return this.date;
    }

    public int getQuantita(){
        return this.quantita;
    }

    public String getIndirizzoOrdine(){
        return this.indirizzo_ordine;
    }

    public String getCapOrdine(){
        return this.cap_ordine;
    }

    public String getCittaOrdine(){
        return this.citta_ordine;
    }

    public void setOrder(TravelBean order){
        this.order = order;
    }

    public void setOrderID(int orderID){
        this.orderID = orderID;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setQuantita(int quantita){
        this.quantita = quantita;
    }

    public void setIndirizzoOrdine(String indirizzo_ordine){
        this.indirizzo_ordine = indirizzo_ordine;
    }

    public void setCapOrdine(String cap_ordine){
        this.cap_ordine = cap_ordine;
    }

    public void setCittaOrdine(String citta_ordine){
        this.citta_ordine = citta_ordine;
    }

    public void setUserID(String userID){
        this.userID = userID;
    }
}
