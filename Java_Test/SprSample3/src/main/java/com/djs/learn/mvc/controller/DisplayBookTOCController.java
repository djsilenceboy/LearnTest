
package com.djs.learn.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.djs.learn.mvc.dto.Book;

public class DisplayBookTOCController extends AbstractController
{
	private List<Book> books;

	private final Logger log = Logger.getLogger(DisplayBookTOCController.class);

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("handleRequestInternal:Books = " + books);

		Book myBook = null;
		if (books != null && !books.isEmpty()) {
			for (Book book : books) {
				if (book.getIsbn() == Integer.parseInt(request.getParameter("isbn"))) {
					myBook = book;
					break;
				}
			}
		}

		log.info("handleRequestInternal:MyBook = " + myBook);

		return new ModelAndView("displayBookTOC", "book", myBook);
	}

	public List<Book> getBooks(){
		return books;
	}

	public void setBooks(List<Book> books){
		this.books = books;

		log.info("Books = " + books);
	}
}
