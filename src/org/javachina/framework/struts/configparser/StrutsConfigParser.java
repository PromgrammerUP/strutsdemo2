package org.javachina.framework.struts.configparser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javachina.framework.struts.model.ActionModel;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class StrutsConfigParser {
	public static Map<String, ActionModel> parser(String configPath){
		Map<String, ActionModel> map = new HashMap<String,ActionModel>();
		SAXBuilder builder = new SAXBuilder();
		Document doc =null;
		try {
			doc = builder.build(configPath);
		} catch (Exception e1) {
			System.err.println("配置文件未找到……");
		}
		System.out.println("struts-config.xml文件解析开始……");
		Element root = doc.getRootElement();
		//解析form-beans
		Element formBeans = root.getChild("form-beans");
		List<Element> beans = formBeans.getChildren("form-bean");
		Map<String, String> beanMap = new HashMap<String, String>();
		for (Element bean : beans) {
			String name = bean.getAttributeValue("name");
			String beanType = bean.getAttributeValue("type");
			beanMap.put(name, beanType);
		}
		//解析 action-mappings
		Element actionMappings = root.getChild("action-mappings");
		List<Element> actions = actionMappings.getChildren("action");
		for (Element e : actions) {
			//创建ActionModel对象
			ActionModel model = new ActionModel();
			String path = e.getAttributeValue("path");
			//System.out.println(path);
			String type = e.getAttributeValue("type");
			String name = e.getAttributeValue("name");
			String scope = null;
			if(name!=null){
				String beanType = beanMap.get(name);
				scope = e.getAttributeValue("scope");
				model.setBeanType(beanType);
				model.setScope(scope);
				model.setName(name);
			}
			model.setPath(path);
			model.setType(type);
			map.put(path, model);
			//System.out.println(model.getBeanType());
		}
		
		System.out.println("struts-config.xml文件解析结束……");
		return map;
	}
}
