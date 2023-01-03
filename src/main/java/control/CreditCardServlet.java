package control;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CreditCardModel;
import model.beans.CreditCardBean;
import model.beans.UserBean;

@WebServlet("/creditcard")
public class CreditCardServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        
        HttpSession session = request.getSession(false);
        UserBean user = (UserBean)session.getAttribute("user");
        String user_id = user.getUsername();
        String codice = request.getParameter("codice");
        String ccv = request.getParameter("ccv");
        int mese_scadenza = Integer.parseInt(request.getParameter("mese"));
        int anno_scadenza = Integer.parseInt(request.getParameter("anno"));

        try {
            CreditCardModel model = new CreditCardModel();
            CreditCardBean cBean = new CreditCardBean();
            cBean.setAnno(anno_scadenza);
            cBean.setMese(mese_scadenza);
            cBean.setCodice(codice);
            cBean.setCcv(ccv);
            if(user.getCreditCard() == null){
                model.InsertCreditCard(user_id, codice, ccv, mese_scadenza, anno_scadenza);
            }else{
                model.editCreditCard(user_id, codice, ccv, mese_scadenza, anno_scadenza);
            }
            user.setCreditCard(cBean);
            session.setAttribute("user", user);
            request.getRequestDispatcher("profile.jsp").forward(request, response);

        } catch (Exception e) {
            PrintWriter out = response.getWriter();
            response.setContentType("text");
            out.println(e.getMessage());
        }
    }
}