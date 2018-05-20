package by.epam.vshop.controller.command.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.vshop.controller.ParameterName;
import by.epam.vshop.controller.command.Command;
import by.epam.vshop.service.FilmService;
import by.epam.vshop.service.ServiceFactory;
import by.epam.vshop.service.exception.ServiceException;

public class AddRating implements Command{
	private final static Logger logger = Logger.getLogger(AddRating.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute(ParameterName.USER_ID);
		String strFilmId = request.getParameter(ParameterName.FILM_ID);
		String ratingValue = request.getParameter(ParameterName.FILM_RATING);
		
		ServiceFactory factory = ServiceFactory.getInstance();
		FilmService filmService = factory.getFilmService();
		
		try {
			filmService.addRating(userId, strFilmId, ratingValue);
		} catch (ServiceException e) {
			logger.error(e);
		}
		response.sendRedirect(request.getContextPath() + "/Controller?command=show_film&commentPageNumber=1&filmId=" + strFilmId);
		
		
	}

}
