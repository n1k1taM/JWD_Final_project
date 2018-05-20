package by.epam.vshop.dao;


import java.util.List;

import by.epam.vshop.bean.Comment;
import by.epam.vshop.dao.exception.DAOException;

public interface CommentDAO {
	List<Comment> getCommentList(int filmId, int begin, int offset) throws DAOException;

	void addComment(Comment comment)throws DAOException;

}
