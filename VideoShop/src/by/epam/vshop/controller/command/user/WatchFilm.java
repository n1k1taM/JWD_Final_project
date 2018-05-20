package by.epam.vshop.controller.command.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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

public class WatchFilm implements Command{
	private final static Logger logger = Logger.getLogger(WatchFilm.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String strFilmId = request.getParameter(ParameterName.FILM_ID);
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		FilmService filmService = serviceFactory.getFilmService();
		
		try {
			Film film = filmService.getFilm(strFilmId);
			request.setAttribute(ParameterName.FILM, film);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.WATCH_FILM);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			logger.error(e);
			response.sendRedirect(JSPPageName.INDEX_PAGE);
		}
		
		
		
	}

}
