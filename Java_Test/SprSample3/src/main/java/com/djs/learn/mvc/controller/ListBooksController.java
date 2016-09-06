
package com.djs.learn.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.djs.learn.mvc.dto.Book;

public class ListBooksController extends AbstractController
{
	private List<Book> books;

	private final Logger log = Logger.getLogger(ListBooksController.class);

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("handleRequestInternal:Books = " + books);

		return new ModelAndView("listBooks", "books", books);
	}

	public List<Book> getBooks(){
		return books;
	}

	public void setBooks(List<Book> books){
		this.books = books;

		log.info("Books = " + books);
	}
}
