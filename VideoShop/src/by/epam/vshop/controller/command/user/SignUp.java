package by.epam.vshop.controller.command.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.vshop.controller.ParameterName;
import by.epam.vshop.controller.command.Command;
import by.epam.vshop.service.ServiceFactory;
import by.epam.vshop.service.UserService;
import by.epam.vshop.service.exception.ServiceException;

public class SignUp implements Command {
	private final static Logger logger = Logger.getLogger(SignUp.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String login = request.getParameter(ParameterName.LOGIN);
		String firstname = request.getParameter(ParameterName.FIRST_NAME);
		String lastname = request.getParameter(ParameterName.LAST_NAME);
		String email = request.getParameter(ParameterName.EMAIL);
		String password = request.getParameter(ParameterName.PASSWORD);
		String confirmPassword = request.getParameter(ParameterName.CONFIRM_PASSWORD);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();

		try {
			userService.signUp(login, firstname, lastname, email, password, confirmPassword);
			logger.info("User " + firstname + " " + lastname + " was authorized");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			logger.error("Registration error", e);
			response.sendRedirect(request.getContextPath() + "/Controller?command=show_sign_up_form&errorMassage=singupError");
		}
	}

}
