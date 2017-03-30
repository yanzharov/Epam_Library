package com.epam.library.command.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.epam.library.command.Command;
import com.epam.library.domain.Book;
import com.epam.library.domain.Request;
import com.epam.library.domain.Response;
import com.epam.library.service.BookService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class SeeAllBooks implements Command{
	private final static String GETTING_BOOKS_ERROR = "Getting books error";
	public final static Logger logger = Logger.getLogger(SeeAllBooks.class);
	
	public Response execute(Request request) {
		List<Book> books = null;
		ServiceFactory factory = ServiceFactory.getINSTANCE();
		BookService bookService = factory.getBookService();
		Response response=new Response();
		
		try {
			books = bookService.getAllBooks();
			response.setBookList(books);
		}
		catch (ServiceException e) {
			logger.error(GETTING_BOOKS_ERROR);
			response.setErrorMessage(GETTING_BOOKS_ERROR);
		}
		
		return response;
	}

}
