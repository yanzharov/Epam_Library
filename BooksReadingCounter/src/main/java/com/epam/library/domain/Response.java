package com.epam.library.domain;

import java.util.List;
import java.util.Map;

public class Response {
	private String errorMessage;
    private Book book;
	private List<Book> bookList;
	private Map<String, Integer> goodReaders;
	private Map<Employee, Integer> badReaders;

	public Response() {

	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	public Map<String, Integer> getGoodReaders() {
		return goodReaders;
	}

	public void setGoodReaders(Map<String, Integer> goodReaders) {
		this.goodReaders = goodReaders;
	}

	public Map<Employee, Integer> getBadReaders() {
		return badReaders;
	}

	public void setBadReaders(Map<Employee, Integer> badReaders) {
		this.badReaders = badReaders;
	}

}
