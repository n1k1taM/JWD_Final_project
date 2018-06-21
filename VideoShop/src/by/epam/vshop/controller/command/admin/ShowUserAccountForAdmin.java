package by.epam.vshop.controller.command.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.vshop.bean.Discount;
import by.epam.vshop.bean.User;
import by.epam.vshop.controller.JSPPageName;
import by.epam.vshop.controller.ParameterName;
import by.epam.vshop.controller.command.Command;
import by.epam.vshop.service.ServiceFactory;
import by.epam.vshop.service.UserService;
import by.epam.vshop.service.exception.ServiceException;

public class ShowUserAccountForAdmin implements Command{
	private final static Logger logger = Logger.getLogger(ShowUserAccountForAdmin.class);
	private final static String SHOW_PROFILE = "show_profile";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
				
		String 	strUserId = request.getParameter(ParameterName.USER_ID);
		
		
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
				
		try {
			User user = userService.getUser(strUserId);
			Discount discount = userService.getDiscount(user.getId());
			List<Discount> discountList = userService.getAllDiscounts();
					
			request.setAttribute("user", user);
			request.setAttribute("account_command", SHOW_PROFILE);
			request.setAttribute("userDiscount", discount);
			request.setAttribute("discountList", discountList);
								
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ACCOUTN_FOR_ADMIN);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			logger.error("Error executing command", e);
			response.sendRedirect(request.getContextPath() + JSPPageName.INDEX_PAGE);
		}
		
	}
	
}
