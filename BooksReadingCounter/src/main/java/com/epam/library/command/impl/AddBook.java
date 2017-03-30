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

public class AddBook implements Command{
	private final static String TITLE = "title";
	private final static String AUTHOR = "author";
	private final static String BRIEF = "brief";
	private final static String PUBLISH_YEAR = "publishYear";
	private final static String ADD_BOOK_ERROR = "Add book error";
	public final static Logger logger = Logger.getLogger(AddBook.class);
	
	public Response execute(Request request) {
		ServiceFactory factory = ServiceFactory.getINSTANCE();
		BookService bookService = factory.getBookService();
		String title = null;
		String author = null;
		String brief = null;
		int publishYear = -1;
		Book book = null;
        Response response=new Response();

	    title = request.getParameter(TITLE).toString();
	    author = request.getParameter(AUTHOR).toString();
	    brief = request.getParameter(BRIEF).toString();
	    publishYear = (Integer)request.getParameter(PUBLISH_YEAR);

		book = new Book();
		book.setAuthor(author);
		book.setBrief(brief);
		book.setPublishYear(publishYear);
		book.setTitle(title);

		try {
			bookService.addBook(book);
		}
		catch (ServiceException e) {
			logger.error(ADD_BOOK_ERROR);
			response.setErrorMessage(ADD_BOOK_ERROR);
		} 
		
		return response;
	}

}
