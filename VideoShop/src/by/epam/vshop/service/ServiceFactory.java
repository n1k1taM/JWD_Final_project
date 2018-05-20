package by.epam.vshop.service;

import by.epam.vshop.service.impl.CommentServiceImpl;
import by.epam.vshop.service.impl.FilmServiceImpl;
import by.epam.vshop.service.impl.OrderServiceImpl;
import by.epam.vshop.service.impl.UserServiceImpl;
import by.epam.vshop.service.impl.init.InitializationSourceServiceImpl;

public final class ServiceFactory {

	private static ServiceFactory instance = new ServiceFactory();
	private final FilmService filmService = new FilmServiceImpl();
	private final InitializationSourceService initializationSourceService = new InitializationSourceServiceImpl();
	private final UserService userService = new UserServiceImpl();
	private final CommentService commentService = new CommentServiceImpl();
	private final OrderService orderService = new OrderServiceImpl();

	public static ServiceFactory getInstance() {
		return instance;
	}

	public FilmService getFilmService() {
		return filmService;
	}

	public InitializationSourceService getInitializationSourceService() {
		return initializationSourceService;
	}

	public UserService getUserService() {
		return userService;
	}

	public CommentService getCommentService() {
		return commentService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

}
