package com.epam.library.command.impl;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.epam.library.command.Command;
import com.epam.library.domain.Request;
import com.epam.library.domain.Response;
import com.epam.library.service.BookService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class RenameBook implements Command{
	public final static Logger logger = Logger.getLogger(RenameBook.class);
	private final static String OLD_TITLE = "oldTitle";
	private final static String NEW_TITLE = "newTitle";
	private final static String RENAME_BOOK_ERROR = "Rename book error";
	
	public Response execute(Request request) {
		ServiceFactory factory = ServiceFactory.getINSTANCE();
		BookService bookService = factory.getBookService();
		String oldTitle = null;
		String newTitle = null;
		Response response=new Response();
		
	    oldTitle = request.getParameter(OLD_TITLE).toString();
		newTitle = request.getParameter(NEW_TITLE).toString();

		try {
			bookService.renameBookByTitle(oldTitle, newTitle);
		}

		catch (ServiceException e) {
			logger.error(RENAME_BOOK_ERROR);
			response.setErrorMessage(RENAME_BOOK_ERROR);
		}
		
		return response;
	}

}
