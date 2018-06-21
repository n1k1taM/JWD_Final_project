package by.epam.vshop.controller.command.film;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.vshop.bean.Comment;
import by.epam.vshop.bean.Film;
import by.epam.vshop.bean.Genre;
import by.epam.vshop.controller.JSPPageName;
import by.epam.vshop.controller.ParameterName;
import by.epam.vshop.controller.command.Command;
import by.epam.vshop.service.CommentService;
import by.epam.vshop.service.FilmService;
import by.epam.vshop.service.OrderService;
import by.epam.vshop.service.ServiceFactory;
import by.epam.vshop.service.exception.ServiceException;

public class ShowFilm implements Command {
	private final static Logger logger = Logger.getLogger(ShowFilm.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute(ParameterName.USER_ID);

		String strFilmId = request.getParameter(ParameterName.FILM_ID);
		String strCommentPageNumber = request.getParameter("commentPageNumber");

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		FilmService filmService = serviceFactory.getFilmService();
		CommentService commentService = serviceFactory.getCommentService();
		OrderService orderService = serviceFactory.getOrderService();

		try {
			Film film = filmService.getFilm(strFilmId);
			List<Genre> genreList = filmService.getAllGenres();
			List<Comment> commentList = commentService.getCommentList(strFilmId, strCommentPageNumber);

			request.setAttribute("film", film);
			request.setAttribute("genreList", genreList);
			request.setAttribute("commentList", commentList);

			if (userId != null) {
				request.setAttribute("filmOrderStatus", orderService.getOrderStatus(userId, strFilmId));
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.SHOW_FILM);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			logger.error("Error executing command", e);
			response.sendRedirect(request.getContextPath() + JSPPageName.INDEX_PAGE);
		}
		

	}

}
