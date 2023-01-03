package control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UserModel;
import model.beans.UserBean;

@WebServlet("/restore-password")
public class RestorePassword extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try {
            UserBean bean = (UserBean) request.getSession().getAttribute("user");
            UserModel model = new UserModel();
            model.RestorePassword(bean.getUsername(), request.getParameter("newpassword"));
            response.sendRedirect("profile.jsp");

        } catch (Exception e) {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println(e.getLocalizedMessage());

        }
    }
}
