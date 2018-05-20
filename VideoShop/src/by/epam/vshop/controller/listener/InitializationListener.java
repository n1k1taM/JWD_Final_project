package by.epam.vshop.controller.listener;


import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import by.epam.vshop.controller.command.Command;
import by.epam.vshop.controller.command.CommandFactory;
import by.epam.vshop.controller.command.CommandName;
import by.epam.vshop.controller.command.user.SignUp;

public class InitializationListener implements ServletContextListener {
	private final static Logger logger = Logger.getLogger(SignUp.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		CommandFactory provider = CommandFactory.getInstance();
		Command command = provider.getCommand(CommandName.INITIALIZATION_SOURCE.name());
		try {
			command.execute(null, null);
		} catch (IOException | ServletException e) {
			logger.error(e);
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		CommandFactory provider = CommandFactory.getInstance();
		Command command = provider.getCommand(CommandName.DESTROY_SOURCE.name());
		try {
			command.execute(null, null);
		} catch (IOException| ServletException e) {
			logger.error(e);
		}

	}

}
