package by.epam.vshop.controller.command.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.vshop.controller.ParameterName;
import by.epam.vshop.controller.command.Command;
import by.epam.vshop.service.CommentService;
import by.epam.vshop.service.ServiceFactory;
import by.epam.vshop.service.exception.ServiceException;

public class AddComment implements Command {
	private final static Logger logger = Logger.getLogger(AddComment.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		String strFilmId = request.getParameter(ParameterName.FILM_ID);
		int userId = (Integer) session.getAttribute(ParameterName.USER_ID);
		String message = request.getParameter(ParameterName.COMMENT_MESSAGE);

		ServiceFactory factory = ServiceFactory.getInstance();
		CommentService commentService = factory.getCommentService();

		try {
			commentService.addComment(userId, strFilmId, message);
		} catch (ServiceException e) {
			logger.error(e);

		}
		response.sendRedirect(request.getContextPath() + "/Controller?command=show_film&filmId=" + strFilmId
				+ "&commentPageNumber=1");

	}

}
