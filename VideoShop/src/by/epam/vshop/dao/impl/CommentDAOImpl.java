package by.epam.vshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.vshop.bean.Comment;
import by.epam.vshop.dao.ColumnLabel;
import by.epam.vshop.dao.CommentDAO;
import by.epam.vshop.dao.connection.ConnectionPool;
import by.epam.vshop.dao.exception.ConnectionPoolException;
import by.epam.vshop.dao.exception.DAOException;
import by.epam.vshop.dao.manager.SQLRequestManager;

public class CommentDAOImpl implements CommentDAO {

	@Override
	public List<Comment> getCommentList(int filmId, int begin, int offset) throws DAOException {

		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Comment> commentList = null;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.GET_ACTIVE_COMMENTS_LIST_BY_FILM_ID);
			preparedStatement.setInt(1, filmId);
			preparedStatement.setInt(2, begin);
			preparedStatement.setInt(3, offset);
			resultSet = preparedStatement.executeQuery();
			commentList = new ArrayList<Comment>();
			while (resultSet.next()) {
				Comment comment = new Comment();
				comment.setId(resultSet.getInt(ColumnLabel.COMMENT_ID));
				comment.setFilmId(resultSet.getInt(ColumnLabel.FILM_ID));
				comment.setMassage(resultSet.getString(ColumnLabel.COMMENT_MESSAGE));
				comment.setStatus(resultSet.getBoolean(ColumnLabel.COMMENT_STATUS));
				comment.setUserLogin(resultSet.getString(ColumnLabel.USER_LOGIN));
				comment.setUserId(resultSet.getInt(ColumnLabel.USER_ID));
				comment.setDate(resultSet.getDate(ColumnLabel.COMMENT_DATE));
				commentList.add(comment);

			}

		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("SQL mistake", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}

		return commentList;
	}

	@Override
	public void addComment(Comment comment) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.ADD_COMMENT_TO_FILM);
			preparedStatement.setInt(1, comment.getFilmId());
			preparedStatement.setInt(2, comment.getUserId());
			preparedStatement.setDate(3, comment.getDate());
			preparedStatement.setString(4, comment.getMassage());
			preparedStatement.executeUpdate();

		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("SQL mistake in query 'add_comment_fo_film'", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}

	}

}
