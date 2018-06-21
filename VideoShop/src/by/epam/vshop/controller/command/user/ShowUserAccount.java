package by.epam.vshop.controller.command.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.vshop.bean.User;
import by.epam.vshop.bean.UserRole;
import by.epam.vshop.controller.JSPPageName;
import by.epam.vshop.controller.ParameterName;
import by.epam.vshop.controller.command.Command;
import by.epam.vshop.service.ServiceFactory;
import by.epam.vshop.service.UserService;
import by.epam.vshop.service.exception.ServiceException;

public class ShowUserAccount implements Command{
	private final static Logger logger = Logger.getLogger(ShowUserAccount.class);
	private final static String SHOW_PROFILE = "show_profile";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		UserRole role = UserRole.valueOf(((String)session.getAttribute("role")).toUpperCase());
		
		String strUserId;
		if(role == UserRole.ADMIN){
			strUserId = request.getParameter(ParameterName.USER_ID);
		}else {
			strUserId = ((Integer)session.getAttribute(ParameterName.USER_ID)).toString();
		}
		
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		
		
		try {
			User user = userService.getUser(strUserId);
			int discount = userService.getDiscountValue(user.getId());
			user.setDiscount(discount);
			
			request.setAttribute("user", user);
			request.setAttribute("account_command", SHOW_PROFILE);
									
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ACCOUTN);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			logger.error("Error executing command", e);
			response.sendRedirect(request.getContextPath() + JSPPageName.INDEX_PAGE);
		}
		
		
	}

}
