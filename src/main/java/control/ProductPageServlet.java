package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TravelModel;
import model.beans.TravelBean;

@WebServlet("/product")
public class ProductPageServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String productCode = request.getParameter("product-code");

        if(productCode == null){
            response.setStatus(422);
            return; 
        }

        try {
            TravelModel travelModel = new TravelModel();
            TravelBean travel = travelModel.getTravelByKey(productCode);
            
            if(travel == null){
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;                
            }

            request.getSession().setAttribute("travel", travel);
            request.getRequestDispatcher("product-page.jsp").include(request, response);
        } catch (Exception e) {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println(e.getMessage());
        }
        

    }
}
