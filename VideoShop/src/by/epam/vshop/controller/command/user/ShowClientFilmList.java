package by.epam.vshop.controller.command.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.vshop.bean.Film;
import by.epam.vshop.bean.Genre;
import by.epam.vshop.controller.JSPPageName;
import by.epam.vshop.controller.ParameterName;
import by.epam.vshop.controller.command.Command;
import by.epam.vshop.service.FilmService;
import by.epam.vshop.service.ServiceFactory;
import by.epam.vshop.service.exception.ServiceException;

public class ShowClientFilmList implements Command{
	private final static Logger logger = Logger.getLogger(ShowClientFilmList.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		String strClientId = ((Integer)session.getAttribute(ParameterName.USER_ID)).toString();
		String strCurrentPageNumber = request.getParameter(ParameterName.PAGE_NUMBER);
		
		ServiceFactory factory = ServiceFactory.getInstance();
		FilmService filmService = factory.getFilmService();
		
		try {
			List<Genre> genreList = filmService.getAllGenres();
			List<Film> filmList = filmService.getClientFilmList(strClientId, strCurrentPageNumber);
			int clientFilmsMaxPage = filmService.getClientFilmsMaxPage(strClientId);
			
			request.setAttribute("filmList", filmList);
			request.setAttribute("genreList", genreList);
			request.setAttribute("currentPageNumber", strCurrentPageNumber);
			request.setAttribute("maxPageNumber", clientFilmsMaxPage);
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.SHOW_CLIENT_FILMS);
			dispatcher.forward(request, response);
			
		} catch (ServiceException e) {
			logger.error("Error executing command", e);
			response.sendRedirect(request.getContextPath() + JSPPageName.INDEX_PAGE);
		}
		
		
		
		
	}

}
