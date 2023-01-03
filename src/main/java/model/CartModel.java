package model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import model.beans.CartBean;
import model.beans.TravelBean;

public class CartModel{
    private static CartModel instance = null;
    private Map<String, CartBean> travelsInCart;
    
    private CartModel(){
        travelsInCart = new HashMap<>();
    };

    public void addToCart(String codice) throws SQLException, ClassNotFoundException{
        CartBean cartBean = travelsInCart.get(codice);
        if(cartBean != null){
            cartBean.increaseQuantity();
        }else{
            TravelModel travelModel = new TravelModel();
            TravelBean travel = travelModel.getTravelByKey(codice);
            CartBean travelToAdd = new CartBean(travel, 1);
            travelsInCart.put(codice, travelToAdd);
        }
    }

    public void deleteFromCart(String codice) throws ClassNotFoundException, SQLException{
        CartBean cartBean = travelsInCart.get(codice);
        if(cartBean != null){
            cartBean.decreaseQuantity();
            if(cartBean.getQuantity() == 0){
                travelsInCart.remove(codice);
            }
        }
    }

    public Map<String, CartBean> getCart(){
        return travelsInCart; 
    }

    public float getTotalPrice(){
        float sum = 0; 
        for (Map.Entry<String, CartBean> entry : this.travelsInCart.entrySet()) {
            sum = sum + entry.getValue().getPrice();
        }

        return sum;
    }

    public void clearCart(){
        travelsInCart.clear();
    }

    public void removeAllOccurrences(String codice) {
        travelsInCart.remove(codice);
    }
    
    public static CartModel getInstance(){
        if(instance == null){
            instance = new CartModel();
        }

        return instance;
    }

    

}
