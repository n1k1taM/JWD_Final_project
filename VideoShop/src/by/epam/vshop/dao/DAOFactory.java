package by.epam.vshop.dao;

import by.epam.vshop.dao.impl.CommentDAOImpl;
import by.epam.vshop.dao.impl.FilmDAOImpl;
import by.epam.vshop.dao.impl.InitializationSourceDAOImpl;
import by.epam.vshop.dao.impl.OrderDAOImpl;
import by.epam.vshop.dao.impl.UserDAOImpl;

public class DAOFactory {
	private static DAOFactory instance = null;
	private final FilmDAO filmDAO = new FilmDAOImpl();
	private final InitializationSourceDAO initializationSourceDAO = new InitializationSourceDAOImpl();
	private final UserDAO userDAO = new UserDAOImpl();
	private final CommentDAO commentDAO = new CommentDAOImpl();
	private final OrderDAO orderDAO = new OrderDAOImpl();

	public static synchronized DAOFactory getInstance() {
		if (instance == null) {
			instance = new DAOFactory();
		}
		return instance;
	}

	public FilmDAO getFilmDAO() {
		return filmDAO;
	}

	public InitializationSourceDAO getInitializationSourceDAO() {
		return initializationSourceDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public CommentDAO getCommentDAO() {
		return commentDAO;
	}

	public OrderDAO getOrderDAO() {
		return orderDAO;
	}
	
	

}
