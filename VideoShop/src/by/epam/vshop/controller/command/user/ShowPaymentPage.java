package by.epam.vshop.controller.command.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.vshop.bean.Film;
import by.epam.vshop.bean.Order;
import by.epam.vshop.controller.JSPPageName;
import by.epam.vshop.controller.ParameterName;
import by.epam.vshop.controller.command.Command;
import by.epam.vshop.service.FilmService;
import by.epam.vshop.service.OrderService;
import by.epam.vshop.service.ServiceFactory;
import by.epam.vshop.service.UserService;
import by.epam.vshop.service.exception.ServiceException;

public class ShowPaymentPage implements Command{
	private final static Logger logger = Logger.getLogger(ShowPaymentPage.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute(ParameterName.USER_ID);
		
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		OrderService orderService = factory.getOrderService();
		FilmService filmService = factory.getFilmService();
		
		try {
			Order order = orderService.getActiveOrder(userId);
			List<Film> filmList = filmService.getFilmListByOrderId(order.getId());
			order.setFilms(filmList);
			
			float orderCostBeforeDiscount = orderService.getCostBeforeDiscount(order);
			int discount = userService.getDiscountValue(userId);
			float orderCostAfterDiscount = orderService.getOrderCostAfterDiscount(orderCostBeforeDiscount, discount);
			
			request.setAttribute("costAfterDiscount", orderCostAfterDiscount);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.PAYMENT);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			logger.error(e);
			response.sendRedirect(JSPPageName.INDEX_PAGE);
		}
		
	}

}
