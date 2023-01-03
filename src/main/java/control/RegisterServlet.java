package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UserModel;

import javax.servlet.http.HttpServlet;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		try {	
			String email = request.getParameter("email");
			String user = request.getParameter("username");
			String password = request.getParameter("password");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String postalcode = request.getParameter("postalcode");
			String phone = request.getParameter("phone");

            UserModel newUser = new UserModel();
            newUser.RegisterUser(email, user, password, firstname, lastname, address, city, postalcode, phone);
			response.sendRedirect("travel");
		} catch (Exception e) {
			PrintWriter out = response.getWriter();
            response.setContentType("text");
			out.println(e.getLocalizedMessage());
            e.printStackTrace();
		}
	}
	
}