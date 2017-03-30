package com.epam.library.command.impl;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.epam.library.command.Command;
import com.epam.library.domain.Request;
import com.epam.library.domain.Response;
import com.epam.library.service.BookService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class DeleteBook implements Command{
	public final static Logger logger = Logger.getLogger(DeleteBook.class);
	private final static String ID = "id";
	private final static String DELETE_BOOK_ERROR = "Delete book error";
	
	public Response execute(Request request) {
		int id = -1;
		ServiceFactory factory = ServiceFactory.getINSTANCE();
		BookService bookService = factory.getBookService();
		Response response=new Response();
		
		id=(Integer)request.getParameter(ID);

		try {
			bookService.deleteBook(id);
		}
		catch (ServiceException e) {
			logger.error(DELETE_BOOK_ERROR);
			response.setErrorMessage(DELETE_BOOK_ERROR);
		}
		
		return response;
	}

}
