package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartModel;
import model.CheckOutModel;
import model.beans.CartBean;
import model.beans.CreditCardBean;
import model.beans.UserBean;

@SuppressWarnings (value="unchecked")
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        HttpSession session = request.getSession(false);
        
        HashMap<String ,CartBean> cart = (HashMap<String, CartBean>) session.getAttribute("cart");
        UserBean user = (UserBean) session.getAttribute("user");
        CreditCardBean creditCard = user.getCreditCard();
        String indirizzo = request.getParameter("indirizzo-ordine");
        String cap = request.getParameter("cap");
        String citta = request.getParameter("citta");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date now = new Date();

        try {
            CheckOutModel checkOut = new CheckOutModel();

            checkOut.checkOut(user.getUsername(), creditCard.getCodice(), cart, sdf.format(now), indirizzo, cap, citta);

            CartModel cartModel = CartModel.getInstance();
            cartModel.clearCart();
            
            session.removeAttribute("cart");
            session.removeAttribute("total");
            response.sendRedirect("Cart.jsp");
        }catch(Exception e){
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println(e.getLocalizedMessage());
        }


    }
}
