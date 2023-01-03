package model.beans;

import java.io.Serializable;

public class UserBean implements Serializable {
    
    private String email;
    private String username;
    private String nome;  
    private String cognome;
    private String indirizzo;
    private String citta;
    private String cap; 
    private String telefono;  
    private boolean editor;
    private CreditCardBean creditCard;
    
    public void setEmail(String email){
        this.email = email; 
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setCognome(String cognome){
        this.cognome = cognome;
    }
    
    public void setIndirizzo(String indirizzo){
        this.indirizzo = indirizzo; 
    }

    public void setUserCitta(String citta){
        this.citta = citta;
    }

    public void setCap(String cap){
        this.cap = cap;
    }

    public void setTelefono(String telefono){
        this.telefono = telefono;
    }
    
    public void setEditor(boolean editor){
        this.editor = editor;
    }

    public void setCreditCard(CreditCardBean card){
        this.creditCard = card; 
    }

    public String getEmail(){
        return this.email;
    }
    public String getUsername(){
        return this.username;
    }
    
    public String getNome(){
        return this.nome;
    }

    public String getCognome(){
        return this.cognome; 
    }

    public String getIndirizzo(){
        return this.indirizzo;
    }

    public String getCap(){
        return this.cap;
    }

    public String getUserCitta(){
        return this.citta;
    }

    public String getTelefono(){
        return this.telefono;
    }

    public boolean getEditor(){
        return this.editor;
    }

    public CreditCardBean getCreditCard(){
        return this.creditCard;
    }
}
