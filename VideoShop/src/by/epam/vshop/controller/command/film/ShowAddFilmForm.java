package by.epam.vshop.controller.command.film;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.vshop.controller.JSPPageName;
import by.epam.vshop.controller.command.Command;

public class ShowAddFilmForm implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADD_FILM);
		dispatcher.forward(request, response);
	}

}
