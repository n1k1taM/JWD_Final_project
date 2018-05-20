package by.epam.vshop.controller.command.film;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.vshop.bean.Film;
import by.epam.vshop.bean.Genre;
import by.epam.vshop.controller.JSPPageName;
import by.epam.vshop.controller.command.Command;
import by.epam.vshop.service.FilmService;
import by.epam.vshop.service.ServiceFactory;
import by.epam.vshop.service.exception.ServiceException;

public class ShowFilmsByGenre implements Command {
	private final static Logger logger = Logger.getLogger(ShowFilmsByGenre.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String pageNumber = request.getParameter("pageNumber");
		String strGenreId = request.getParameter("genreId");
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		FilmService filmService = serviceFactory.getFilmService();
		try {
			List<Film> filmList = filmService.getFilmListByGenreId(strGenreId, pageNumber);
			List<Genre> genreList = filmService.getAllGenres();
			int maxPageNumber = filmService.getMaxPageNumberbyGenreId(strGenreId);

			request.setAttribute("filmList", filmList);
			request.setAttribute("genreList", genreList);
			request.setAttribute("currentGenreId", strGenreId);
			request.setAttribute("currentPageNumber", pageNumber);
			request.setAttribute("maxPageNumber", maxPageNumber);
			

			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.SHOW_FILM_BY_GENRE);
			dispatcher.forward(request, response);

		} catch (ServiceException e) {
			logger.error(e);
			response.sendRedirect(JSPPageName.MAIN);
		}

	}

}
