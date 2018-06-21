package by.epam.vshop.controller.command.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.vshop.bean.Film;
import by.epam.vshop.controller.JSPPageName;
import by.epam.vshop.controller.ParameterName;
import by.epam.vshop.controller.command.Command;
import by.epam.vshop.service.FilmService;
import by.epam.vshop.service.OrderService;
import by.epam.vshop.service.ServiceFactory;
import by.epam.vshop.service.exception.ServiceException;

public class WatchFilm implements Command{
	private final static Logger logger = Logger.getLogger(WatchFilm.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		int userId = (Integer)session.getAttribute(ParameterName.USER_ID);
		String strFilmId = request.getParameter(ParameterName.FILM_ID);
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		FilmService filmService = serviceFactory.getFilmService();
		OrderService orderService = serviceFactory.getOrderService();
		
		try {
			if (orderService.isFilmInPayedOrder(userId, strFilmId)) {
				Film film = filmService.getFilm(strFilmId);
				request.setAttribute(ParameterName.FILM, film);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.WATCH_FILM);
				dispatcher.forward(request, response);
			}
			
		} catch (ServiceException e) {
			logger.error("Error executing command", e);
			response.sendRedirect(request.getContextPath() + JSPPageName.INDEX_PAGE);
		}
		
		
		
	}

}
