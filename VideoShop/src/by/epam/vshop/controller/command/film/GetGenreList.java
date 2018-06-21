package by.epam.vshop.controller.command.film;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import by.epam.vshop.bean.Genre;
import by.epam.vshop.controller.command.Command;
import by.epam.vshop.service.FilmService;
import by.epam.vshop.service.ServiceFactory;
import by.epam.vshop.service.exception.ServiceException;

public class GetGenreList implements Command {
	private final static Logger logger = Logger.getLogger(GetGenreList.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ServiceFactory factory = ServiceFactory.getInstance();
		FilmService filmService = factory.getFilmService();

		try {
			List<Genre> genreList = filmService.getAllGenres();
			String json = new Gson().toJson(genreList);
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		} catch (ServiceException e) {
			logger.error("Error executing command", e);
		}

	}

}
