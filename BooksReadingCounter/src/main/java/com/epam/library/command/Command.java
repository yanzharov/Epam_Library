package com.epam.library.command;

import com.epam.library.domain.Request;
import com.epam.library.domain.Response;

public interface Command {
	
	Response execute(Request request);
	
}
