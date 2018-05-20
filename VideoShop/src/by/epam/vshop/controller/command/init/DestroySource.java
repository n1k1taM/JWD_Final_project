package by.epam.vshop.controller.command.init;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.vshop.controller.command.Command;
import by.epam.vshop.service.InitializationSourceService;
import by.epam.vshop.service.ServiceFactory;
import by.epam.vshop.service.exception.ServiceException;

public class DestroySource implements Command {
	private static final Logger logger = Logger.getLogger(DestroySource.class);
	 
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ServiceFactory factory = ServiceFactory.getInstance();
		InitializationSourceService initializationService = factory.getInitializationSourceService();
		 
		try {
			initializationService.destroySource();
			logger.info("Database has been destroyed");
		} catch (ServiceException e) {
			logger.info("Database has not been destroyed");
			logger.error(e);
		}
	}

}
