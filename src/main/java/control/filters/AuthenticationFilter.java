package control.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

@WebFilter(urlPatterns = {"/profile", "/checkout"}, filterName = "auth-filter")
public class AuthenticationFilter implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
    
        if(session.getAttribute("user") != null){
            chain.doFilter(request, response);
        }else{
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    
    }
}
