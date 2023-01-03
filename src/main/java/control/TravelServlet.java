package control;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.TravelModel;
import model.beans.TravelBean;


@WebServlet("/travel")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
  maxFileSize = 1024 * 1024 * 4, 
  maxRequestSize = 1024 * 1024 * 5 * 5)
public class TravelServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if(request.getParameter("action") != null){
            if(request.getParameter("action").equals("remove")){
                try {
                    TravelModel travel = new TravelModel();
                    travel.deleteTravel(request.getParameter("codice"));
                } catch (Exception e) {
                    PrintWriter out = response.getWriter();
                    response.setContentType("text/html");
                    out.println(e.getLocalizedMessage());
                }
                
            }
        }
        doPost(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        try {
            TravelModel travel = new TravelModel();
            
            if(request.getParameter("submit") != null){
                String codice = request.getParameter("codice");
                String nome = request.getParameter("nome");
                Float prezzo = Float.parseFloat(request.getParameter("prezzo"));
                int giorni = Integer.parseInt(request.getParameter("giorni"));
                String citta = request.getParameter("citta");
                String stato = request.getParameter("stato");
                String descrizione = request.getParameter("descrizione");
                InputStream foto = null; // input stream del file da caricare
            
                // recupera l'immagine della richiesta
                Part filePart = request.getPart("image");
                if (filePart != null) {
                    // recupera l'input stream dell'immagine
                    foto = filePart.getInputStream();
                }

                
                if(request.getParameter("edit") == null){
                    travel.insertNewTravel(codice, nome, prezzo, giorni, citta, stato, descrizione, foto);
                }else{
                    travel.updateTravel(codice, nome, prezzo, giorni, citta, stato, descrizione, foto);
                }
            }
            List<TravelBean> listOfTravels = travel.getAll();
            request.setAttribute("catalogo", listOfTravels);
            request.getRequestDispatcher("Dashboard.jsp").include(request, response);
            
        } catch (Exception e) {
            PrintWriter out = response.getWriter();
            response.setContentType("text");
            out.println(e.getLocalizedMessage());
        }

       
    }
}
