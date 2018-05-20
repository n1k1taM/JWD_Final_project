package by.epam.vshop.controller.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.vshop.controller.ParameterName;
import by.epam.vshop.controller.command.Command;
import by.epam.vshop.service.ServiceFactory;
import by.epam.vshop.service.UserService;
import by.epam.vshop.service.exception.ServiceException;

public class AddDiscountToClient implements Command{
	private final static Logger logger = Logger.getLogger(AddDiscountToClient.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String strDiscountId = request.getParameter(ParameterName.DISCOUNT_ID);
		String strClientId = request.getParameter(ParameterName.USER_ID);
		
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();

		try {
			userService.addDiscountToClient(strClientId, strDiscountId);
			response.sendRedirect(request.getContextPath() + "/Controller?command=show_user_account_for_admin&userId=" + strClientId);
		} catch (ServiceException e) {
			logger.error(e);
			response.sendRedirect(request.getContextPath() + "/Controller?command=show_active_order");
		}
	}
	
}
