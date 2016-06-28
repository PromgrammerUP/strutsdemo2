package org.javachina.login.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javachina.framework.struts.action.Action;
import org.javachina.framework.struts.action.ActionForm;

public class LoginAction implements Action{
	public void execute (HttpServletRequest request, HttpServletResponse response ,ActionForm form) throws ServletException, IOException {
//		String userId = request.getParameter("userid");
//		String pwd  = request.getParameter("pwd");
		String userId = null;
		String pwd = null;
			LoginForm loginForm = (LoginForm) form;
			userId = loginForm.getUserId();
			pwd = loginForm.getPwd();
		String userIdFromDb = "scott";
		String pwdFromDb = "tiger";
		if(userId.equals(userIdFromDb)&&pwd.equals(pwdFromDb)){
			request.getRequestDispatcher("/success.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
}
