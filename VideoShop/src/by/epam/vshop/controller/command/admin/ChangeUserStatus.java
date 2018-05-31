package by.epam.vshop.controller.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.vshop.controller.JSPPageName;
import by.epam.vshop.controller.ParameterName;
import by.epam.vshop.controller.command.Command;
import by.epam.vshop.service.ServiceFactory;
import by.epam.vshop.service.UserService;
import by.epam.vshop.service.exception.ServiceException;

public class ChangeUserStatus implements Command{
	private static final Logger logger = Logger.getLogger(ChangeUserStatus.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String strUserStatus = request.getParameter(ParameterName.USER_STATUS);
		String strUserId = request.getParameter(ParameterName.USER_ID);
		
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		
		try {
			userService.changeUserStatus(strUserId, strUserStatus);
			response.sendRedirect(request.getContextPath() + "/Controller?command=show_user_account_for_admin&userId=" + strUserId);
		} catch (ServiceException e) {
			logger.error("Error executing command", e);
			response.sendRedirect(JSPPageName.INDEX_PAGE);
		}
		
		
	}
	
}
