package by.epam.vshop.controller.command.init;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.vshop.controller.command.Command;
import by.epam.vshop.service.InitializationSourceService;
import by.epam.vshop.service.ServiceFactory;
import by.epam.vshop.service.exception.ServiceException;

public class InitializationSource implements Command {
	private static final Logger ligger = Logger.getLogger(InitializationSource.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory factory = ServiceFactory.getInstance();
		InitializationSourceService initializationService = factory.getInitializationSourceService();

		try {
			initializationService.initSource();
			ligger.info("Database has been initialized");
		} catch (ServiceException e) {
			ligger.info("Database has not been initialized");
			ligger.error(e);
		}
	}

}
