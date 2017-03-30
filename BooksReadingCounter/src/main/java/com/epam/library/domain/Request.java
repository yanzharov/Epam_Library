package com.epam.library.domain;

import java.util.HashMap;
import java.util.Map;

public class Request {
	private String commandName;
	private Map<String, Object> parameter = new HashMap<String, Object>();

	public String getCommandName() {
		return commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}

	public Object getParameter(String key) {
		return parameter.get(key);
	}

	public void setParameter(String key, Object value) {
		this.parameter.put(key, value);
	}
	
}
