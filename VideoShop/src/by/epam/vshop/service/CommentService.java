package by.epam.vshop.service;

import java.util.List;

import by.epam.vshop.bean.Comment;
import by.epam.vshop.service.exception.ServiceException;

public interface CommentService {
	List<Comment> getCommentList(String strFilmId, String strCommentPageNumber) throws ServiceException;

	void addComment(int userId, String strFilmId, String message) throws ServiceException;

}
