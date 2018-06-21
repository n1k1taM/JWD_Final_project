package by.epam.vshop.controller.command.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.vshop.bean.Order;
import by.epam.vshop.bean.UserRole;
import by.epam.vshop.controller.JSPPageName;
import by.epam.vshop.controller.ParameterName;
import by.epam.vshop.controller.command.Command;
import by.epam.vshop.service.OrderService;
import by.epam.vshop.service.ServiceFactory;
import by.epam.vshop.service.exception.ServiceException;

public class GetUserPaymentHistory implements Command {
	private final static Logger logger = Logger.getLogger(GetUserPaymentHistory.class);
	private final static String SHOW_ORDER_LIST = "show_order_list";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		String userRole = (String)session.getAttribute(ParameterName.ROLE);
		
		String userId = null;
		if (userRole.equalsIgnoreCase(UserRole.ADMIN.name())) {
			userId = request.getParameter(ParameterName.USER_ID);
		}else {
			userId = (String.valueOf(session.getAttribute(ParameterName.USER_ID)));
		}
		

		ServiceFactory factory = ServiceFactory.getInstance();
		OrderService orderService = factory.getOrderService();

		try {
			List<Order> orderList = orderService.getPayedOrdersByUserId(userId);
			request.setAttribute(ParameterName.ORDER_LIST, orderList);
			request.setAttribute(ParameterName.USER_ID, userId);
			request.setAttribute(ParameterName.ACCOUNT_COMMAND, SHOW_ORDER_LIST);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.USER_ORDER_HISTORY);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			logger.error("Error executing command", e);
			response.sendRedirect(request.getContextPath() +  JSPPageName.INDEX_PAGE);
		}
	}

}
