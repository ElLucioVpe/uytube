package servlets;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class CodigoVideoFilter
 */
//@WebFilter("/CodigoVideoFilter")
public class CodigoVideoFilter implements Filter {

	    @Override
	    public void init(FilterConfig config) throws ServletException {
	        //
	    }

	    @Override 
	    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
	        HttpServletRequest request = (HttpServletRequest) req;
	        String requestURI = request.getRequestURI();
	        String contenido = requestURI.replace(request.getContextPath()+"/", "");

	        if (contenido.length() == 11 && !contenido.contains(".")) {
	            String newURI = "video.jsp?cod="+contenido;
	            req.getRequestDispatcher(newURI).forward(req, res);
	        } else {
	            chain.doFilter(req, res);
	        }
	        
	    }

	    @Override
	    public void destroy() {
	        //
	    }
}