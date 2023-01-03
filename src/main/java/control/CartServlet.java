package control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CartModel;

@WebServlet("/cart")
public class CartServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        CartModel cart = CartModel.getInstance();
        HttpSession session = request.getSession(false);


        if(action == null){
            session.setAttribute("cart", cart.getCart());
            session.setAttribute("total", cart.getTotalPrice());
            request.getRequestDispatcher("Cart.jsp").include(request, response);
        }else if(action.equals("add-to-cart")){
            try {
                String codice = request.getParameter("product-code");
                cart.addToCart(codice);
            } catch (Exception e) {
                PrintWriter out = response.getWriter();
                response.setContentType("text");
                out.println(e.getLocalizedMessage());
            }
        }else if(action.equals("remove-from-cart")){
            try {
                String codice = request.getParameter("product-code");
                cart.deleteFromCart(codice);
                session.setAttribute("cart", cart.getCart());
                session.setAttribute("total", cart.getTotalPrice());
                request.getRequestDispatcher("Cart.jsp").include(request, response);
            } catch (Exception e) {
                PrintWriter out = response.getWriter();
                response.setContentType("text");
                out.println(e.getMessage());
            }
        }else if(action.equals("increase")){
            try{
                String codice = request.getParameter("product-code");
                cart.addToCart(codice);
                session.setAttribute("cart", cart.getCart());
                session.setAttribute("total", cart.getTotalPrice());
                request.getRequestDispatcher("Cart.jsp").include(request, response);

            }catch(Exception e){
                PrintWriter out = response.getWriter();
                response.setContentType("text");
                out.println(e.getMessage());
            }

        }else if(action.equals("remove-all-occurrences")){
            try{
                String codice = request.getParameter("product-code");
                cart.removeAllOccurrences(codice);
                session.setAttribute("cart", cart.getCart());
                session.setAttribute("total", cart.getTotalPrice());
                request.getRequestDispatcher("Cart.jsp").include(request, response);

            }catch(Exception e){
                PrintWriter out = response.getWriter();
                response.setContentType("text");
                out.println(e.getMessage());
            }
        }
    }
}