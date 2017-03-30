package com.epam.library.controller;

import com.epam.library.command.Command;

import com.epam.library.domain.Request;
import com.epam.library.domain.Response;


public class Controller {
	private final CommandProvider provider = new CommandProvider();

	public Response doAction(Request request) {
		String commandName = request.getCommandName();
		Command command = provider.getCommand(commandName);
		Response response=null;
		
		if(command!=null){
			response = command.execute(request);
		}
		
		return response;
	}

}