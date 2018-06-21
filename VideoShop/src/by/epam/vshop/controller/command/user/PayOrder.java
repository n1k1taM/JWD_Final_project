package by.epam.vshop.controller.command.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.vshop.controller.ParameterName;
import by.epam.vshop.controller.command.Command;
import by.epam.vshop.service.OrderService;
import by.epam.vshop.service.ServiceFactory;
import by.epam.vshop.service.exception.ServiceException;

public class PayOrder implements Command{
	private final static Logger logger = Logger.getLogger(PayOrder.class);	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		String strPaymentValue = request.getParameter(ParameterName.PAYMENT_VALUE);
		
		ServiceFactory factory = ServiceFactory.getInstance();
		OrderService orderService = factory.getOrderService();
		
		
		try {
			orderService.payOrder(userId, strPaymentValue);
		} catch (ServiceException e) {
			logger.error("Error executing command", e);
		}
		response.sendRedirect(request.getContextPath() + "/Controller?command=show_client_film_list&pageNumber=1");
		
		
	}

}
