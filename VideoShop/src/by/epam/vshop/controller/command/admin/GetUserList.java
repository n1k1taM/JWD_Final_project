package by.epam.vshop.controller.command.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.vshop.bean.User;
import by.epam.vshop.controller.JSPPageName;
import by.epam.vshop.controller.ParameterName;
import by.epam.vshop.controller.command.Command;
import by.epam.vshop.service.ServiceFactory;
import by.epam.vshop.service.UserService;
import by.epam.vshop.service.exception.ServiceException;

public class GetUserList implements Command {
	private final static Logger logger = Logger.getLogger(GetUserList.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		String pageNumber = request.getParameter(ParameterName.PAGE_NUMBER);

		try {
			List<User> userList = userService.getUserList(pageNumber, "10");
			request.setAttribute(ParameterName.USER_LIST, userList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.USER_LIST);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			logger.error("Error executing command", e);
		}
	}

}
