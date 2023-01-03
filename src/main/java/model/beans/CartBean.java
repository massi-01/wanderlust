package model.beans;

public class CartBean {
    private TravelBean travelBean;
    private int quantity = 0;

    public CartBean(TravelBean travel, int quantity){
        this.travelBean = travel;
        this.quantity = quantity; 
    }
    public void setTravelBean(TravelBean travelBean){
        this.travelBean = travelBean;
    }

    public void increaseQuantity(){
        ++quantity;
    }

    public void decreaseQuantity(){
        if(quantity > 0) --quantity;
    }

    public TravelBean getTravelBean(){
        return this.travelBean;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public float getPrice(){
        float sum = 0; 
        for(int i = 0; i < quantity; i++){
            sum = sum + this.travelBean.getPrezzo();
        }
        return sum; 
    }
}
