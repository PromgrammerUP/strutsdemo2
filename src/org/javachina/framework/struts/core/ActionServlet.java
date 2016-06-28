package org.javachina.framework.struts.core;


import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javachina.framework.struts.action.ActionForm;
import org.javachina.framework.struts.configparser.StrutsConfigParser;
import org.javachina.framework.struts.model.ActionModel;
import org.javachina.login.view.LoginAction;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * Servlet implementation class ActionServlet
 */
//@WebServlet("/ActionServlet")
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doRequest(request, response); 
	}
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建一个请求处理器
		RequestProcesser processer = new RequestProcesser();
		//请求处理器处理请求
		processer.process(request, response);
	}
	/*
	 * 解析xml,并存放入servletcontext
	 */
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		//"D:\\mywork01\\eclipseMarsWork\\strutsdemo2\\WebContent\\WEB-INF\\struts-config.xml"
		Map<String, ActionModel> map = StrutsConfigParser.parser("D:\\mywork01\\eclipseMarsWork\\strutsdemo2\\WebContent\\WEB-INF\\struts-config.xml");
		//把解析后的结果存储到servletcontext--->application中
		ServletContext application = this.getServletContext();
		application.setAttribute("actions", map);
	}
}
