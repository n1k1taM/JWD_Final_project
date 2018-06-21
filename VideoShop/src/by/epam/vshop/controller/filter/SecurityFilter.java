package by.epam.vshop.controller.filter;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.vshop.bean.UserRole;
import by.epam.vshop.controller.JSPPageName;
import by.epam.vshop.controller.ParameterName;
import by.epam.vshop.controller.command.CommandName;

public class SecurityFilter implements Filter {
	private final static Logger logger = Logger.getLogger(SecurityFilter.class);

	private HashSet<CommandName> clientCommands = new HashSet<>();
	private HashSet<CommandName> adminCommands = new HashSet<>();
	private HashSet<CommandName> unauthorizedUserCommands = new HashSet<>();

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		clientCommands.add(CommandName.ADD_COMMENT);
		clientCommands.add(CommandName.ADD_FILM_TO_ACTIVE_ORDER);
		clientCommands.add(CommandName.ADD_RATING);
		clientCommands.add(CommandName.DELETE_FILM_FROM_ACTIVE_ORDER);
		clientCommands.add(CommandName.SHOW_PAYMENT_PAGE);
		clientCommands.add(CommandName.GET_GENRE_LIST);
		clientCommands.add(CommandName.GET_USER_PAYMENT_HISTORY);
		clientCommands.add(CommandName.LOCALIZATION);
		clientCommands.add(CommandName.PAY_ORDER);
		clientCommands.add(CommandName.SHOW_ACTIVE_ORDER);
		clientCommands.add(CommandName.SHOW_FILM);
		clientCommands.add(CommandName.SHOW_FILM_LIST);
		clientCommands.add(CommandName.SHOW_FILMS_BY_GENRE);
		clientCommands.add(CommandName.SHOW_MAIN_PAGE);
		clientCommands.add(CommandName.SHOW_USER_ACCOUNT);
		clientCommands.add(CommandName.WATCH_FILM);
		clientCommands.add(CommandName.LOCALIZATION);
		clientCommands.add(CommandName.LOGOUT);
		clientCommands.add(CommandName.SHOW_CLIENT_FILM_LIST);

		adminCommands.add(CommandName.ADD_DISCOUNT_TO_CLIENT);
		adminCommands.add(CommandName.ADD_FILM);
		adminCommands.add(CommandName.SHOW_ADD_FILM_FORM);
		adminCommands.add(CommandName.CHANGE_USER_STATUS);
		adminCommands.add(CommandName.EDIT_FILM);
		adminCommands.add(CommandName.GET_GENRE_LIST);
		adminCommands.add(CommandName.GET_USER_PAYMENT_HISTORY);
		adminCommands.add(CommandName.LOAD_FILM_ON_EDIT_PAGE);
		adminCommands.add(CommandName.LOCALIZATION);
		adminCommands.add(CommandName.LOGOUT);
		adminCommands.add(CommandName.SHOW_CLIENT_FILM_LIST);
		adminCommands.add(CommandName.SHOW_FILM);
		adminCommands.add(CommandName.SHOW_FILM_LIST);
		adminCommands.add(CommandName.SHOW_FILMS_BY_GENRE);
		adminCommands.add(CommandName.SHOW_MAIN_PAGE);
		adminCommands.add(CommandName.SHOW_USER_ACCOUNT_FOR_ADMIN);
		adminCommands.add(CommandName.USER_LIST);

		unauthorizedUserCommands.add(CommandName.GET_GENRE_LIST);
		unauthorizedUserCommands.add(CommandName.LOCALIZATION);
		unauthorizedUserCommands.add(CommandName.SHOW_FILM);
		unauthorizedUserCommands.add(CommandName.SHOW_FILM_LIST);
		unauthorizedUserCommands.add(CommandName.SHOW_FILMS_BY_GENRE);
		unauthorizedUserCommands.add(CommandName.SHOW_MAIN_PAGE);
		unauthorizedUserCommands.add(CommandName.SIGN_UP);
		unauthorizedUserCommands.add(CommandName.SING_IN);
		unauthorizedUserCommands.add(CommandName.SHOW_SIGN_IN_FORM);
		unauthorizedUserCommands.add(CommandName.SHOW_SIGN_UP_FORM);

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();

		String strCommandName = request.getParameter(ParameterName.COMMAND);
		String userRole = (String) session.getAttribute(ParameterName.ROLE);

		if (strCommandName != null) {
			CommandName commandName = null;
			try {
				commandName = CommandName.valueOf(strCommandName.toUpperCase());
				boolean isCommandAvailable = false;

				if (userRole == null) {
					isCommandAvailable = unauthorizedUserCommands.contains(commandName);

				} else if (userRole.equalsIgnoreCase(UserRole.CLIENT.name())) {
					isCommandAvailable = clientCommands.contains(commandName);
				} else if (userRole.equalsIgnoreCase(UserRole.ADMIN.name())) {
					isCommandAvailable = adminCommands.contains(commandName);
				}

				if (isCommandAvailable) {
					chain.doFilter(servletRequest, servletResponse);
				} else {
					response.sendRedirect(request.getContentType() + JSPPageName.INDEX_PAGE);
				}

			} catch (IllegalArgumentException e) {
				logger.error("Unknown command", e);
				response.sendRedirect(request.getContextPath() + JSPPageName.INDEX_PAGE);
			}

		} else {
			chain.doFilter(servletRequest, servletResponse);

		}
	}

	@Override
	public void destroy() {
	}

}
