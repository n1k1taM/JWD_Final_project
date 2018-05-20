package by.epam.vshop.service.impl;

import java.sql.Date;
import java.util.List;

import by.epam.vshop.bean.Comment;
import by.epam.vshop.dao.CommentDAO;
import by.epam.vshop.dao.DAOFactory;
import by.epam.vshop.dao.exception.DAOException;
import by.epam.vshop.service.CommentService;
import by.epam.vshop.service.DataValidater;
import by.epam.vshop.service.exception.ServiceException;

public class CommentServiceImpl implements CommentService {
	private final int COMMENTS_PER_PAGE = 5;
	private final int DEFAULT_COMMENT_PAGE_NUMBER = 1;

	@Override
	public List<Comment> getCommentList(String strFilmId, String strCommentPageNumber) throws ServiceException {
		if (!DataValidater.validatePositiveIntegers(strFilmId, strCommentPageNumber)) {
			throw new ServiceException("Incorrect request`s parameters");
		}
		int filmId = Integer.parseInt(strFilmId);
		int commentPageNumber = Integer.parseInt(strCommentPageNumber);
		if (commentPageNumber == 0) {
			commentPageNumber = DEFAULT_COMMENT_PAGE_NUMBER;
		}

		DAOFactory daoFactory = DAOFactory.getInstance();
		CommentDAO commentDAO = daoFactory.getCommentDAO();

		int numberFirstCommentOnPage = (commentPageNumber - 1) * COMMENTS_PER_PAGE;
		try {
			List<Comment> commentList = commentDAO.getCommentList(filmId, numberFirstCommentOnPage, COMMENTS_PER_PAGE);
			return commentList;

		} catch (DAOException e) {
			throw new ServiceException("Can`t get fimls by genre id", e);
		}
	}

	@Override
	public void addComment(int userId, String strFilmId, String message) throws ServiceException {
		if (!DataValidater.validatePositiveInteger(strFilmId) || DataValidater.isStringEmpty(message)) {
			throw new ServiceException("Incorect request parameters");
		}
		int filmId = Integer.parseInt(strFilmId);
		Date currentDate = new Date(new java.util.Date().getTime());
		
		Comment comment = new Comment();
		comment.setFilmId(filmId);
		comment.setUserId(userId);
		comment.setDate(currentDate);
		comment.setMassage(message);

		DAOFactory daoFactory = DAOFactory.getInstance();
		CommentDAO commentDAO = daoFactory.getCommentDAO();

		try {
			commentDAO.addComment(comment);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
