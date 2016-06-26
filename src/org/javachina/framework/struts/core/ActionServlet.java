package org.javachina.framework.struts.core;


import java.io.IOException;
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
		String act = request.getParameter("act");
//		if(act.equals("login")){
//			LoginAction login = new LoginAction();
//			login.execute(request, response);
//		}
		//第一次调用时，struts-config.xml已经解析完毕
		ServletContext application = this.getServletContext();
		Map<String,String> map = (Map)application.getAttribute("actions");
		//System.out.println(map);
		if(map.containsKey(act)){
			String type = map.get(act);
			try {
				Class clazz = Class.forName(type);
				Object obj = clazz.newInstance();
				Method exe = clazz.getDeclaredMethod("execute", HttpServletRequest.class,HttpServletResponse.class);
				exe.invoke(obj, request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("action路径未找到"+type);
			} 
			
			
		}else{
			System.out.println("本次http请求"+act+"未找到");
		}
	}
	/*
	 * 解析xml,并存放入servletcontext
	 */
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String,String>();
		SAXBuilder builder = new SAXBuilder();
		Document doc =null;
		try {
			doc = builder.build("D:\\mywork01\\eclipseMarsWork\\strutsdemo2\\WebContent\\WEB-INF\\struts-config.xml");
		} catch (Exception e1) {
			System.err.println("配置文件未找到……");
		}
		System.out.println("struts-config.xml文件解析开始……");
		Element root = doc.getRootElement();
		Element actionMappings = root.getChild("action-mappings");
		List<Element> actions = actionMappings.getChildren("action");
		for (Element e : actions) {
			String path = e.getAttributeValue("path");
			//System.out.println(path);
			String type = e.getAttributeValue("type");
			map.put(path, type);
		}
		System.out.println("struts-config.xml文件解析结束……");
		//把解析后的结果存储到servletcontext--->application中
		ServletContext application = this.getServletContext();
		application.setAttribute("actions", map);
	}
}
