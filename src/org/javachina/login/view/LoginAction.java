package org.javachina.login.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction {
	public void execute (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userid");
		String pwd  = request.getParameter("pwd");
				
		String userIdFromDb = "scott";
		String pwdFromDb = "tiger";
		if(userId.equals(userIdFromDb)&&pwd.equals(pwdFromDb)){
			request.getRequestDispatcher("/success.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
}
