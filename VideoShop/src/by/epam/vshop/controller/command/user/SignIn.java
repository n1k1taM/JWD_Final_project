package by.epam.vshop.controller.command.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.vshop.bean.User;
import by.epam.vshop.controller.JSPPageName;
import by.epam.vshop.controller.ParameterName;
import by.epam.vshop.controller.command.Command;
import by.epam.vshop.service.ServiceFactory;
import by.epam.vshop.service.UserService;
import by.epam.vshop.service.exception.ServiceException;

public class SignIn implements Command {
	private final static Logger logger = Logger.getLogger(SignIn.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String login = request.getParameter(ParameterName.LOGIN);
		String password = request.getParameter(ParameterName.PASSWORD);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		
		System.out.println(login + password);

		try {
			User user = userService.singIn(login, password);
			
			if(!user.isStatus()){
				response.sendRedirect(request.getContextPath() + JSPPageName.SIGN_IN + "?errorMassage=blockedAccount");
				return;
			}

			HttpSession session = request.getSession();
			session.setAttribute(ParameterName.LOGIN, user.getLogin());
			session.setAttribute(ParameterName.ROLE, user.getRole().toString().toLowerCase());
			session.setAttribute(ParameterName.USER_ID, user.getId());
						
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);

			logger.info("User " + login + " successfully authorized");
		} catch (ServiceException e) {
			logger.error("Login error in account", e);
			response.sendRedirect(request.getContextPath() +  "/Controller?command=show_sign_in_form&errorMassage=singinError");
		}

	}

}
