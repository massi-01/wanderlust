package model.beans;

public class TravelBean {

    private String codice;
    private String nome; 
    private float prezzo;
    private int giorni;
    private String citta;
    private String stato; 
    private String descrizione;
    private String base64Foto; 
    

    public void setCodice(String codice){
        this.codice = codice;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setPrezzo(float prezzo){
        this.prezzo = prezzo;
    }

    public void setgiorni(int giorni){
        this.giorni = giorni;
    }

    public void setCitta(String citta){
        this.citta = citta;
    }
    
    public void setStato(String stato){
        this.stato = stato; 
    }

    public void setDescrizione(String descrizione){
        this.descrizione = descrizione;
    }
    public void setBase64Foto(String foto){
        this.base64Foto = foto;
    }
    public String getCodice(){
        return this.codice;
    }
    
    public String getNome(){
        return this.nome;
    }

    public float getPrezzo(){
        return this.prezzo;
    }

    public int getGiorni(){
        return this.giorni; 
    }

    public String getCitta(){
        return this.citta;
    }

    public String getStato(){
        return this.stato; 
    }

    public String getDescrizione(){
        return this.descrizione; 
    }

    public String getBase64Foto(){
        return this.base64Foto;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj ==  this) return true;
        if(!(obj instanceof TravelBean)) return false;

        TravelBean bean = (TravelBean) obj;

        return bean.getCodice().equals(this.codice);
    }
}
