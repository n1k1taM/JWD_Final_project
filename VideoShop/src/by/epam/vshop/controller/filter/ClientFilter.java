package by.epam.vshop.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ClientFilter implements Filter{

	

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
			
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {		
	}
	
	@Override
	public void destroy() {		
	}
	
}
