package com.epam.library.controller;

import java.util.HashMap;
import java.util.Map;

import com.epam.library.command.Command;
import com.epam.library.command.impl.AddBook;
import com.epam.library.command.impl.DeleteBook;
import com.epam.library.command.impl.GetBadReaders;
import com.epam.library.command.impl.GetGoodReaders;
import com.epam.library.command.impl.RenameBook;
import com.epam.library.command.impl.SeeAllBooks;
import com.epam.library.command.impl.SeeBookById;
import com.epam.library.command.impl.UpdateBook;

public class CommandProvider {
	private Map<String, Command> commands = new HashMap<String, Command>();
	private final static String ADD_BOOK = "ADD_BOOK";
	private final static String SEE_BOOK_BY_ID = "SEE_BOOK_BY_ID";
	private final static String DELETE_BOOK = "DELETE_BOOK";
	private final static String UPDATE_BOOK = "UPDATE_BOOK";
	private final static String RENAME_BOOK = "RENAME_BOOK";
	private final static String SEE_ALL_BOOKS = "SEE_ALL_BOOKS";
	private final static String GET_GOOD_READERS = "GET_GOOD_READERS";
	private final static String GET_BAD_READERS = "GET_BAD_READERS";
	
	public CommandProvider() {
		commands.put(ADD_BOOK, new AddBook());
		commands.put(SEE_BOOK_BY_ID, new SeeBookById());
		commands.put(DELETE_BOOK, new DeleteBook());
		commands.put(UPDATE_BOOK, new UpdateBook());
		commands.put(RENAME_BOOK, new RenameBook());
		commands.put(SEE_ALL_BOOKS, new SeeAllBooks());
		commands.put(GET_GOOD_READERS, new GetGoodReaders());
		commands.put(GET_BAD_READERS, new GetBadReaders());
	}

	public Command getCommand(String commandName) {
		Command command;
		command = commands.get(commandName);
		return command;
	}
	
}
