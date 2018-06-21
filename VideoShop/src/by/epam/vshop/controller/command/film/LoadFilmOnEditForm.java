package by.epam.vshop.controller.command.film;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.vshop.bean.Film;
import by.epam.vshop.controller.JSPPageName;
import by.epam.vshop.controller.ParameterName;
import by.epam.vshop.controller.command.Command;
import by.epam.vshop.service.FilmService;
import by.epam.vshop.service.ServiceFactory;
import by.epam.vshop.service.exception.ServiceException;

public class LoadFilmOnEditForm implements Command{
	private final static Logger logger = Logger.getLogger(LoadFilmOnEditForm.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String filmId = request.getParameter(ParameterName.FILM_ID);
		ServiceFactory factory = ServiceFactory.getInstance();
		FilmService filmService = factory.getFilmService();
		
		try {
			Film film = filmService.getFilm(filmId);
			request.setAttribute(ParameterName.FILM, film);
			request.getRequestDispatcher(JSPPageName.EDIT_FILM).forward(request, response);
		} catch (ServiceException e) {
			logger.error("Error executing command", e);
			response.sendRedirect(request.getContextPath() + JSPPageName.MAIN);
		}
		
		
	}

}
