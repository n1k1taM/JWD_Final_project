package by.epam.vshop.controller.command.film;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.vshop.controller.ParameterName;
import by.epam.vshop.controller.command.Command;
import by.epam.vshop.service.FilmService;
import by.epam.vshop.service.ServiceFactory;
import by.epam.vshop.service.exception.ServiceException;

public class EditFilm implements Command {
	private final static Logger logger = Logger.getLogger(EditFilm.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String id = request.getParameter(ParameterName.FILM_ID);
		String title = request.getParameter(ParameterName.FILM_TITLE);
		String cover = request.getParameter(ParameterName.FILM_COVER);
		String url = request.getParameter(ParameterName.FILM_URL);
		String trailerUrl = request.getParameter(ParameterName.FILM_TRAILER_URL);
		String year = request.getParameter(ParameterName.FILM_YEAR);
		String price = request.getParameter(ParameterName.FILM_PRICE);
		String shortDescription = request.getParameter(ParameterName.FILM_SHORT_DESCRIPTION);
		String longDescription = request.getParameter(ParameterName.FILM_LONG_DESCRIPTION);
		String[] genres = request.getParameterValues(ParameterName.GENRE);

		ServiceFactory factory = ServiceFactory.getInstance();
		FilmService filmService = factory.getFilmService();

		try {
			filmService.editFilm(id, title, cover, url, trailerUrl, year, price, shortDescription, longDescription, genres);

			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			logger.error("Edit film error", e);
			response.sendRedirect(request.getContextPath() + "/editFilm?errorMassage=editFilmError");
		}
		
	}

}
