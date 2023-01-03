package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UserModel;

@WebServlet("/validation")
public class ValidationServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        

        try {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            boolean isAvailable;

            UserModel model = new UserModel();

            if(email == null){
                isAvailable = model.checkUsernameAvailability(username);
            }else{
                isAvailable = model.checkEmailAvailability(email);
            }
            
            response.setContentType("application/json");
            out.printf("{ \"availability\": %s}", Boolean.toString(isAvailable));
        } catch (Exception e) {
            response.setContentType("text/html");
            out.println(e.getLocalizedMessage());
        }
        

    }
    
}
