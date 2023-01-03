package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.OrderModel;
import model.beans.OrderBean;

@WebServlet("/receipt")
public class ReceiptServlet extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String orderCode = request.getParameter("order-code");

        try {
            OrderModel orderModel = new OrderModel();
            OrderBean orderBean = orderModel.getOrderByKey(orderCode);
            request.getSession(false).setAttribute("order", orderBean);
            request.getRequestDispatcher("receipt.jsp").forward(request, response);
        } catch (Exception e) {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println(e.getMessage());
        }
    }
}
