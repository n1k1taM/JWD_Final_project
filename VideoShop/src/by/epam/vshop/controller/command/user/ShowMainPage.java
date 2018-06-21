package by.epam.vshop.controller.command.user;

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
import by.epam.vshop.controller.ParameterName;
import by.epam.vshop.controller.command.Command;
import by.epam.vshop.service.FilmService;
import by.epam.vshop.service.ServiceFactory;
import by.epam.vshop.service.exception.ServiceException;

public class ShowMainPage implements Command{
	private static final Logger logger = Logger.getLogger(ShowMainPage.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String pageNumber = request.getParameter(ParameterName.PAGE_NUMBER);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		FilmService filmService = serviceFactory.getFilmService();
		try {
			int maxPageNumber = filmService.getMaxPageNumber();
			List<Film> filmList = filmService.getFilmList(pageNumber, ((Integer)maxPageNumber).toString());
			List<Genre> genreList = filmService.getAllGenres();

			request.setAttribute("filmList", filmList);
			request.setAttribute("genreList", genreList);
			request.setAttribute("currentPageNumber", pageNumber);
			request.setAttribute("maxPageNumber", maxPageNumber);
			

			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.MAIN);
			dispatcher.forward(request, response);

		} catch (ServiceException e) {
			logger.error("Show main page error", e);
			response.sendRedirect(request.getContextPath() +  JSPPageName.INDEX_PAGE);
		}
		
	}
	
}
