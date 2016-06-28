package org.javachina.login.view;

import org.javachina.framework.struts.action.ActionForm;

public class LoginForm extends ActionForm{
	private String userId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	private String pwd;
}
