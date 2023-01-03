package control;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartModel;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet{
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        
        HttpSession session = request.getSession();
        Iterator<String> attributesNames = request.getAttributeNames().asIterator();
        
        while(attributesNames.hasNext()){
            session.removeAttribute(attributesNames.next());
        }
        CartModel cart = CartModel.getInstance();
        cart.clearCart();
        session.removeAttribute("userID");
        session.invalidate();
        request.getRequestDispatcher("travel").forward(request, response);
    }
}
