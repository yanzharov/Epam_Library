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

public class UpdateBook implements Command{
	private final static String TITLE = "title";
	private final static String AUTHOR = "author";
	private final static String BRIEF = "brief";
	private final static String PUBLISH_YEAR = "publishYear";
	private final static String ID = "id";
	private final static String UPDATE_BOOK_ERROR = "Update book error";
	public final static Logger logger = Logger.getLogger(UpdateBook.class);
	
	public Response execute(Request request) {
		ServiceFactory factory = ServiceFactory.getINSTANCE();
		BookService bookService = factory.getBookService();
		String title = null;
		String author = null;
		String brief = null;
		int publishYear = -1;
		Book book = null;
		int id = -1;
		Response response=new Response();
		
		id=(Integer)request.getParameter(ID);
		title = request.getParameter(TITLE).toString();
	    author = request.getParameter(AUTHOR).toString();
	    brief = request.getParameter(BRIEF).toString();
	    publishYear = (Integer)request.getParameter(PUBLISH_YEAR);
		
		book = new Book();
		book.setId(id);
		book.setAuthor(author);
		book.setBrief(brief);
		book.setPublishYear(publishYear);
		book.setTitle(title);

		try {
			bookService.updateBook(book);
		}
		catch (ServiceException e) {
			logger.error(UPDATE_BOOK_ERROR);
			response.setErrorMessage(UPDATE_BOOK_ERROR);
		}
		
		return response;
	}

}
