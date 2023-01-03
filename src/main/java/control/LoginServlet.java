package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.UserBean;
import model.UserModel;

import javax.servlet.http.HttpServlet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		UserBean bean;
		String destination = "travel";

		try {	
			String user = request.getParameter("username");
			String password = request.getParameter("password");
			HttpSession session = request.getSession(false);
			UserModel model = new UserModel();

			if(session!=null){
				session.invalidate();
			}

			bean = model.getUserByCredentials(user, password);
			if(bean != null){
				HttpSession newsession = request.getSession();
				newsession.setAttribute("user", bean);
				request.removeAttribute("loginError");
				request.getRequestDispatcher(destination).forward(request, response);
			}else{
				String loginError = "Username o password invalidi";
				request.setAttribute("loginError", loginError);
				request.getRequestDispatcher("login.jsp").include(request, response);
			}
			
		} catch (Exception e) {
			PrintWriter out = response.getWriter();
            response.setContentType("text");
            out.println(e.getLocalizedMessage());
		}
		
		
	}
	
}