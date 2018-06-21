package by.epam.vshop.controller.command.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.vshop.controller.JSPPageName;
import by.epam.vshop.controller.command.Command;

public class ShowSignInForm implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {		
		RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.SIGN_IN);
		dispatcher.forward(request, response);
		
	}

}
