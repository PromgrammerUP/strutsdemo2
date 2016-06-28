package org.javachina.framework.struts.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javachina.framework.struts.action.ActionForm;
import org.javachina.framework.struts.model.ActionModel;

public class RequestProcesser {
	public void process(HttpServletRequest request, HttpServletResponse response){
		String act = request.getParameter("act");
//		if(act.equals("login")){
//			LoginAction login = new LoginAction();
//			login.execute(request, response);
//		}
		//第一次调用时，struts-config.xml已经解析完毕
		ServletContext application = request.getSession().getServletContext();
		Map<String,ActionModel> map = (Map)application.getAttribute("actions");
		//System.out.println(map);
		if(map.containsKey(act)){
			ActionModel model = map.get(act);
			//创建form
			String beanType = model.getBeanType();
			String type = model.getType();
			Object form = null;
			try {
			if(beanType!=null){//证明用户配置过form
				Class formClazz = Class.forName(beanType);
				form = formClazz.newInstance();
				Field[] fields = formClazz.getDeclaredFields();
				for (Field field : fields) {
					//取得formBean的属性名
					String name = field.getName();
					//用户提交的form数据
					String value = request.getParameter(name);
					field.setAccessible(true);
					field.set(form, value);
					field.setAccessible(false);
				}
				
				
			}
			Class clazz = Class.forName(type);
			Object obj = clazz.newInstance();
			Method exe = clazz.getDeclaredMethod("execute", HttpServletRequest.class,HttpServletResponse.class,ActionForm.class);
			exe.invoke(obj, request,response,form);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("action路径未找到"+type);
			} 
			
			
		}else{
			System.out.println("本次http请求"+act+"未找到");
		}
	}
}
