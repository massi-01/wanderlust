package model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import model.beans.*;

public class TravelModel {
    
    private Database db; 

    public TravelModel() throws ClassNotFoundException, SQLException{
        db = new Database();
    }

    public void insertNewTravel(String codice, String nome, float prezzo, int giorni, String citta, String stato, String descrizione, InputStream foto){
        try{
            db.insertImage(codice,nome, prezzo, giorni, citta, stato, descrizione, foto);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public TravelBean getTravelByKey(String codice) throws SQLException{
		TravelBean bean = new TravelBean();
        ResultSet set = db.execQuery("select * from viaggio where codice='"+codice+"'");
        while(set.next()){
            bean.setCodice(set.getString("codice"));
            bean.setNome(set.getString("nome"));
            bean.setPrezzo(set.getFloat("prezzo"));
            bean.setgiorni(set.getInt("giorni"));
            bean.setCitta(set.getString("citta"));
            bean.setStato(set.getString("stato"));
            bean.setDescrizione(set.getString("descrizione"));
            try{
            Blob blob = set.getBlob("foto");
                 
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                 
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);                  
                }
                 
                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                 
                 
                inputStream.close();
                outputStream.close();
                 
                bean.setBase64Foto(base64Image);
            }catch(Exception e){
                System.out.println(e.getLocalizedMessage());
            }

        }
        return bean;
    }

    public List<TravelBean> getAll() throws SQLException, IOException{
        
        List<TravelBean> list = new ArrayList<TravelBean>();

        ResultSet set = db.execQuery("select * from viaggio order by citta asc");
        
        while(set.next()){
            TravelBean bean = new TravelBean();
            bean.setCodice(set.getString("codice"));
            bean.setNome(set.getString("nome"));
            bean.setPrezzo(set.getFloat("prezzo"));
            bean.setgiorni(set.getInt("giorni"));
            bean.setCitta(set.getString("citta"));
            bean.setStato(set.getString("stato"));
            bean.setDescrizione(set.getString("descrizione"));
            try{
            Blob blob = set.getBlob("foto");
                 
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                 
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);                  
                }
                 
                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                 
                 
                inputStream.close();
                outputStream.close();
                 
                bean.setBase64Foto(base64Image);
            }catch(Exception e){
                System.out.println(e.getLocalizedMessage());
            }
            list.add(bean);
        }
        return list;
    }

    public void updateTravel(String codice, String nome, float prezzo, int giorni, String citta, String stato, String descrizione, InputStream foto){
        try{
            db.updateWithImage(codice, nome, prezzo, giorni, citta, stato, descrizione, foto);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void deleteTravel(String codice){
        try{
            db.deleteFromViaggio(codice);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
