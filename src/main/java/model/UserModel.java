package model;
import model.beans.*;
import java.sql.*;

public class UserModel {
    private Database db;

    public UserModel() throws ClassNotFoundException, SQLException{
        db = new Database();
    }

    public UserBean getUserByCredentials(String Username, String password) throws SQLException{

        UserBean userBean;
        ResultSet set = db.execQuery("SELECT * FROM users WHERE username=\""+ Username+"\" AND pwd=\""+password+"\";");
        ResultSet set2 = db.execQuery("SELECT * from users inner join pagamento on utente_id = username where username = '"+Username+"';");
        if(!set.next()){
            return null;
        }

        userBean = new UserBean();

        String username = set.getString("username");
        String nome = set.getString("nome");
        String cognome = set.getString("cognome");
        String email = set.getString("email");
        String indirizzo = set.getString("indirizzo");
        String citta = set.getString("citta");
        String cap = set.getString("cap");
        String telefono = set.getString("telefono");
        boolean editor = set.getBoolean("editor");
        userBean.setUsername(username);
        userBean.setCognome(cognome);
        userBean.setNome(nome);
        userBean.setEditor(editor);
        userBean.setEmail(email);
        userBean.setIndirizzo(indirizzo);
        userBean.setUserCitta(citta);
        userBean.setTelefono(telefono);
        userBean.setCap(cap);
        
        if(set2.next()){
            CreditCardBean cBean = new CreditCardBean();
            cBean.setCodice(set2.getString("codice"));
            cBean.setMese(set2.getInt("mese_scadenza"));
            cBean.setAnno(set2.getInt("anno_scadenza"));
            cBean.setCcv(set2.getString("ccv"));
            userBean.setCreditCard(cBean);
        }else{
            userBean.setCreditCard(null);
        }

        return userBean;
    }

    public void RegisterUser(String email,String username, String password, String firstname, String lastname, String address, String city, String postalcode, String phone){
        
        try {
            db.registerUserQuery(email, username, password, firstname, lastname, address, city, postalcode, phone);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void RestorePassword(String username, String password){
        try{
            db.restorePassword(username, password);
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    public boolean checkUsernameAvailability(String username){
        boolean isAvailable = false;
            
        try {
            ResultSet set = db.execQuery("select * from users where username = '"+username+"';");
            isAvailable = !(set.next());
        } catch (SQLException e) {
            e.getLocalizedMessage();
        }

        return isAvailable;
    }

    public boolean checkEmailAvailability(String email){
        boolean isAvailable = false;
            
        try {
            ResultSet set = db.execQuery("select * from users where email = '"+email+"';");
            isAvailable = !(set.next());
        } catch (SQLException e) {
            e.getLocalizedMessage();
        }

        return isAvailable;
    }
}
