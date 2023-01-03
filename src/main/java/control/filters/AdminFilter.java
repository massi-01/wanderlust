package control.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.UserBean;

@WebFilter(urlPatterns = "/retrieve", filterName = "auth-filter")
public class AdminFilter implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        UserBean user = (UserBean) session.getAttribute("user");
        if(user == null){
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        if(user.getEditor()){
            chain.doFilter(request, response);
        }else{
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
    
}
