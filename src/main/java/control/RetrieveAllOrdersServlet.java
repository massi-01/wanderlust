package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.OrderModel;
import model.beans.OrderBean;

@WebServlet("/retrieve")
public class RetrieveAllOrdersServlet extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        try {
            OrderModel model = new OrderModel();
            List<OrderBean> allOrders = model.getAll();
            
            session.setAttribute("allOrders", allOrders);
            request.getRequestDispatcher("allOrders.jsp").include(request, response);
        } catch (Exception e) {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println(e.getMessage());
        }
        
    }
}
