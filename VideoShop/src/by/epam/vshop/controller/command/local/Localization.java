package by.epam.vshop.controller.command.local;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.vshop.controller.JSPPageName;
import by.epam.vshop.controller.ParameterName;
import by.epam.vshop.controller.command.Command;

public class Localization implements Command {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession(true).setAttribute(ParameterName.LOCAL, request.getParameter(ParameterName.LOCAL));
		response.sendRedirect(request.getContextPath() + JSPPageName.INDEX_PAGE);
	}
	 
}
