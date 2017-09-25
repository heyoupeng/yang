package com.yh.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.yh.model.Account;
import com.yh.model.Repair;

public class LoginListener implements HttpSessionAttributeListener {
	private static final String LISTENER_NAME = "user";
	private static Map<String, HttpSession> map = new HashMap<String, HttpSession>();

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		// 处理用户登录情况
		if (LISTENER_NAME.equals(event.getName())) {//如果添加入session的对象名为user
			//获得user
			Account acc = (Account) event.getValue();
			//创建唯一标识id
			String id = "id:" + acc.getRid() + " name:" + acc.getUsername();
			//获得当前session
			HttpSession thisSession = event.getSession();
			if (map.containsKey(id)) {//如果已有该唯一标识
				HttpSession oldSession = map.get(id);//获得存在Map中的session
				if (oldSession.getId().equals(thisSession.getId()) == false) {//如果不是同一个
					thisSession.removeAttribute("user");//清空当前session的user值，阻止登录
				}
			} else {
				map.put(id, thisSession);
			}
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
	}
}
