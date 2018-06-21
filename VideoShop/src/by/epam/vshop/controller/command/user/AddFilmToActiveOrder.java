package by.epam.vshop.controller.command.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.vshop.controller.JSPPageName;
import by.epam.vshop.controller.ParameterName;
import by.epam.vshop.controller.command.Command;
import by.epam.vshop.service.OrderService;
import by.epam.vshop.service.ServiceFactory;
import by.epam.vshop.service.exception.ServiceException;

public class AddFilmToActiveOrder implements Command {
	private final static Logger logger = Logger.getLogger(AddFilmToActiveOrder.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		
		
		if (session.getAttribute(ParameterName.ROLE) == null) {
			response.sendRedirect(request.getContextPath() + JSPPageName.SIGN_IN);
			return;
		}
			
		int userId = (Integer)session.getAttribute(ParameterName.USER_ID);
		String strFilmId = request.getParameter("filmId");

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		OrderService orderService = serviceFactory.getOrderService();

		try {
			orderService.addFilmToActiveOrder(strFilmId, userId);
		} catch (ServiceException e) {
			logger.error("Error executing command", e);
		}
		response.sendRedirect(request.getContextPath() + "/Controller?command=show_film&filmId="+ strFilmId + "&commentPageNumber=1");

	}

}
