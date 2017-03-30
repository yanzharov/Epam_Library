package com.epam.library.command.impl;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.epam.library.command.Command;
import com.epam.library.domain.Book;
import com.epam.library.domain.Request;
import com.epam.library.domain.Response;
import com.epam.library.service.BookService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class SeeBookById implements Command{
	private final static String GETTING_BOOK_ERROR = "Getting book error";
	public final static Logger logger = Logger.getLogger(SeeBookById.class);
	private final static String ID = "id";

	public Response execute(Request request) {
		int id = -1;
		Book book = null;
		ServiceFactory factory = ServiceFactory.getINSTANCE();
		BookService bookService = factory.getBookService();
        Response response=new Response();
        
	    id = (Integer)request.getParameter(ID);

		try {
			book = bookService.getBookById(id);
			response.setBook(book);
		}
		catch (ServiceException e) {
			logger.error(GETTING_BOOK_ERROR);
			response.setErrorMessage(GETTING_BOOK_ERROR);
		}

        return response;
	}

}
