package by.epam.vshop.controller.command.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.vshop.controller.command.Command;

public class Logout implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		session.removeAttribute("login");
		session.removeAttribute("userId");
		session.removeAttribute("role");
		response.sendRedirect("index.jsp");
	}

}
