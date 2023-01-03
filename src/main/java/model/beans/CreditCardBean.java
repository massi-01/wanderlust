package model.beans;


public class CreditCardBean {

    private String codice;
    private String ccv;
    private int mese;
    private int anno;

    public void setCodice(String codice){
        this.codice = codice;
    }

    public void setCcv(String ccv){
        this.ccv = ccv;
    }
    
    public void setMese(int mese){
        this.mese = mese; 
    }
    
    public void setAnno(int anno){
        this.anno = anno;
    }

    public String getCodice(){
        return this.codice;
    }

    public String getCcv(){
        return this.ccv; 
    }

    public int getMese(){
        return this.mese; 
    }

    public int getAnno(){
        return this.anno;
    }
   
}
