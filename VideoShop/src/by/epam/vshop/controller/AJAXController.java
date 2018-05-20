package by.epam.vshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.vshop.controller.command.Command;
import by.epam.vshop.controller.command.CommandFactory;

public class AJAXController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(AJAXController.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		CommandFactory commandFactory = CommandFactory.getInstance();
		String commandName = request.getParameter(ParameterName.COMMAND);
		Command command = commandFactory.getCommand(commandName);
		try {
			command.execute(request, response);
		} catch (IOException e) {
			logger.error("Operation error", e);
		}
	}

}
