package com.agri.agriculture.webServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/change-language")
public class LanguageServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String selectedLanguage = request.getParameter("language");

		// Implement logic to change the app's language based on selectedLanguage.
		// For example, you can set a session attribute or a cookie to store the
		// selected language.

		// Respond to the client with a success message or appropriate response.
		response.getWriter().write("Language changed to " + selectedLanguage);
	}
}
