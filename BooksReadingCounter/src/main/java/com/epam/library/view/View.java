package com.epam.library.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.epam.library.controller.Controller;
import com.epam.library.domain.Book;
import com.epam.library.domain.Employee;
import com.epam.library.domain.Request;
import com.epam.library.domain.Response;
import com.epam.library.service.BookService;
import com.epam.library.service.InitializeConnectionService;
import com.epam.library.service.StatisticService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class View {
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static boolean init = false;
	private final static Logger logger = Logger.getLogger(View.class);
	private final static Controller controller=new Controller();
	private final static String READING_ERROR = "Reading error";
	private final static String AVAILABLE_OPTIONS = "Available options:";
	private final static String SEE_ALL_BOOKS = "1 - see all books";
	private final static String SEE_BOOKS_BY_ID = "2 - see book by id";
	private final static String ADD_BOOK = "3 - add book";
	private final static String UPDATE_BOOK = "4 - update book";
	private final static String RENAME_BOOK = "5 - rename book";
	private final static String DELETE_BOOK = "6 - delete book";
	private final static String GET_GOOD_READERS = "7 - get good readers";
	private final static String GET_BAD_READERS = "8 - get bad readers";
	private final static String OTHER = "Other - exit program";
	private final static String DESIRED_VALUE = "Please input desired value";
	private final static String ID = "id";
	private final static String TITLE = "title";
	private final static String AUTHOR = "author";
	private final static String BRIEF = "brief";
	private final static String PUBLISH_YEAR = "publishYear";
	private final static String INPUT_ID = "Input id";
	private final static String INPUT_TITLE = "Input title";
	private final static String INPUT_AUTHOR = "Input author";
	private final static String INPUT_BRIEF = "Input brief";
	private final static String INPUT_PUBLISH_YEAR = "Input publish year";
	private final static String INPUT_OLD_TITLE = "Input old title";
	private final static String INPUT_NEW_TITLE = "Input new title";
	private final static String OLD_TITLE = "oldTitle";
	private final static String NEW_TITLE = "newTitle";
	private final static String ADD_BOOK_COMMAND = "ADD_BOOK";
	private final static String SEE_BOOK_BY_ID_COMMAND = "SEE_BOOK_BY_ID";
	private final static String DELETE_BOOK_COMMAND = "DELETE_BOOK";
	private final static String UPDATE_BOOK_COMMAND = "UPDATE_BOOK";
	private final static String RENAME_BOOK_COMMAND = "RENAME_BOOK";
	private final static String SEE_ALL_BOOKS_COMMAND = "SEE_ALL_BOOKS";
	private final static String GET_GOOD_READERS_COMMAND = "GET_GOOD_READERS";
	private final static String GET_BAD_READERS_COMMAND = "GET_BAD_READERS";
	
	public static void main(String[] args) {
		String i = null;
		int number;
		boolean flag = true;
		
		if (!init) {
			connectionPoolInit();
		}

		while (flag) {
			System.out.println(AVAILABLE_OPTIONS);
			System.out.println(SEE_ALL_BOOKS);
			System.out.println(SEE_BOOKS_BY_ID);
			System.out.println(ADD_BOOK);
			System.out.println(UPDATE_BOOK);
			System.out.println(RENAME_BOOK);
			System.out.println(DELETE_BOOK);
			System.out.println(GET_GOOD_READERS);
			System.out.println(GET_BAD_READERS);
			System.out.println(OTHER);
			System.out.println(DESIRED_VALUE);

			try {
				i = reader.readLine();
			}
			catch (IOException e) {
				logger.error(READING_ERROR);
			}

			number = Integer.valueOf(i);
			
			switch (number) {

				case 1:
					seeAllBooks();
					break;
				case 2:
					seeBookById();
					break;
				case 3:
					addBook();
					break;
				case 4:
					updateBook();
					break;
				case 5:
					renameBook();
					break;
				case 6:
					deleteBook();
					break;
				case 7:
					getGoodReaders();
					break;
				case 8:
					getBadReaders();
					break;
				default:
					flag = false;
					break;
			}
		}
	}
	
	public static void connectionPoolInit() {
		ServiceFactory factory=ServiceFactory.getINSTANCE();
		InitializeConnectionService service=factory.getInitializeConnectionService();

		try {
			service.init();
		} 
		catch (ServiceException e) {
			throw new RuntimeException();
		}

		init = true;
	}

	public static void seeAllBooks() {
		List<Book> books = null;
		Request request = new Request();
        Response response=null;
        
		request.setCommandName(SEE_ALL_BOOKS_COMMAND);

	    response = controller.doAction(request);
	    
	    if(response.getErrorMessage()!=null){
	    	System.out.println(response.getErrorMessage());
	    }
	    
        books=response.getBookList();
        
        if(books!=null){
        	
			for (Book singleBook : books) {
				System.out.println(ID +"-"+ singleBook.getId());
				System.out.println(TITLE +"-"+ singleBook.getTitle());
				System.out.println(AUTHOR +"-"+ singleBook.getAuthor());
				System.out.println(BRIEF +"-"+ singleBook.getBrief());
				System.out.println(PUBLISH_YEAR +"-"+ singleBook.getPublishYear());
			}
			
        }

	}

	public static void seeBookById() {
		String strId = null;
		int id = -1;
		Book book = null;
		Request request = new Request();
        Response response=null;
        
        System.out.println(INPUT_ID);
        
		try {
			strId = reader.readLine();
		} 
		catch (IOException e) {
			logger.error(READING_ERROR);
		}

		id = Integer.valueOf(strId);
		
		request.setCommandName(SEE_BOOK_BY_ID_COMMAND);
		request.setParameter(ID, id);
		
        response = controller.doAction(request);
	    
	    if(response.getErrorMessage()!=null){
	    	System.out.println(response.getErrorMessage());
	    }
        
	    book=response.getBook();
	    
        if(book!=null){
			System.out.println(ID +"-"+ book.getId());
			System.out.println(TITLE +"-"+ book.getTitle());
			System.out.println(AUTHOR +"-"+ book.getAuthor());
			System.out.println(BRIEF +"-"+ book.getBrief());
			System.out.println(PUBLISH_YEAR +"-"+ book.getPublishYear());
        }
        
	}

	public static void addBook() {
		String title = null;
		String author = null;
		String brief = null;
		String strPublishYear = null;
		int publishYear = -1;
		Book book = null;
		Request request = new Request();
        Response response=null;
        
		System.out.println(INPUT_TITLE);

		try {
			title = reader.readLine();
		}
		catch (IOException e) {
			logger.error(READING_ERROR);
		}

		System.out.println(INPUT_AUTHOR);

		try {
			author = reader.readLine();
		}
		catch (IOException e) {
			logger.error(READING_ERROR);
		}

		System.out.println(INPUT_BRIEF);

		try {
			brief = reader.readLine();
		}
		catch (IOException e) {
			logger.error(READING_ERROR);
		}

		System.out.println(INPUT_PUBLISH_YEAR);

		try {
			strPublishYear = reader.readLine();
		}
		catch (IOException e) {
			logger.error(READING_ERROR);
		}
        
		publishYear = Integer.valueOf(strPublishYear);
		
		request.setCommandName(ADD_BOOK_COMMAND);
		request.setParameter(AUTHOR, author);
		request.setParameter(BRIEF, brief);
		request.setParameter(PUBLISH_YEAR, publishYear);
		request.setParameter(TITLE, title);
		
        response = controller.doAction(request);
	    
	    if(response.getErrorMessage()!=null){
	    	System.out.println(response.getErrorMessage());
	    }
	    
	}

	public static void updateBook() {
		String title = null;
		String author = null;
		String brief = null;
		String strPublishYear = null;
		int publishYear = -1;
		Book book = null;
		String strId = null;
		int id = -1;
		Request request = new Request();
        Response response=null;
        
		System.out.println(INPUT_ID);

		try {
			strId = reader.readLine();
		}
		catch (IOException e) {
			logger.error(READING_ERROR);
		}

		id = Integer.valueOf(strId);

		System.out.println(INPUT_TITLE);

		try {
			title = reader.readLine();
		}
		catch (IOException e) {
			logger.error(READING_ERROR);
		}

		System.out.println(INPUT_AUTHOR);

		try {
			author = reader.readLine();
		}
		catch (IOException e) {
			logger.error(READING_ERROR);
		}

		System.out.println(INPUT_BRIEF);

		try {
			brief = reader.readLine();
		}
		catch (IOException e) {
			logger.error(READING_ERROR);
		}

		System.out.println(INPUT_PUBLISH_YEAR);

		try {
			strPublishYear = reader.readLine();
		}
		catch (IOException e) {
			logger.error(READING_ERROR);
		}

		publishYear = Integer.valueOf(strPublishYear);
		
		request.setCommandName(UPDATE_BOOK_COMMAND);
		request.setParameter(ID, id);
		request.setParameter(AUTHOR, author);
		request.setParameter(BRIEF, brief);
		request.setParameter(PUBLISH_YEAR, publishYear);
		request.setParameter(TITLE, title);
		
        response = controller.doAction(request);
	    
	    if(response.getErrorMessage()!=null){
	    	System.out.println(response.getErrorMessage());
	    }

	}

	public static void renameBook() {
		ServiceFactory factory = ServiceFactory.getINSTANCE();
		BookService bookService = factory.getBookService();
		String oldTitle = null;
		String newTitle = null;
		Request request = new Request();
        Response response=null;
        
		System.out.println(INPUT_OLD_TITLE);

		try {
			oldTitle = reader.readLine();
		}
		catch (IOException e) {
			logger.error(READING_ERROR);
		}

		System.out.println(INPUT_NEW_TITLE);

		try {
			newTitle = reader.readLine();
		}

		catch (IOException e) {
			logger.error(READING_ERROR);
		}

		request.setCommandName(RENAME_BOOK_COMMAND);
		request.setParameter(OLD_TITLE, oldTitle);
		request.setParameter(NEW_TITLE, newTitle);
		
        response = controller.doAction(request);
	    
	    if(response.getErrorMessage()!=null){
	    	System.out.println(response.getErrorMessage());
	    }

	}

	public static void deleteBook() {
		String strId = null;
		int id = -1;
		ServiceFactory factory = ServiceFactory.getINSTANCE();
		BookService bookService = factory.getBookService();
		Request request = new Request();
        Response response=null;
        
		System.out.println(INPUT_ID);

		try {
			strId = reader.readLine();
		}
		catch (IOException e) {
			logger.error(READING_ERROR);
		}

		id = Integer.valueOf(strId);

		request.setCommandName(DELETE_BOOK_COMMAND);
		request.setParameter(ID, id);
		
        response = controller.doAction(request);
	    
	    if(response.getErrorMessage()!=null){
	    	System.out.println(response.getErrorMessage());
	    }

	}

	public static void getGoodReaders() {
		ServiceFactory factory = ServiceFactory.getINSTANCE();
		StatisticService statisticService = factory.getStatisticService();
		Map<String, Integer> goodReaders = null;
		Request request = new Request();
        Response response=null;
        
        request.setCommandName(GET_GOOD_READERS_COMMAND);
		
        response = controller.doAction(request);
	    
	    if(response.getErrorMessage()!=null){
	    	System.out.println(response.getErrorMessage());
	    }
        
	    goodReaders=response.getGoodReaders();

	    if(goodReaders!=null){
	    	
			for (Map.Entry<String, Integer> entry : goodReaders.entrySet()) {
				System.out.println(entry.getKey());
				System.out.println(entry.getValue());
			}
			
	    }

	}

	public static void getBadReaders() {
		ServiceFactory factory = ServiceFactory.getINSTANCE();
		StatisticService statisticService = factory.getStatisticService();
		Map<Employee, Integer> badReaders = null;
		Request request = new Request();
        Response response=null;
        
        request.setCommandName(GET_BAD_READERS_COMMAND);
		
        response = controller.doAction(request);
	    
	    if(response.getErrorMessage()!=null){
	    	System.out.println(response.getErrorMessage());
	    }
        
	    badReaders=response.getBadReaders();

	    if(badReaders!=null){
	    	
			for (Map.Entry<Employee, Integer> entry : badReaders.entrySet()) {
				System.out.println(entry.getKey().getName());
				System.out.println(entry.getKey().getBirthday());
				System.out.println(entry.getValue());
			}
			
	    }

	}

}
